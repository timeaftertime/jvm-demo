package cn.milai.jvmdemo.classfile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * {@link AccessMask} 测试类
 * @author milai
 * @date 2021.08.02
 */
public class AccessMaskTest {

	@Test
	public void parseAccessMask() {
		AccessMask accessMask = new AccessMask(33);
		assertFalse(accessMask.isInterface());
		assertFalse(accessMask.isEnum());
		assertFalse(accessMask.isAnnotation());
		assertFalse(accessMask.isFinal());
		assertFalse(accessMask.isAbstract());
		assertTrue(accessMask.isPublic());
		assertTrue(accessMask.isSuper());
	}
}
