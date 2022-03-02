package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Field;
import cn.milai.jvmdemo.runtime.slot.Elements;
import cn.milai.jvmdemo.runtime.slot.MemberSlots;
import cn.milai.jvmdemo.runtime.stack.StackTrace;

/**
 * 对象引用
 * @author milai
 * @date 2021.09.15
 */
public class ObjectRef {

	private static final String MESSAGE_NAME = "detailMessage";
	private static final String MESSAGE_DESC = "Ljava/lang/String;";
	private static final int STRING_VALUE_SLOT_ID = 0;

	private ClassInfo classInfo;

	// 普通类
	private MemberSlots fields;
	// Throable 子类
	private StackTrace[] stackTraces;
	// 数组类
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

	public char[] chars() {
		char[] chs = new char[elements.getCapacity()];
		for (int i = 0; i < elements.getCapacity(); i++) {
			chs[i] = (char) elements.getInt(i);
		}
		return chs;
	}

	public String getMessage() {
		ClassInfo c = getClassInfo();
		ClassInfoLoader loader = c.getClassInfoLoader();
		while (c != null && c != loader.load(ClassConst.THROWABLE)) {
			c = c.getSuperClassInfo();
		}
		if (c == null) {
			throw new IllegalStateException(String.format("%s 不是 %s 子类", getClassInfo(), ClassConst.THROWABLE));
		}
		Field messageField = c.getField(MESSAGE_NAME, MESSAGE_DESC, false);
		ObjectRef strRef = getFields().getRef(messageField.getSlotId());
		return new String(strRef.getFields().getRef(STRING_VALUE_SLOT_ID).chars());
	}

	public void setStackTraces(StackTrace[] stackTraces) { this.stackTraces = stackTraces; }

	public StackTrace[] getStackTraces() { return stackTraces; }

	@Override
	public String toString() {
		return String.format("ObjectRef[%s]", classInfo);
	}

}
