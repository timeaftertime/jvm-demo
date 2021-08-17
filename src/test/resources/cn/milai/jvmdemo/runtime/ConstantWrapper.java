package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.Constant;

public class ConstantWrapper {

	private Constant c;

	public ConstantWrapper(Constant c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "ConstantWrapper(tag=" + c.tag() + "0";
	}

}
