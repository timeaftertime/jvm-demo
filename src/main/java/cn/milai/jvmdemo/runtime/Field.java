package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.ClassMember;
import cn.milai.jvmdemo.classfile.attribute.ConstantValueAttribute;

/**
 * 类字段
 * @author milai
 * @date 2021.09.02
 */
public class Field extends Member {

	private int slotId;
	private int constantValueIndex;

	public Field(ClassInfo owner, ClassMember member, RTConstantPool pool) {
		super(owner, member, pool);
		ConstantValueAttribute constantValue = member.getConstantValueAttribute();
		if (constantValue != null) {
			constantValueIndex = constantValue.getConstantValueIndex();
		}
	}

	public boolean isVolatile() { return getAccess().isVolatile(); }

	public boolean isTransient() { return getAccess().isTransient(); }

	public boolean isEnum() { return getAccess().isEnum(); }

	public int getConstantValueIndex() { return constantValueIndex; }

	public int getSlotId() { return slotId; }

	public void setSlotId(int slotId) { this.slotId = slotId; }

	@Override
	public String toString() {
		return getClassInfo().toString() + "." + getName() + " " + getDescriptor();
	}

}
