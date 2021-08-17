package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class InvokeDynamicConstant implements Constant {

	private int bootstrapMethodAttrIndex;
	private int nameAndTypeIndex;

	InvokeDynamicConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.INVOKE_DYNAMIC; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException {
		bootstrapMethodAttrIndex = data.readUnsignedShort();
		nameAndTypeIndex = data.readUnsignedShort();
	}

	public int getBootstrapMethodAttrIndex() { return bootstrapMethodAttrIndex; }

	public int getNameAndTypeIndex() { return nameAndTypeIndex; }

}
