package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * 不支持的 {@link Attribute} 类型
 * @author milai
 * @date 2021.08.05
 */
public class UnknownAttribute extends Attribute {

	public UnknownAttribute(int nameIndex, int length) { super(nameIndex, length); }

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		while (attrLen-- > 0) {
			data.readByte();
		}
	}

}
