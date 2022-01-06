package cn.milai.jvmdemo.constants;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;

/**
 * {@link MethodConst} 测试类
 * @author milai
 * @date 2021.12.15
 */
public class MethodConstTest {

	@Test
	public void testIsInit() {
		assertFalse(MethodConst.isInit(MockFactory.newMethod(null)));
		assertFalse(MethodConst.isInit(MockFactory.newMethod("abc")));
		assertFalse(MethodConst.isInit(MockFactory.newMethod("init")));
		assertTrue(MethodConst.isInit(MockFactory.newMethod(MethodConst.INIT)));
	}

	@Test
	public void testIsClinit() {
		assertFalse(MethodConst.isClinit(MockFactory.newMethod(null)));
		assertFalse(MethodConst.isClinit(MockFactory.newMethod("XXXX")));
		assertFalse(MethodConst.isClinit(MockFactory.newMethod("clinit")));
		assertTrue(MethodConst.isClinit(MockFactory.newMethod(MethodConst.CLINIT)));
	}
}
