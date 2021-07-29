package cn.milai.jvmdemo.classfile.constant;

public class InterfaceMethodrefConstantInfo extends RefConstant {

	InterfaceMethodrefConstantInfo() {}

	@Override
	public ConstantTag tag() { return ConstantTag.INTERFACE_METHODREF; }

}
