package cn.milai.jvmdemo.classfile.constant;

public class ClassConstant extends SingleRefConstant {

	ClassConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.CLASS; }

}
