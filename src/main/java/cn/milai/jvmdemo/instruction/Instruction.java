package cn.milai.jvmdemo.instruction;

import java.io.IOException;

import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 字节码指令
 * @author milai
 * @date 2021.11.14
 */
public interface Instruction {

	void readOperands(BytecodeReader reader) throws IOException;

	void execute(Frame frame);

}