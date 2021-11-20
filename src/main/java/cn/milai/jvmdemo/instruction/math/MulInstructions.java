package cn.milai.jvmdemo.instruction.math;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * Xmul 指令
 * @author milai
 * @date 2021.11.20
 */
public class MulInstructions {

	public static class IMUL implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			stack.pushInt(op2 * op1);
		}

	}

	public static class LMUL implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			long op1 = stack.popLong();
			long op2 = stack.popLong();
			stack.pushLong(op2 * op1);
		}

	}

	public static class FMUL implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			float op1 = stack.popFloat();
			float op2 = stack.popFloat();
			stack.pushFloat(op2 * op1);
		}

	}

	public static class DMUL implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			double op1 = stack.popDouble();
			double op2 = stack.popDouble();
			stack.pushDouble(op2 * op1);
		}

	}

}
