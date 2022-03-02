package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.runtime.classes.Field;

/**
 * {@link StringPool} 测试类
 * @author milai
 * @date 2022.03.01
 */
public class StringPoolTest {

	@Test
	public void loadString() throws ClassNotFoundException, IOException {
		ClassInfoLoader loader = TestClassInfoLoader.get();
		ObjectRef ref = StringPool.getString(loader, "abc");
		assertNotNull(ref);
		assertSame(ref, StringPool.getString(loader, "abc"));
		assertNotSame(ref, StringPool.getString(loader, "Abc"));
		Field field = ref.getClassInfo().getField("value", "[C", false);
		assertArrayEquals("abc".toCharArray(), ref.getFields().getRef(field.getSlotId()).chars());
	}

}
