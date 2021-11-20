package cn.milai.jvmdemo.instruction.conversion;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * f2X 指令
 * @author milai
 * @date 2021.11.20
 */
public class F2XInstructions {

	public static class F2I implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt((int) stack.popFloat());
		}

	}

	public static class F2L implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong((long) stack.popFloat());
		}

	}

	public static class F2D implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushDouble(stack.popFloat());
		}

	}
}
