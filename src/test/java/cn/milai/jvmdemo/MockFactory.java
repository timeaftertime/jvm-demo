package cn.milai.jvmdemo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.Method;

/**
 * Mock 工厂测试工具类
 * @author milai
 * @date 2021.09.23
 */
public class MockFactory {

	private static final ClassInfo MOCK_CLASS_INFO;

	static {
		MOCK_CLASS_INFO = mock(ClassInfo.class);
		when(MOCK_CLASS_INFO.isInitialized()).thenReturn(true);
	}

	public static Method newMethod(int maxLocals, int maxStack) {
		Method method = newMethod();
		when(method.getMaxLocal()).thenReturn(maxLocals);
		when(method.getMaxStack()).thenReturn(maxStack);
		return method;
	}

	public static Method newMethod(String name) {
		Method method = newMethod();
		when(method.getName()).thenReturn(name);
		return method;
	}

	private static Method newMethod() {
		Method method = mock(Method.class);
		when(method.getClassInfo()).thenReturn(MOCK_CLASS_INFO);
		return method;
	}

}
