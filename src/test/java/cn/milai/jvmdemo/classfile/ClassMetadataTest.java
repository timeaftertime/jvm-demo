package cn.milai.jvmdemo.classfile;

import static cn.milai.jvmdemo.classfile.constant.ConstantPoolTestUtils.assertClass;
import static cn.milai.jvmdemo.classfile.constant.ConstantPoolTestUtils.assertFieldRef;
import static cn.milai.jvmdemo.classfile.constant.ConstantPoolTestUtils.assertMethodRef;
import static cn.milai.jvmdemo.classfile.constant.ConstantPoolTestUtils.assertNameAndType;
import static cn.milai.jvmdemo.classfile.constant.ConstantPoolTestUtils.assertString;
import static cn.milai.jvmdemo.classfile.constant.ConstantPoolTestUtils.assertUTF8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.classfile.attribute.Attribute;
import cn.milai.jvmdemo.classfile.attribute.SourceFileAttribute;
import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * {@link ClassMetadata} 测试类
 * @author milai
 * @date 2021.08.09
 */
public class ClassMetadataTest {

	@Test
	public void testParseClassFile() throws IOException {
		ClassMetadata metadata = ClassMetadataRes.helloWorld();

		assertEquals("CAFEBABE", metadata.getMagicNumber());
		assertEquals(0, metadata.getMinorVersion());
		assertEquals(52, metadata.getMajorVersion());

		ConstantPool pool = metadata.getConstantPool();
		assertEquals(29, pool.size());
		assertMethodRef(pool, 1, 6, 15);
		assertFieldRef(pool, 2, 16, 17);
		assertString(pool, 3, 18);
		assertMethodRef(pool, 4, 19, 20);
		assertClass(pool, 5, 21);
		assertClass(pool, 6, 22);
		assertUTF8(pool, 7, "<init>");
		assertUTF8(pool, 8, "()V");
		assertUTF8(pool, 9, "Code");
		assertUTF8(pool, 10, "LineNumberTable");
		assertUTF8(pool, 11, "main");
		assertUTF8(pool, 12, "([Ljava/lang/String;)V");
		assertUTF8(pool, 13, "SourceFile");
		assertUTF8(pool, 14, "HelloWorld.java");
		assertNameAndType(pool, 15, 7, 8);
		assertClass(pool, 16, 23);
		assertNameAndType(pool, 17, 24, 25);
		assertUTF8(pool, 18, "Hello World!");
		assertClass(pool, 19, 26);
		assertNameAndType(pool, 20, 27, 28);
		assertUTF8(pool, 21, "cn/milai/jvmdemo/classfile/HelloWorld");
		assertUTF8(pool, 22, "java/lang/Object");
		assertUTF8(pool, 23, "java/lang/System");
		assertUTF8(pool, 24, "out");
		assertUTF8(pool, 25, "Ljava/io/PrintStream;");
		assertUTF8(pool, 26, "java/io/PrintStream");
		assertUTF8(pool, 27, "println");
		assertUTF8(pool, 28, "(Ljava/lang/String;)V");

		AccessMask mask = metadata.getAccessMask();
		assertTrue(mask.isPublic());
		assertTrue(mask.isSuper());
		assertFalse(mask.isAbstract());
		assertFalse(mask.isAnnotation());
		assertFalse(mask.isBridge());
		assertFalse(mask.isEnum());
		assertFalse(mask.isFinal());
		assertFalse(mask.isInterface());
		assertFalse(mask.isNative());
		assertFalse(mask.isPrivate());
		assertFalse(mask.isProtected());
		assertFalse(mask.isStatic());
		assertFalse(mask.isStrictfp());
		assertFalse(mask.isSynthetic());

		assertEquals(6, metadata.getSuperClassIndex());
		assertEquals(5, metadata.getClassIndex());
		assertEquals(0, metadata.getInterfacesIndex().length);

		assertEquals(0, metadata.getFields().length);
		ClassMember[] methods = metadata.getMethods();
		assertEquals(2, methods.length);
		assertEquals(7, methods[0].nameIndex);
		assertEquals(8, methods[0].descriptorIndex);
		assertEquals(11, methods[1].nameIndex);
		assertEquals(12, methods[1].descriptorIndex);

		Attribute[] attributes = metadata.getAttributes();
		assertEquals(1, attributes.length);
		assertTrue(attributes[0] instanceof SourceFileAttribute);
		SourceFileAttribute sourceFile = (SourceFileAttribute) attributes[0];
		assertEquals(14, sourceFile.getSourceFileIndex());

	}
}
