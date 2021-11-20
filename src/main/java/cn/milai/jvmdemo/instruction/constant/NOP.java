package cn.milai.jvmdemo.instruction.constant;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * nop(0x00) 指令
 * @author milai
 * @date 2021.11.14
 */
public class NOP implements Instruction {

	@Override
	public void execute(Frame frame) {
	}

}
