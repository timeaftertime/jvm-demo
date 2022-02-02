package cn.milai.jvmdemo.instruction.extended;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * multianewarray 指令
 * @author milai
 * @date 2022.02.01
 */
public class MutilAnewArray implements Instruction {

	private int index;
	private int dimension;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		index = reader.readUint16();
		dimension = reader.readUint8();
	}

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		OperandStack stack = frame.getOperandStack();
		ClassInfo elementType;
		ObjectRef array;
		elementType = pool.getClassRef(index).resolvedClass();
		int[] dimensions = popAndCheckDimension(stack);
		array = createMutilArray(dimensions, 0, elementType);
		stack.pushRef(array);
	}

	private ObjectRef createMutilArray(int[] dimensions, int index, ClassInfo type) {
		ObjectRef array = new ObjectRef(type, dimensions[index]);
		if (index < dimensions.length - 1) {
			for (int i = 0; i < dimensions[index]; i++) {
				array.getElements().setRef(i, createMutilArray(dimensions, index + 1, type.element()));
			}
		}
		return array;
	}

	private int[] popAndCheckDimension(OperandStack stack) {
		int[] dimensions = new int[dimension];
		for (int i = dimension - 1; i >= 0; i--) {
			dimensions[i] = stack.popInt();
			if (dimensions[i] < 0) {
				throw new NegativeArraySizeException();
			}
		}
		return dimensions;
	}

}
