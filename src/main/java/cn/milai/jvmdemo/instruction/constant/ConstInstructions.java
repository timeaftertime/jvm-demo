package cn.milai.jvmdemo.instruction.constant;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * Xconst_X 相关指令
 * @author milai
 * @date 2021.11.14
 */
public class ConstInstructions {

	public static class ACONST_NULL implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(null);
		}

	}

	public static class ICONST_M1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(-1);
		}

	}

	public static class ICONST_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(0);
		}

	}

	public static class ICONST_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(1);
		}

	}

	public static class ICONST_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(2);
		}

	}

	public static class ICONST_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(3);
		}

	}

	public static class ICONST_4 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(4);
		}

	}

	public static class ICONST_5 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(5);
		}

	}

	public static class LCONST_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(0);
		}

	}

	public static class LCONST_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(1);
		}

	}

	public static class FCONST_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(0f);
		}

	}

	public static class FCONST_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(1f);
		}

	}

	public static class FCONST_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(2f);
		}

	}

	public static class DCONST_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(0);
		}

	}

	public static class DCONST_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(1);
		}

	}

}
