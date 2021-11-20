package cn.milai.jvmdemo.instruction.stack;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.slot.Slot;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * swap 指令
 * @author milai
 * @date 2021.11.20
 */
public class Swap implements Instruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		Slot slot1 = stack.popSlot();
		Slot slot2 = stack.popSlot();
		stack.pushSlot(slot1);
		stack.pushSlot(slot2);
	}

}
