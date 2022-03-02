package cn.milai.jvmdemo.runtime.ref;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.RefConstant;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.Method;
import cn.milai.jvmdemo.util.ClassNames;

/**
 * 抽象的方法符号引用
 * @author milai
 * @date 2021.12.15
 */
public abstract class AbstractMethodRef extends MemberRef {

	private String className;
	private String name;
	private String descriptor;

	private Method method;

	public AbstractMethodRef(ConstantPool pool, RTConstantPool rtPool, RefConstant c) {
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

	public Method getMethod() { return method; }

	public void setMethod(Method method) {
		if (this.method != null) {
			throw new IllegalStateException("重复解析方法");
		}
		this.method = method;
	}

}
