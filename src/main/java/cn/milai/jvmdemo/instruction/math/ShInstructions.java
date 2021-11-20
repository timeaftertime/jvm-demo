package cn.milai.jvmdemo.instruction.math;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * Xshl 指令
 * @author milai
 * @date 2021.11.20
 */
public class ShInstructions {

	public static class ISHL implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			op1 &= 0x1f;
			stack.pushInt(op2 << op1);
		}

	}

	public static class ISHR implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			op1 &= 0x1f;
			stack.pushInt(op2 >> op1);
		}

	}

	public static class IUSHR implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			int op2 = stack.popInt();
			op1 &= 0x1f;
			stack.pushInt(op2 >>> op1);
		}

	}

	public static class LSHL implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			long op2 = stack.popLong();
			op1 &= 0x3f;
			stack.pushLong(op2 << op1);
		}

	}

	public static class LSHR implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			long op2 = stack.popLong();
			op1 &= 0x3f;
			stack.pushLong(op2 >> op1);
		}

	}

	public static class LUSHR implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int op1 = stack.popInt();
			long op2 = stack.popLong();
			op1 &= 0x3f;
			stack.pushLong(op2 >>> op1);
		}

	}

}
