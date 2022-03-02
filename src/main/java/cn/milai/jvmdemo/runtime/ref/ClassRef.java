package cn.milai.jvmdemo.runtime.ref;

import cn.milai.jvmdemo.classfile.constant.ClassConstant;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.util.ClassNames;

public class ClassRef extends SymRef {

	private String className;

	public ClassRef(ConstantPool pool, RTConstantPool rtPool, ClassConstant c) {
		super(pool, rtPool);
		className = ClassNames.fromSlash(pool.getUTF8(c.getIndex()).getValue());
	}

	@Override
	public String targetClassName() {
		return className;
	}

	@Override
	public String toString() {
		return targetClassName();
	}
}
