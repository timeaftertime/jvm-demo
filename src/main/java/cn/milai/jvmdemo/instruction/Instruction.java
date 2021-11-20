package cn.milai.jvmdemo.instruction;

import java.io.IOException;

import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 字节码指令
 * @author milai
 * @date 2021.11.14
 */
public interface Instruction {

	/**
	 * 从 {@link BytecodeReader} 读取所需的操作数
	 * @param reader
	 * @throws IOException
	 */
	default void readOperands(BytecodeReader reader) throws IOException {
	}

	/**
	 * 在指定栈帧上执行当前指令
	 * @param frame
	 */
	void execute(Frame frame);

}