package cn.milai.jvmdemo.instruction.stack;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.slot.Slot;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * dupX 指令
 * @author milai
 * @date 2021.11.20
 */
public class DupInstructions {

	public static class DUP implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot = stack.popSlot();
			stack.pushSlot(slot);
			stack.pushSlot(slot);
		}

	}

	public static class DUP_X1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot = stack.popSlot();
			Slot offset1 = stack.popSlot();
			stack.pushSlot(slot);
			stack.pushSlot(offset1);
			stack.pushSlot(slot);
		}

	}

	public static class DUP_X2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot = stack.popSlot();
			Slot offset1 = stack.popSlot();
			Slot offset2 = stack.popSlot();
			stack.pushSlot(slot);
			stack.pushSlot(offset2);
			stack.pushSlot(offset1);
			stack.pushSlot(slot);
		}

	}

	public static class DUP2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot2 = stack.popSlot();
			Slot slot1 = stack.popSlot();
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
		}

	}

	public static class DUP2_X1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot2 = stack.popSlot();
			Slot slot1 = stack.popSlot();
			Slot offset1 = stack.popSlot();
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
			stack.pushSlot(offset1);
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
		}

	}

	public static class DUP2_X2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Slot slot2 = stack.popSlot();
			Slot slot1 = stack.popSlot();
			Slot offset1 = stack.popSlot();
			Slot offset2 = stack.popSlot();
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
			stack.pushSlot(offset2);
			stack.pushSlot(offset1);
			stack.pushSlot(slot1);
			stack.pushSlot(slot2);
		}

	}

}
