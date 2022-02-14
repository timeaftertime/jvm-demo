package cn.milai.jvmdemo.instruction.natives;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MessageHolder;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link NativeMethodRegistry} 测试类
 * @author milai
 * @date 2022.02.13
 */
public class NativeMethodRegistryTest {

	@Before
	public void setUp() {
		MessageHolder.clear();
	}

	@Test
	public void registerAndInvokeMethod() {
		String className = "XXX";
		String methodName = "fun1";
		String descriptor = "V";
		NativeMethodRegistry.register(
			className, methodName, descriptor, f -> MessageHolder.addMessage("invoke....")
		);
		NativeMethodRegistry.invoke(new Frame(null, MockFactory.newMethod(className, methodName, descriptor)));
		List<String> messages = MessageHolder.getMessages();
		assertEquals(1, messages.size());
		assertEquals("invoke....", messages.get(0));
	}

}
