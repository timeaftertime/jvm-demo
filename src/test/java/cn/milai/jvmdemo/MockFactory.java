package cn.milai.jvmdemo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.milai.jvmdemo.runtime.Method;

/**
 * Mock 工厂测试工具类
 * @author milai
 * @date 2021.09.23
 */
public class MockFactory {

	public static Method newMethod(int maxLocals, int maxStack) {
		Method method = mock(Method.class);
		when(method.getMaxLocal()).thenReturn(maxLocals);
		when(method.getMaxStack()).thenReturn(maxStack);
		return method;
	}

}
