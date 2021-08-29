package cn.milai.jvmdemo.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * {@link ClassNames} 测试类
 * @author milai
 * @date 2021.08.17
 */
public class ClassNamesTest {

	@Test
	public void testFromSlash() {
		assertEquals(
			"cn.milai.jvmdemo.classfile.constant.Constant", ClassNames.fromSlash(
				"cn/milai/jvmdemo/classfile/constant/Constant"
			)
		);
		assertEquals("java.lang.Object", ClassNames.fromSlash("java/lang/Object"));
		assertEquals("java.lang.StringBuilder", ClassNames.fromSlash("java/lang/StringBuilder"));
	}

	@Test
	public void testToSlash() {
		assertEquals(
			"cn/milai/jvmdemo/classfile/constant/Constant", ClassNames.toSlash(
				"cn.milai.jvmdemo.classfile.constant.Constant"
			)
		);
		assertEquals("java/lang/Comparable", ClassNames.toSlash("java.lang.Comparable"));
		assertEquals("java/lang/String", ClassNames.toSlash("java.lang.String"));
	}
}
