package cn.milai.jvmdemo.instruction.math;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * Xor 指令
 * @author milai
 * @date 2021.11.20
 */
public class XorInstructions {

	public static class IXOR implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt(stack.popInt() ^ stack.popInt());
		}

	}

	public static class LXOR implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong(stack.popLong() ^ stack.popLong());
		}

	}
}
