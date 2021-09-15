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
}
