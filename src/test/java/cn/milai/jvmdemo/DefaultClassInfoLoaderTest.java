package cn.milai.jvmdemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link DefaultClassInfoLoader} 测试类
 * @author milai
 * @date 2021.08.27
 */
public class DefaultClassInfoLoaderTest {

	private static ClassInfoLoader loader;

	@BeforeClass
	public static void setUp() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		loader = DefaultClassInfoLoader.getInstance();
	}

	@Test
	public void testLoadJDKClass() {
		ClassInfo comparable = loader.load(Classes.COMPARABLE);
		assertEquals(Classes.COMPARABLE, comparable.getName());
		assertEquals(Classes.OBJECT, comparable.getSuperName());
		assertEquals(0, comparable.getInterfacesName().length);
	}

	@Test
	public void testLoaderAppClass() {
		ClassInfo hello = loader.load(Classes.HELLO_WORLD);
		assertNotNull(hello);
		assertEquals(Classes.HELLO_WORLD, hello.getName());
		assertEquals(Classes.OBJECT, hello.getSuperName());
		assertEquals(0, hello.getInterfacesName().length);
	}

	@Test
	public void testLoadSame() {
		assertSame(loader.load(Classes.COMPARABLE), loader.load(Classes.COMPARABLE));
	}

	@Test
	public void testLoadArray() {
		ClassInfo comparableArray = loader.load("[" + Classes.COMPARABLE);
		assertNotNull(comparableArray);
		assertEquals(ClassConst.ARRAY_PREFIX + Classes.COMPARABLE, comparableArray.getName());
		assertEquals(Classes.OBJECT, comparableArray.getSuperClassInfo().getName());
	}

}
