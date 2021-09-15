package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.classfile.ClassMetadataRes;
import cn.milai.jvmdemo.classfile.TypeDesc;

/**
 * {@link ClassInfo} 测试类
 * @author milai
 * @date 2021.08.17
 */
public class ClassInfoTest {

	private static ClassInfo hello;

	@BeforeClass
	public static void setUp() throws IOException {
		hello = new ClassInfo(ClassMetadataRes.helloWorld());
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
		assertEquals("cn.milai.jvmdemo.classfile.HelloWorld", hello.getName());
		assertEquals("java.lang.Object", hello.getSuperName());
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

}
