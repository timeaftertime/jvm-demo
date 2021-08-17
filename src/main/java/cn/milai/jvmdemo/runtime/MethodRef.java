package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.MethodrefConstant;
import cn.milai.jvmdemo.util.ClassNames;

public class MethodRef extends MemberRef {

	private String targetClassName;
	private String name;
	private String descriptor;

	public MethodRef(ConstantPool pool, RTConstantPool rtPool, MethodrefConstant c) {
		super(pool, rtPool);
		targetClassName = ClassNames.fromSlash(pool.getUTF8(pool.getClass(c.getClassIndex()).getIndex()).getValue());
		name = pool.getUTF8(pool.getNameAndType(c.getNameAndTypeIndex()).getNameIndex()).getValue();
		descriptor = pool.getUTF8(pool.getNameAndType(c.getNameAndTypeIndex()).getDescriptorIndex()).getValue();
	}

	@Override
	public String getName() { return name; }

	@Override
	public String getDescriptor() { return descriptor; }

	@Override
	public String targetClassName() {
		return targetClassName;
	}

}
