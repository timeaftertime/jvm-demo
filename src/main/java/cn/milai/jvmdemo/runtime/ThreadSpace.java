package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.JVMStack;

/**
 * 线程空间
 * @author milai
 * @date 2021.09.27
 */
public class ThreadSpace {

	private int pc;

	private JVMStack stack;
	private static final int DEFAULT_MAX_STACK_SIZE = 100;

	public ThreadSpace() {
		this.pc = 0;
		this.stack = new JVMStack(DEFAULT_MAX_STACK_SIZE);
	}

	public void pushFrame(Method method) {
		stack.push(new Frame(this, method));
	}

	public Frame popFrame() {
		return stack.pop();
	}

	public Frame currentFrame() {
		return stack.peek();
	}

	public int getPC() { return pc; }

	public void setPC(int pc) { this.pc = pc; }

	public void invokeMethod(Method method) {
		pushFrame(method);
	}

	public boolean finished() {
		return stack.isEmpty();
	}

	@Override
	public String toString() {
		return "ThreadSpace [pc=" + pc + "]";
	}

	public void clearStack() {
		stack.clear();
	}

	public Frame[] getFrames() { return stack.toArray(new Frame[0]); }

}
