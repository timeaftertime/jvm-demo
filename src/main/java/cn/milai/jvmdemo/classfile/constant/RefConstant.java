package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class RefConstant implements Constant {

	protected int classIndex;
	protected int nameAndTypeIndex;

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException {
		classIndex = data.readUnsignedShort();
		nameAndTypeIndex = data.readUnsignedShort();
	}

	public int getClassIndex() { return classIndex; }

	public int getNameAndTypeIndex() { return nameAndTypeIndex; }

}
