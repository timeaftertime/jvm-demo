package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * Synthetic 属性，标记由编译器产生
 * @author milai
 * @date 2021.08.05
 */
public class SyntheticAttribute extends Attribute {

	public SyntheticAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if (length != 0)
			throw new AssertionError("length 必须为 0：" + length);
	}

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {}

}
