package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * 解析 {@link Attribute} 的工具类 
 * @author milai
 * @date 2021.08.03
 */
public class Attributes {

	private Attributes() {}

	public static Attribute[] readAttributes(DataInputStream data, ConstantPool constantPool) throws IOException {
		int attributeCount = data.readUnsignedShort();
		Attribute[] attributes = new Attribute[attributeCount];
		for (int i = 0; i < attributeCount; i++) {
			int attrNameIndex = data.readUnsignedShort();
			int attrLen = data.readInt();
			String attrName = constantPool.getUTF8(attrNameIndex).getValue();
			Attribute attribute = AttributeFactory.createAttribute(attrNameIndex, attrName, attrLen);
			attribute.init(attrLen, data, constantPool);
			attributes[i] = attribute;
		}
		return attributes;
	}

}
