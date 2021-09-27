package cn.milai.jvmdemo.runtime.stack;

import cn.milai.jvmdemo.runtime.Method;
import cn.milai.jvmdemo.runtime.ThreadSpace;

/**
 * 栈帧
 * @author milai
 * @date 2021.09.23
 */
public class Frame {

	private ThreadSpace threadSpace;
	private Method method;

	private LocalVarsTable localVars;
	private OperandStack operands;

	public Frame(ThreadSpace threadSpace, Method method) {
		this.threadSpace = threadSpace;
		this.method = method;
		localVars = new LocalVarsTable(method.getMaxLocal());
		operands = new OperandStack(method.getMaxStack());
	}

	public Method getMethod() { return method; }

	public ThreadSpace getThreadSpace() { return threadSpace; }

	public LocalVarsTable getLocalVarsTable() { return localVars; }

	public OperandStack getOperandStack() { return operands; }

	@Override
	public String toString() {
		return "Frame [method=" + method + "]";
	}

}
