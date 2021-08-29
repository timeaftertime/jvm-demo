package cn.milai.jvmdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import cn.milai.jvmdemo.classfile.ClassFileRes;
import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link ClassInfoLoader} 测试类
 * @author milai
 * @date 2021.08.29
 */
public class ClassInfoLoaderTest {

	@Test
	public void testLoadClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		String TEST_ERR_MSG = "测试消息";
		ClassInfoLoader loader = new ClassInfoLoader() {
			@Override
			protected ClassInfo loadClass(String name) {
				throw new ClassInfoNotFoundException(TEST_ERR_MSG);
			}
		};

		String COMPARABLE = "java.lang.Comparable";
		ClassInfo comparable = loader.load(COMPARABLE);
		assertEquals(COMPARABLE, comparable.getName());
		assertEquals("java.lang.Object", comparable.getSuperName());
		assertEquals(0, comparable.getInterfacesName().length);

		ClassInfo hello = loader.load(ClassFileRes.HELLO_WORLD);
		assertNotNull(hello);
		assertEquals(ClassFileRes.HELLO_WORLD, hello.getName());
		assertEquals("java.lang.Object", hello.getSuperName());
		assertEquals(0, hello.getInterfacesName().length);

		String CLASS_NOT_FOUND = "class.not.found";
		try {
			loader.load(CLASS_NOT_FOUND);
		} catch (Exception e) {
			assertTrue(e instanceof ClassInfoNotFoundException);
			assertTrue(e.getMessage().contains(TEST_ERR_MSG) && !e.getMessage().contains(CLASS_NOT_FOUND));
			return;
		}
		fail();
	}

}
