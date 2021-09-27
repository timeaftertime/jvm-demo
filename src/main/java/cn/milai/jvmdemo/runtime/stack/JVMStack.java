package cn.milai.jvmdemo.runtime.stack;

import java.util.Stack;

/**
 * Java 虚拟机栈
 * @author milai
 * @date 2021.09.26
 */
public class JVMStack extends Stack<Frame> {

	private static final long serialVersionUID = 1L;

	private int maxSize;

	public JVMStack(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public Frame push(Frame item) {
		if (size() == maxSize)
			throw new StackOverflowError();
		return super.push(item);
	}

	@Override
	public String toString() {
		return "JVMStack [maxSize=" + maxSize + ", Frames=" + super.toString() + "]";
	}

}
