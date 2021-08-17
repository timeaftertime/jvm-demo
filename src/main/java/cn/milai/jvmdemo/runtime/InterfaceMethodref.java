package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.InterfaceMethodrefConstant;
import cn.milai.jvmdemo.util.ClassNames;

public class InterfaceMethodref extends MemberRef {

	private String className;
	private String name;
	private String descriptor;

	public InterfaceMethodref(ConstantPool pool, RTConstantPool rtPool, InterfaceMethodrefConstant c) {
		super(pool, rtPool);
		className = ClassNames.fromSlash(pool.getUTF8(pool.getClass(c.getClassIndex()).getIndex()).getValue());
		name = pool.getUTF8(pool.getNameAndType(c.getNameAndTypeIndex()).getNameIndex()).getValue();
		descriptor = pool.getUTF8(pool.getNameAndType(c.getNameAndTypeIndex()).getDescriptorIndex()).getValue();
	}

	@Override
	public String getName() { return name; }

	@Override
	public String getDescriptor() { return descriptor; }

	@Override
	public String targetClassName() {
		return className;
	}

}
