package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

public abstract class MemberRef extends SymRef {

	public MemberRef(ConstantPool pool, RTConstantPool rtPool) {
		super(pool, rtPool);
	}

	public abstract String getName();

	public abstract String getDescriptor();

	@Override
	public String toString() {
		return targetClassName() + " " + getName() + " " + getDescriptor();
	}

}
