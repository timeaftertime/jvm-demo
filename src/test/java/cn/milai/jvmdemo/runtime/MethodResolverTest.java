package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.MethodConst;

/**
 * {@link MethodResolver} 测试类
 * @author milai
 * @date 2021.12.15
 */
public class MethodResolverTest {

	private ClassInfo child;

	@Before
	public void setUp() {
		TestClassInfoLoader.initDefaultClassInfoLoader();
		child = DefaultClassInfoLoader.getInstance().load(Classes.CHILD);
	}

	@Test
	public void testFindMethod() {
		MethodRef parentInit = child.getConstantPool().getMethodRef(1);
		Method method = MethodResolver.resolve(child.getSuperClassInfo(), parentInit);
		assertEquals(MethodConst.INIT, method.getName());
		assertEquals(TypeDesc.VOID.getValue(), method.getReturnType());
		assertEquals(child.getClassInfoLoader().load(Classes.PARENT), method.getClassInfo());
	}
}
