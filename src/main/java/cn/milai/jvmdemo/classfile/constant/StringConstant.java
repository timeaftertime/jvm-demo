package cn.milai.jvmdemo.classfile.constant;

public class StringConstant extends SingleRefConstant {

	StringConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.STRING; }

	public String getValue() { return pool.getUTF8(index).getValue(); }

}
