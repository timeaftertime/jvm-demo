package cn.milai.jvmdemo.classfile.constant;

public class InterfaceMethodrefConstant extends RefConstant {

	InterfaceMethodrefConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.INTERFACE_METHODREF; }

}
