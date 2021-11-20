package cn.milai.jvmdemo.instruction.conversion;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * d2X 指令
 * @author milai
 * @date 2021.11.20
 */
public class D2XInstructions {

	public static class D2I implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushInt((int) stack.popDouble());
		}

	}

	public static class D2L implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong((long) stack.popDouble());
		}

	}

	public static class D2F implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushFloat((float) stack.popDouble());
		}

	}
}
