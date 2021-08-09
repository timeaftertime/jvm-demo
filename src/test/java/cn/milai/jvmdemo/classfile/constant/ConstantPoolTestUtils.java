package cn.milai.jvmdemo.classfile.constant;

import static org.junit.Assert.assertEquals;

/**
 * {@link ConstantPool} 测试工具类
 * @author milai
 * @date 2021.08.09
 */
public class ConstantPoolTestUtils {

	public static void assertUTF8(ConstantPool pool, int index, String value) {
		assertEquals(value, pool.getUTF8(index).getValue());
	}

	public static void assertString(ConstantPool pool, int index, int value) {
		assertEquals(value, pool.getString(index).getIndex());
	}

	public static void assertClass(ConstantPool pool, int index, int value) {
		assertEquals(value, pool.getClass(index).getIndex());
	}

	public static void assertMethodRef(ConstantPool pool, int index, int classIndex, int nameAndTypeIndex) {
		MethodrefConstant c = pool.getMethodref(index);
		assertEquals(classIndex, c.getClassIndex());
		assertEquals(nameAndTypeIndex, c.getNameAndTypeIndex());
	}

	public static void assertFieldRef(ConstantPool pool, int index, int classIndex, int nameAndTypeIndex) {
		FieldrefConstant c = pool.getFieldref(index);
		assertEquals(classIndex, c.getClassIndex());
		assertEquals(nameAndTypeIndex, c.getNameAndTypeIndex());
	}

	public static void assertNameAndType(ConstantPool pool, int index, int nameIndex, int descriptorIndex) {
		NameAndTypeConstant c = pool.getNameAndType(index);
		assertEquals(nameIndex, c.getNameIndex());
		assertEquals(descriptorIndex, c.getDescriptorIndex());
	}
}
