package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.FieldrefConstant;
import cn.milai.jvmdemo.util.ClassNames;

/**
 * 字段引用
 * @author milai
 * @date 2022.01.19
 */
public class FieldRef extends MemberRef {

	private String className;
	private String name;
	private String descriptor;

	private Field field;

	public FieldRef(ConstantPool pool, RTConstantPool rtPool, FieldrefConstant c) {
		super(pool, rtPool);
		className = ClassNames.fromSlash(pool.getUTF8(pool.getClass(c.getClassIndex()).getIndex()).getValue());
		name = pool.getUTF8(pool.getNameAndType(c.getNameAndTypeIndex()).getNameIndex()).getValue();
		descriptor = pool.getUTF8(pool.getNameAndType(c.getNameAndTypeIndex()).getDescriptorIndex()).getValue();
		c.getNameAndTypeIndex();
	}

	@Override
	public String getName() { return name; }

	@Override
	public String getDescriptor() { return descriptor; }

	@Override
	public String targetClassName() {
		return className;
	}

	public Field getField() { return field; }

	public void setField(Field field) { this.field = field; }

	@Override
	public String toString() {
		return "FieldRef [className=" + className + ", name=" + name + ", descriptor=" + descriptor + "]";
	}

}
