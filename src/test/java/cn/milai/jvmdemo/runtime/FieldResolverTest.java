package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Field;
import cn.milai.jvmdemo.runtime.ref.FieldRef;

/**
 * {@link FieldResolver} 测试类
 * @author milai
 * @date 2022.01.19
 */
public class FieldResolverTest {

	@Test
	public void testResolve() {
		ClassInfo child = TestClassInfoLoader.get().load(Classes.CHILD);
		FieldRef field3Ref = child.getConstantPool().getFieldRef(3);
		Field field3 = FieldResolver.resolve(field3Ref, false);
		assertSame(child, field3.getClassInfo());
		assertEquals("field3", field3.getName());
		assertEquals("Ljava/lang/String;", field3.getDescriptor());
	}

}
