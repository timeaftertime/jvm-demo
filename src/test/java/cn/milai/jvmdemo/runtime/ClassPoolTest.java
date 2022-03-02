package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;

/**
 * {@link ClassPool} 测试类
 * @author milai
 * @date 2022.03.01
 */
public class ClassPoolTest {

	@Test
	public void loadString() throws ClassNotFoundException, IOException {
		ClassInfoLoader loader = TestClassInfoLoader.get();
		ObjectRef addTest = ClassPool.getRef(loader.load(Classes.ADD_TEST));
		assertNotNull(addTest);
		assertSame(addTest, ClassPool.getRef(loader.load(Classes.ADD_TEST)));
		assertNotSame(addTest, ClassPool.getRef(loader.load(Classes.ARRAY_LIST)));
	}

}
