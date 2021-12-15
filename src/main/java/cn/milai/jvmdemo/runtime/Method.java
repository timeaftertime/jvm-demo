package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.ClassMember;
import cn.milai.jvmdemo.classfile.attribute.CodeAttribute;

/**
 * 类方法
 * @author milai
 * @date 2021.09.02
 */
public class Method extends Member {

	private int maxStack;
	private int maxLocal;
	private byte[] codes;
	private Descriptor descriptor;

	public Method(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		super(owner, member, pool);
		descriptor = new Descriptor(getDescriptor());
		parseCodeAttribute(member);
	}

	private void parseCodeAttribute(ClassMember member) {
		CodeAttribute attribute = member.getCodeAttribute();
		if (attribute == null)
			return;
		maxStack = attribute.getMaxStack();
		maxLocal = attribute.getMaxLocal();
		codes = attribute.getCodes();
	}

	public boolean isSynchronized() { return getAccess().isSynchronized(); }

	public boolean isBridge() { return getAccess().isBridge(); }

	public boolean isVarargs() { return getAccess().isVarargs(); }

	public boolean isNative() { return getAccess().isNative(); }

	public boolean isAbstract() { return getAccess().isAbstract(); }

	public int getMaxStack() { return maxStack; }

	public int getMaxLocal() { return maxLocal; }

	public byte[] getCodes() { return codes; }

	public String[] getArgsType() { return descriptor.getArgsType(); }

	public String getReturnType() { return descriptor.getReturnType(); }

	public int getArgsSlotCnt() { return descriptor.getArgsSlotCnt(); }

	@Override
	public String toString() {
		return getClassInfo().toString() + "." + getName() + " " + getDescriptor();
	}

}
