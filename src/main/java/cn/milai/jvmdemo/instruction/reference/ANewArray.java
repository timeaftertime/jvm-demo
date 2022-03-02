package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * anew_array
 * @author milai
 * @date 2022.01.19
 */
public class ANewArray implements Instruction {

	private int operand;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		operand = reader.readUint16();
	}

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		int length = stack.popInt();
		if (length < 0) {
			throw new NegativeArraySizeException(length + "");
		}
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassInfo eleType = pool.getClassRef(operand).resolvedClass();
		ClassInfoLoader loader = frame.getMethod().getClassInfo().getClassInfoLoader();
		ObjectRef ref = new ObjectRef(loader.load("[L" + eleType.getName() + ";"), length);
		stack.pushRef(ref);
	}

}
