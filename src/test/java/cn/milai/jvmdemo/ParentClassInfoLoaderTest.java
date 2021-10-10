package cn.milai.jvmdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link ParentClassInfoLoader} 测试类
 * @author milai
 * @date 2021.08.29
 */
public class ParentClassInfoLoaderTest {

	@Test
	public void testLoadClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		String TEST_ERR_MSG = "测试消息";
		ParentClassInfoLoader loader = new ParentClassInfoLoader() {
			@Override
			protected ClassInfo loadClass(String name) {
				throw new ClassInfoNotFoundException(TEST_ERR_MSG);
			}
		};

		ClassInfo comparable = loader.load(Classes.COMPARABLE);
		assertEquals(Classes.COMPARABLE, comparable.getName());
		assertEquals("java.lang.Object", comparable.getSuperName());
		assertEquals(0, comparable.getInterfacesName().length);

		ClassInfo hello = loader.load(Classes.HELLO_WORLD);
		assertNotNull(hello);
		assertEquals(Classes.HELLO_WORLD, hello.getName());
		assertEquals(Classes.OBJECT, hello.getSuperName());
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
