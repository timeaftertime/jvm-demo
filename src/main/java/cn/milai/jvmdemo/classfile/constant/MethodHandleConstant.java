package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodHandleConstant implements Constant {

	private int refKind;
	private int refIndex;

	MethodHandleConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.METHOD_HANDLE; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException {
		refKind = data.readUnsignedByte();
		refIndex = data.readUnsignedShort();
	}

	public int getRefKind() { return refKind; }

	public int getRefIndex() { return refIndex; }

}
