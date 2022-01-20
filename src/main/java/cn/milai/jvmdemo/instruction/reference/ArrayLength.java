package cn.milai.jvmdemo.instruction.reference;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * array_length 指令
 * @author milai
 * @date 2022.01.19
 */
public class ArrayLength implements Instruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef ref = stack.popRef();
		if (ref == null) {
			throw new NullPointerException();
		}
		stack.pushInt(ref.length());
	}

}
