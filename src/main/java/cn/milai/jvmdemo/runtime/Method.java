package cn.milai.jvmdemo.runtime;

import java.util.ArrayList;
import java.util.List;

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
	private String[] argsType;
	private String returnType;

	public Method(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		super(owner, member, pool);
		parseDescriptor();
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

	private void parseDescriptor() {
		List<String> types = new ArrayList<String>();
		String type = null;
		int nowIndex = 1;
		String descriptor = getDescriptor();
		while ((type = getNextType(nowIndex, descriptor)) != null) {
			types.add(type);
			nowIndex += type.length();
		}
		returnType = getDescriptor().substring(nowIndex + 1);
		argsType = types.toArray(new String[0]);
	}

	private String getNextType(int nowIndex, String descriptor) {
		char ch = descriptor.charAt(nowIndex);
		switch (ch) {
			case 'L' : {
				StringBuilder sb = new StringBuilder();
				for (int i = nowIndex;; i++) {
					sb.append(descriptor.charAt(i));
					if (descriptor.charAt(i) == ';')
						break;
				}
				return sb.toString();
			}
			case '[' : {
				return "[" + getNextType(nowIndex + 1, descriptor);
			}
			case ')' : {
				return null;
			}
			default: {
				return "" + ch;
			}
		}
	}

	public boolean isSynchronized() { return getAccess().isSynchronized(); }

	public boolean isBridge() { return getAccess().isBridge(); }

	public boolean isVarargs() { return getAccess().isVarargs(); }

	public boolean isNative() { return getAccess().isNative(); }

	public boolean isAbstract() { return getAccess().isAbstract(); }

	public int getMaxStack() { return maxStack; }

	public int getMaxLocal() { return maxLocal; }

	public byte[] getCodes() { return codes; }

	public String[] getArgsType() { return argsType; }

	public String getReturnType() { return returnType; }

	@Override
	public String toString() {
		return getClassInfo().toString() + "." + getName() + " " + getDescriptor();
	}

}
