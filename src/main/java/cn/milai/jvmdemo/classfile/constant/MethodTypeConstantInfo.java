package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodTypeConstantInfo implements Constant {

	private int descriptorIndex;

	MethodTypeConstantInfo() {}

	@Override
	public ConstantTag tag() { return ConstantTag.METHOD_TYPE; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException {
		descriptorIndex = data.readUnsignedShort();
	}

	public int getDescriptorIndex() { return descriptorIndex; }

}
