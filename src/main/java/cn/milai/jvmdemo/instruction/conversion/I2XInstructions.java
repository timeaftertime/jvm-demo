package cn.milai.jvmdemo.instruction.conversion;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * i2X 指令
 * @author milai
 * @date 2021.11.20
 */
public class I2XInstructions {

	public static class I2L implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushLong(stack.popInt());
		}

	}

	public static class I2F implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushFloat(stack.popInt());
		}

	}

	public static class I2D implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			stack.pushDouble(stack.popInt());
		}

	}

}
