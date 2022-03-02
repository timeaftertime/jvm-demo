package cn.milai.jvmdemo.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Method;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.LocalVarsTable;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

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

	public static Method newMethod(String className, String name, String descriptor) {
		Method method = mock(Method.class);
		when(method.getName()).thenReturn(name);
		when(method.getDescriptor()).thenReturn(descriptor);
		ClassInfo info = newClassInfo(className);
		when(method.getClassInfo()).thenReturn(info);
		return method;
	}

	public static ClassInfo newClassInfo(String name) {
		ClassInfo classInfo = mock(ClassInfo.class);
		when(classInfo.getName()).thenReturn(name);
		return classInfo;
	}

	private static Method newMethod() {
		Method method = mock(Method.class);
		when(method.getClassInfo()).thenReturn(MOCK_CLASS_INFO);
		when(method.getCodes()).thenReturn(new byte[0]);
		return method;
	}

	public static ClassInfo classInfoWithSlotCnt(int instanceSlotCnt) {
		ClassInfo c = mock(ClassInfo.class);
		when(c.getInstanceSlotCnt()).thenReturn(instanceSlotCnt);
		return c;
	}

	public static Frame newFrame(OperandStack s, LocalVarsTable t, Method m) {
		Frame frame = mock(Frame.class);
		when(frame.getOperandStack()).thenReturn(s);
		when(frame.getLocalVarsTable()).thenReturn(t);
		when(frame.getMethod()).thenReturn(m);
		return frame;
	}

}
