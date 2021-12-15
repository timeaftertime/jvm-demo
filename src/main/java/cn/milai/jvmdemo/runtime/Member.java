package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.AccessMask;
import cn.milai.jvmdemo.classfile.ClassMember;

/**
 * 类成员
 * @author milai
 * @date 2021.09.02
 */
public class Member {

	private ClassInfo owner;
	private AccessMask access;
	private String name;
	private String descriptor;

	public Member(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		this.owner = owner;
		access = member.getAccessMask();
		name = pool.getString(member.getNameIndex());
		descriptor = pool.getString(member.getDescriptorIndex());
	}

	public String getName() { return name; }

	public String getDescriptor() { return descriptor; }

	public boolean isPublic() { return access.isPublic(); }

	public boolean isPrivate() { return access.isPrivate(); }

	public boolean isProtected() { return access.isProtected(); }

	public boolean isStatic() { return access.isStatic(); }

	public boolean isFinal() { return access.isFinal(); }

	public boolean isSynthetic() { return access.isSynthetic(); }

	public AccessMask getAccess() { return access; }

	/**
	 * 获取该成员属于哪个类
	 * 
	 * @return
	 */
	public ClassInfo getClassInfo() { return owner; }

	/**
	 * 判断指定 {@code name} 和 {@code descriptor} 是否为当前 {@link Member} 的签名
	 * @param name
	 * @param descriptor
	 * @return
	 */
	public boolean isSignature(String name, String descriptor) {
		if (name == null || descriptor == null) {
			return false;
		}
		return this.name.equals(name) && this.descriptor.equals(descriptor);
	}

	/**
	 * 判断当前成员是否可以被指定类访问
	 * @param c
	 * @return
	 */
	public boolean isAccessiableFrom(ClassInfo c) {
		if (isPublic()) {
			return true;
		}
		if (isPrivate()) {
			return c == owner;
		}
		if (c.getPackageName().equals(owner.getPackageName())) {
			return true;
		}
		return isProtected() && c.isSubClassOf(owner);
	}
}
