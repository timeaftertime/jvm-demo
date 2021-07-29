package cn.milai.jvmdemo.classfile.constant;

public class FieldrefConstant extends RefConstant {

	FieldrefConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.FIELDREF; }

}
