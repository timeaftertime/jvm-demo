package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.classfile.ClassMetadataRes;
import cn.milai.jvmdemo.runtime.ref.MemberRef;

/**
 * {@link RTConstantPool} 测试类
 * @author milai
 * @date 2021.08.16
 */
public class RTConstantPoolTest {

	@Test
	public void testFromConstantPool() throws IOException {
		RTConstantPool pool = new RTConstantPool(ClassMetadataRes.constantWrapper().getConstantPool(), null);
		assertEquals(42, pool.size());
		assertMethodRef(pool, 1, Classes.OBJECT, "<init>", "()V");
		assertFieldRef(pool, 2, Classes.CONSTANT_WRAPPER, "c", "Lcn/milai/jvmdemo/classfile/constant/Constant;");
		assertEquals(Classes.STRING_BUILDER, pool.getClassRef(3).targetClassName());
		assertMethodRef(pool, 4, Classes.STRING_BUILDER, "<init>", "()V");
		assertEquals("ConstantWrapper(tag=", pool.getString(5));
		assertMethodRef(pool, 6, Classes.STRING_BUILDER, "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
		assertInterfaceMethodRef(
			pool, 7, Classes.CONSTANT, "tag", "()Lcn/milai/jvmdemo/classfile/constant/ConstantTag;"
		);
		assertMethodRef(pool, 8, Classes.STRING_BUILDER, "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;");
		assertEquals("0", pool.getString(9));
		assertMethodRef(pool, 10, Classes.STRING_BUILDER, "toString", "()Ljava/lang/String;");
		assertEquals(Classes.CONSTANT_WRAPPER, pool.getClassRef(11).targetClassName());
		assertEquals(Classes.OBJECT, pool.getClassRef(12).targetClassName());
		assertEquals("c", pool.getString(13));
		assertEquals("Lcn/milai/jvmdemo/classfile/constant/Constant;", pool.getString(14));
		assertEquals("<init>", pool.getString(15));
		assertEquals("(Lcn/milai/jvmdemo/classfile/constant/Constant;)V", pool.getString(16));
		assertEquals("Code", pool.getString(17));
		assertEquals("LineNumberTable", pool.getString(18));
		assertEquals("toString", pool.getString(19));
		assertEquals("()Ljava/lang/String;", pool.getString(20));
		assertEquals("SourceFile", pool.getString(21));
		assertEquals("ConstantWrapper.java", pool.getString(22));
		assertEquals("java/lang/StringBuilder", pool.getString(25));
		assertEquals("ConstantWrapper(tag=", pool.getString(26));
		assertEquals(Classes.CONSTANT, pool.getClassRef(28).targetClassName());
		assertEquals("0", pool.getString(31));
		assertEquals("cn/milai/jvmdemo/runtime/ConstantWrapper", pool.getString(33));
		assertEquals("java/lang/Object", pool.getString(34));
		assertEquals("()V", pool.getString(35));
		assertEquals("append", pool.getString(36));
		assertEquals("(Ljava/lang/String;)Ljava/lang/StringBuilder;", pool.getString(37));
		assertEquals("cn/milai/jvmdemo/classfile/constant/Constant", pool.getString(38));
		assertEquals("tag", pool.getString(39));
		assertEquals("()Lcn/milai/jvmdemo/classfile/constant/ConstantTag;", pool.getString(40));
		assertEquals("(Ljava/lang/Object;)Ljava/lang/StringBuilder;", pool.getString(41));
	}

	private void assertMethodRef(RTConstantPool pool, int index, String className, String name, String descriptor) {
		assertMember(pool.getMethodRef(index), className, name, descriptor);
	}

	private void assertFieldRef(RTConstantPool pool, int index, String className, String name, String descriptor) {
		assertMember(pool.getFieldRef(index), className, name, descriptor);
	}

	private void assertInterfaceMethodRef(RTConstantPool pool, int index, String className, String name,
		String descriptor) {
		assertMember(pool.getInterfaceMethodRef(index), className, name, descriptor);
	}

	private void assertMember(MemberRef ref, String className, String name, String descriptor) {
		assertEquals(className, ref.targetClassName());
		assertEquals(name, ref.getName());
		assertEquals(descriptor, ref.getDescriptor());
	}
}
