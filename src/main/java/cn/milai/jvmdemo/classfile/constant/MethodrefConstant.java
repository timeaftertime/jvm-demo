package cn.milai.jvmdemo.classfile.constant;

/**
 * 方法常量
 * @author milai
 * @date 2021.12.15
 */
public class MethodrefConstant extends RefConstant {

	MethodrefConstant() {
	}

	@Override
	public ConstantTag tag() {
		return ConstantTag.METHODREF;
	}

}
