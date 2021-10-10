package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.ParentClassInfoLoader;
import cn.milai.jvmdemo.classfile.TypeDesc;

/**
 * {@link ClassInfo} 测试类
 * @author milai
 * @date 2021.08.17
 */
public class ClassInfoTest {

	private static ClassInfo hello;
	private static ClassInfoLoader loader = new ParentClassInfoLoader() {
		protected ClassInfo loadClass(String name) {
			throw new UnsupportedOperationException();
		};
	};

	@BeforeClass
	public static void setUp() throws IOException {
		DefaultClassInfoLoader.init(Arrays.asList(ClassInfoTest.class.getResource("/").getPath()));
		hello = loader.load(Classes.HELLO_WORLD);
	}

	@Test
	public void testFromClassMetadata() {
		assertFalse(hello.isAbstract());
		assertFalse(hello.isAnnotation());
		assertFalse(hello.isEnum());
		assertFalse(hello.isFinal());
		assertFalse(hello.isInterface());
		assertTrue(hello.isSuper());
		assertTrue(hello.isPublic());
		assertEquals(Classes.HELLO_WORLD, hello.getName());
		assertEquals(Classes.OBJECT, hello.getSuperName());
		assertEquals("cn.milai.jvmdemo.classfile", hello.getPackageName());
		assertEquals(0, hello.getInterfacesName().length);
		assertEquals("HelloWorld.java", hello.getSourceFileName());
	}

	@Test
	public void testMethodAndFields() {
		Field[] fields = hello.getFields();
		assertEquals(0, fields.length);
		Method[] methods = hello.getMethods();
		assertEquals(2, methods.length);

		Method main = hello.getMethod("main", "([Ljava/lang/String;)V");
		assertTrue(main.isPublic());
		assertTrue(main.isStatic());
		assertEquals(TypeDesc.VOID.getValue(), main.getReturnType());
		assertArrayEquals(new String[] { "[Ljava/lang/String;" }, main.getArgsType());
		assertEquals(2, main.getMaxStack());
		assertEquals(1, main.getMaxLocal());
		assertEquals(9, main.getCodes().length);

		Method init = hello.getMethod("<init>", "()V");
		assertTrue(init.isPublic());
		assertFalse(init.isStatic());
		assertEquals(TypeDesc.VOID.getValue(), init.getReturnType());
		assertArrayEquals(new String[] {}, init.getArgsType());
		assertEquals(1, init.getMaxStack());
		assertEquals(1, init.getMaxLocal());
		assertEquals(5, init.getCodes().length);
	}

	@Test
	public void testBindClassInfoLoader() {
		assertSame(DefaultClassInfoLoader.getInstance(), hello.getClassInfoLoader());
	}

	@Test
	public void testGetSuperAndInterface() throws IOException {
		ClassInfo stringLenComparator = loader.load(Classes.STRING_LEN_COMPARATOR);
		ClassInfo object = stringLenComparator.getSuperClassInfo();
		assertNotNull(object);
		assertEquals(Classes.OBJECT, object.getName());
		ClassInfo[] interfacesClassInfo = stringLenComparator.getInterfacesClassInfo();
		assertEquals(1, interfacesClassInfo.length);
		ClassInfo comparator = interfacesClassInfo[0];
		assertEquals(Classes.COMPARATOR, comparator.getName());
	}

	@Test
	public void testIsAssignableFrom() {
		assertTrue(loader.load(Classes.SERIABLIZABLE).isAssignableFrom(loader.load(Classes.STRING)));
		assertTrue(loader.load(Classes.COMPARABLE).isAssignableFrom(loader.load(Classes.STRING)));
		assertTrue(loader.load(Classes.CHAR_SEQUENCE).isAssignableFrom(loader.load(Classes.STRING)));
		assertTrue(loader.load(Classes.OBJECT).isAssignableFrom(loader.load(Classes.STRING)));
		assertTrue(loader.load(Classes.LIST).isAssignableFrom(loader.load(Classes.ARRAY_LIST)));

		assertFalse(loader.load(Classes.COLLECTIONS).isAssignableFrom(loader.load(Classes.LIST)));
		assertFalse(loader.load(Classes.INPUT_STREAM).isAssignableFrom(loader.load(Classes.STRING)));
		assertFalse(loader.load(Classes.LIST).isAssignableFrom(loader.load(Classes.STRING)));

		assertTrue(loader.load(Classes.OBJECT).isAssignableFrom(loader.load(Classes.COMPARABLE)));
		assertTrue(loader.load(Classes.OBJECT).isAssignableFrom(loader.load(Classes.FUNCTION)));
	}

	@Test
	public void testIsSubClassOf() {
		assertTrue(loader.load(Classes.STRING).isSubClassOf(loader.load(Classes.OBJECT)));
		assertTrue(loader.load(Classes.LIST).isSubClassOf(loader.load(Classes.OBJECT)));
		assertTrue(loader.load(Classes.FILE_INPUT).isSubClassOf(loader.load(Classes.INPUT_STREAM)));

		assertFalse(loader.load(Classes.STRING).isSubClassOf(loader.load(Classes.COMPARABLE)));
		assertFalse(loader.load(Classes.STRING).isSubClassOf(loader.load(Classes.CHAR_SEQUENCE)));
		assertFalse(loader.load(Classes.LIST).isSubClassOf(loader.load(Classes.COLLECTION)));
		assertFalse(loader.load(Classes.LIST).isSubClassOf(loader.load(Classes.STRING)));
	}

	@Test
	public void testIsImplements() {
		assertTrue(loader.load(Classes.STRING).isImplements(loader.load(Classes.COMPARABLE)));
		assertTrue(loader.load(Classes.STRING).isImplements(loader.load(Classes.CHAR_SEQUENCE)));
		assertTrue(loader.load(Classes.LIST).isImplements(loader.load(Classes.COLLECTION)));

		assertFalse(loader.load(Classes.STRING).isImplements(loader.load(Classes.OBJECT)));
		assertFalse(loader.load(Classes.LIST).isImplements(loader.load(Classes.OBJECT)));
		assertFalse(loader.load(Classes.FILE_INPUT).isImplements(loader.load(Classes.INPUT_STREAM)));
		assertFalse(loader.load(Classes.LIST).isImplements(loader.load(Classes.STRING)));
	}

}
