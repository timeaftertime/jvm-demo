package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * Deprecated 属性，表示被 @Deprecated 标记
 * @author milai
 * @date 2021.08.05
 */
public class DeprecatedAttribute extends Attribute {

	public DeprecatedAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if (length != 0)
			throw new AssertionError("length 必须为 0：" + length);
	}

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {}

}
