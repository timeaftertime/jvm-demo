package cn.milai.jvmdemo.constants;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.milai.jvmdemo.Classes;

/**
 * {@link ClassConst} 测试类
 * @author milai
 * @date 2022.01.06
 */
public class ClassConstTest {

	@Test
	public void testIsPrimitive() {
		assertTrue(ClassConst.isPrimitive("int"));
		assertTrue(ClassConst.isPrimitive("int"));
		assertTrue(ClassConst.isPrimitive("int"));
		assertFalse(ClassConst.isPrimitive(Classes.ADD_TEST));
		assertFalse(ClassConst.isPrimitive(Classes.ARRAY_LIST));
		assertFalse(ClassConst.isPrimitive(Classes.STRING));
		assertFalse(ClassConst.isPrimitive(null));
	}

	@Test
	public void testIsArray() {
		assertTrue(ClassConst.isArray("[I"));
		assertTrue(ClassConst.isArray("[F"));
		assertTrue(ClassConst.isArray(ClassConst.ARRAY_PREFIX + Classes.ADD_TEST));
		assertFalse(ClassConst.isArray(null));
		assertFalse(ClassConst.isArray(Classes.ADD_TEST));
	}

}
