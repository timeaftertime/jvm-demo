package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * ConstantValue 属性，表示一个常量字段(final)的值
 * @author milai
 * @date 2021.08.05
 */
public class ConstantValueAttribute extends Attribute {

	private int constantValueIndex;

	public ConstantValueAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if (length != 2)
			throw new AssertionError("length 必须为 2：" + length);
	}

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		constantValueIndex = data.readUnsignedShort();
	}

	public int getConstantValueIndex() { return constantValueIndex; }

}
