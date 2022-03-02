package cn.milai.jvmdemo.runtime.ref;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;

/**
 * {@link SymRef} 测试类
 * @author milai
 * @date 2021.12.13
 */
public class SymRefTest {

	private RTConstantPool pool;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		TestClassInfoLoader.initDefaultClassInfoLoader();
		pool = DefaultClassInfoLoader.getInstance().load(Classes.CLASS_TEST).getConstantPool();
	}

	@Test
	public void resolvedClass() throws ClassNotFoundException, IOException {
		SymRef classRef = pool.getClassRef(5);
		ClassInfo classInfo = classRef.resolvedClass();
		assertEquals(classInfo.getName(), classRef.targetClassName().replace("/", "."));
		assertSame(classInfo, classRef.resolvedClass());
	}

}
