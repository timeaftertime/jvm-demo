package cn.milai.jvmdemo.classfile.constant;

public class MethodrefConstant extends RefConstant {

	MethodrefConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.METHODREF; }

}
