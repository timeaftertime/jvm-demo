package cn.milai.jvmdemo.instruction.stack;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * popX 指令
 * @author milai
 * @date 2021.11.20
 */
public class PopInstructions {

	public static class POP implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().popSlot();
		}

	}

	public static class POP2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().popSlot();
			frame.getOperandStack().popSlot();
		}

	}

}
