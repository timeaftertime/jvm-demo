package cn.milai.jvmdemo.runtime;

/**
 * 对象引用
 * @author milai
 * @date 2021.09.15
 */
public class ObjectRef {

	private ClassInfo classInfo;
	private MemberSlots fields;

	public ObjectRef(ClassInfo classInfo) {
		this.classInfo = classInfo;
		this.fields = new MemberSlots(classInfo.getInstanceSlotCnt());
	}

	public ClassInfo getClassInfo() { return classInfo; }

	public MemberSlots getFields() { return fields; }

}
