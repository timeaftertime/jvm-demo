package cn.milai.jvmdemo.instruction.compare;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * Xcmp 指令
 * @author milai
 * @date 2021.12.02
 */
public class CmpInstructions {

	private static abstract class CMP implements Instruction {
		protected void pushGreater(OperandStack s) {
			s.pushInt(1);
		}

		protected void pushLittle(OperandStack s) {
			s.pushInt(-1);
		}

		protected void pushEquals(OperandStack s) {
			s.pushInt(0);
		}
	}

	public static class LCMP extends CMP {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			long op1 = stack.popLong();
			long op2 = stack.popLong();
			if (op2 == op1) {
				pushEquals(stack);
			} else if (op2 > op1) {
				pushGreater(stack);
			} else {
				pushLittle(stack);
			}
		}

	}

	public static class FCMPG extends CMP {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			float op1 = stack.popFloat();
			float op2 = stack.popFloat();
			if (op2 == op1) {
				pushEquals(stack);
			} else if (op2 > op1) {
				pushGreater(stack);
			} else if (op2 < op1) {
				pushLittle(stack);
			} else {
				pushGreater(stack);
			}
		}

	}

	public static class FCMPL extends CMP {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			float op1 = stack.popFloat();
			float op2 = stack.popFloat();
			if (op2 == op1) {
				pushEquals(stack);
			} else if (op2 > op1) {
				pushGreater(stack);
			} else if (op2 < op1) {
				pushLittle(stack);
			} else {
				pushLittle(stack);
			}
		}

	}

	public static class DCMPG extends CMP {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			double op1 = stack.popDouble();
			double op2 = stack.popDouble();
			if (op2 == op1) {
				pushEquals(stack);
			} else if (op2 > op1) {
				pushGreater(stack);
			} else if (op2 < op1) {
				pushLittle(stack);
			} else {
				pushGreater(stack);
			}
		}

	}

	public static class DCMPL extends CMP {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			double op1 = stack.popDouble();
			double op2 = stack.popDouble();
			if (op2 == op1) {
				pushEquals(stack);
			} else if (op2 > op1) {
				pushGreater(stack);
			} else if (op2 < op1) {
				pushLittle(stack);
			} else {
				pushLittle(stack);
			}
		}

	}

}
