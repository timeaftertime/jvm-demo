package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.ClassMember;
import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.classfile.attribute.CodeAttribute;
import cn.milai.jvmdemo.instruction.Opcode;

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
		descriptor = new Descriptor(getDescriptor(), isStatic());
		if (isNative()) {
			injectNativeCode();
		} else {
			parseCodeAttribute(member);
		}
	}

	/**
	 * 根据方法描述注入 invoke_native 和 return 指令
	 */
	private void injectNativeCode() {
		maxStack = 4;
		maxLocal = descriptor.getArgsSlotCnt();
		codes = buildNativeCode(TypeDesc.of(descriptor.getReturnType()));
	}

	private byte[] buildNativeCode(TypeDesc returnType) {
		int returnCode = 0;
		switch (returnType) {
			case VOID :
				returnCode = Opcode.RETURN.getValue();
				break;
			case INT :
				returnCode = Opcode.IRETURN.getValue();
				break;
			case LONG :
				returnCode = Opcode.LRETURN.getValue();
				break;
			case FLOAT :
				returnCode = Opcode.FRETURN.getValue();
				break;
			case DOUBLE :
				returnCode = Opcode.DRETURN.getValue();
				break;
			default:
				returnCode = Opcode.ARETURN.getValue();
				break;
		}
		return new byte[] { (byte) Opcode.INVOKENATIVE.getValue(), (byte) returnCode };
	}

	private void parseCodeAttribute(ClassMember member) {
		CodeAttribute attribute = member.getCodeAttribute();
		if (attribute == null) {
			return;
		}
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
