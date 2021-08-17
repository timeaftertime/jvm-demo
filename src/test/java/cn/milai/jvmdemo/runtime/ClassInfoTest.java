package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.classfile.ClassMetadataRes;

/**
 * {@link ClassInfo} 测试类
 * @author milai
 * @date 2021.08.17
 */
public class ClassInfoTest {

	@Test
	public void testFromClassMetadata() throws IOException {
		ClassInfo classInfo = new ClassInfo(ClassMetadataRes.helloWorld());
		assertFalse(classInfo.isAbstract());
		assertFalse(classInfo.isAnnotation());
		assertFalse(classInfo.isEnum());
		assertFalse(classInfo.isFinal());
		assertFalse(classInfo.isInterface());
		assertTrue(classInfo.isSuper());
		assertTrue(classInfo.isPublic());
		assertEquals("cn.milai.jvmdemo.classfile.HelloWorld", classInfo.getName());
		assertEquals("java.lang.Object", classInfo.getSuperName());
		assertEquals("cn.milai.jvmdemo.classfile", classInfo.getPackageName());
		assertEquals(0, classInfo.getInterfacesName().length);
		assertEquals("HelloWorld.java", classInfo.getSourceFileName());
	}

}
