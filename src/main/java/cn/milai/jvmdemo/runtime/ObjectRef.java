package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.ClassConst;

/**
 * 对象引用
 * @author milai
 * @date 2021.09.15
 */
public class ObjectRef {

	private ClassInfo classInfo;
	private MemberSlots fields;
	private Elements elements;
	private boolean hasDoubleSlot;

	/**
	 * 创建一个对象
	 * @param classInfo
	 */
	public ObjectRef(ClassInfo classInfo) {
		this.classInfo = classInfo;
		this.fields = new MemberSlots(classInfo.getInstanceSlotCnt());
	}

	/**
	 * 创建一个数组对象
	 * @param arrayClass
	 * @param len
	 */
	public ObjectRef(ClassInfo arrayClass, int len) {
		if (!arrayClass.isArray()) {
			throw new IllegalArgumentException("不是数组类型:" + arrayClass);
		}
		if (len < 0) {
			throw new IllegalArgumentException("数组长度必须大于 0");
		}
		String className = ClassConst.elementClassNameOf(arrayClass.getName());
		if (ClassConst.findPrimitive(className) != null && TypeDesc.of(className).needDoubleSlot()) {
			len *= 2;
			hasDoubleSlot = true;
		}
		this.classInfo = arrayClass;
		this.elements = new Elements(len);
	}

	public ClassInfo getClassInfo() { return classInfo; }

	public MemberSlots getFields() { return fields; }

	public Elements getElements() { return elements; }

	public int length() {
		if (elements == null) {
			throw new UnsupportedOperationException("不是数组:" + classInfo);
		}
		return hasDoubleSlot ? elements.getCapacity() / 2 : elements.getCapacity();
	}

}
