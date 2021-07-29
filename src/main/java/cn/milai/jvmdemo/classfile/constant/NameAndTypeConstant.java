package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class NameAndTypeConstant implements Constant {

	private int nameIndex;
	private int descriptorIndex;

	NameAndTypeConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.NAME_AND_TYPE; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException {
		nameIndex = data.readUnsignedShort();
		descriptorIndex = data.readUnsignedShort();
	}

	public int getNameIndex() { return nameIndex; }

	public int getDescriptorIndex() { return descriptorIndex; }

}
