package cn.milai.jvmdemo.instruction.conversion;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * l2X 指令
 * @author milai
 * @date 2021.11.20
 */
public class L2XInstructions {

	public static class L2I implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt((int) stack.popLong());
		}

	}

	public static class L2F implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushFloat(stack.popLong());
		}

	}

	public static class L2D implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushDouble(stack.popLong());
		}

	}

}
