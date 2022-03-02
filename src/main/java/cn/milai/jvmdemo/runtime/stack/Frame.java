package cn.milai.jvmdemo.runtime.stack;

import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.classes.Method;

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

	/**
	 * 当前正在执行的指令的开始地址
	 */
	private int currentPC;

	/**
	 * 返回地址
	 */
	private int returnPC;

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

	public int getCurrentPC() { return currentPC; }

	public void setCurrentPC(int currentPC) { this.currentPC = currentPC; }

	public int getReturnPC() { return returnPC; }

	public void setReturnPC(int returnPC) { this.returnPC = returnPC; }

}
