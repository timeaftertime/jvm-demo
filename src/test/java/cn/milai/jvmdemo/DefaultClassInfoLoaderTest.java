package cn.milai.jvmdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link DefaultClassInfoLoader} 测试类
 * @author milai
 * @date 2021.08.27
 */
public class DefaultClassInfoLoaderTest {

	@Test
	public void testLoadJDKClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		DefaultClassInfoLoader loader = DefaultClassInfoLoader.getInstance();
		ClassInfo comparable = loader.load(Classes.COMPARABLE);
		assertEquals(Classes.COMPARABLE, comparable.getName());
		assertEquals("java.lang.Object", comparable.getSuperName());
		assertEquals(0, comparable.getInterfacesName().length);
	}

	@Test
	public void testLoaderAppClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		DefaultClassInfoLoader loader = DefaultClassInfoLoader.getInstance();
		ClassInfo hello = loader.load(Classes.HELLO_WORLD);
		assertNotNull(hello);
		assertEquals(Classes.HELLO_WORLD, hello.getName());
		assertEquals(Classes.OBJECT, hello.getSuperName());
		assertEquals(0, hello.getInterfacesName().length);
	}

}
