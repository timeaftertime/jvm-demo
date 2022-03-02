package cn.milai.jvmdemo.instruction.natives;

import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Method;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.StackTrace;

/**
 * {@link Throwable#fillInStackTrace() }实现
 * @author milai
 * @date 2022.03.02
 */
public class ThrowableFillInStackTrace implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		ObjectRef ex = frame.getLocalVarsTable().getRef(0);
		frame.getOperandStack().pushRef(ex);
		ex.setStackTraces(createStackTrace(ex, frame.getThreadSpace()));
	}

	private StackTrace[] createStackTrace(ObjectRef ex, ThreadSpace thread) {
		// 此时 JVM 栈从下到上分别是 其他栈帧、异常类构造方法、fillInStackTrace()、fillInStackTrace(int)
		// 异常堆栈应该排除异常类构造方法和 fill 这个方法 
		int skip = distanceToObject(ex.getClassInfo()) + 2;
		Frame[] frames = thread.getFrames();
		int len = frames.length - skip;
		StackTrace[] traces = new StackTrace[len];
		for (int i = 0; i < len; i++) {
			Method method = frames[len - 1 - i].getMethod();
			ClassInfo classInfo = method.getClassInfo();
			traces[i] = new StackTrace(
				classInfo.getSourceFileName(), classInfo.getName(), method.getName(),
				method.getLineNumberResolver().resolve(frames[i].getCurrentPC() - 1)
			);
		}
		return traces;
	}

	private int distanceToObject(ClassInfo ex) {
		int cnt = 0;
		ClassInfo nowClass = ex;
		while (nowClass.getSuperClassInfo() != null) {
			nowClass = nowClass.getSuperClassInfo();
			cnt++;
		}
		return cnt;
	}

}
