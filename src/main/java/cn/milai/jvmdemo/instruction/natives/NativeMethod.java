package cn.milai.jvmdemo.instruction.natives;

import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 本地方法
 * @author milai
 * @date 2022.02.13
 */
public interface NativeMethod {

	NativeMethod EMPTY = frame -> {};

	/**
	 * 执行本地方法
	 * @param frame 方法对应的栈帧
	 */
	void invoke(Frame frame);

}
