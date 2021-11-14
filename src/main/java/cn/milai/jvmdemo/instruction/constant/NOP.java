package cn.milai.jvmdemo.instruction.constant;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * nop(0x00) 指令
 * @author milai
 * @date 2021.11.14
 */
public class NOP implements Instruction {

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
	}

	@Override
	public void execute(Frame frame) {
	}

}
