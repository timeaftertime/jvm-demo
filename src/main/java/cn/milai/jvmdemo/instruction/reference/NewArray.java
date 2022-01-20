package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * new_array 指令
 * @author milai
 * @date 2022.01.19
 */
public class NewArray implements Instruction {

	private int operand;

	private static final int AT_BOOLEAN = 4;
	private static final int AT_CHAR = 5;
	private static final int AT_FLOAT = 6;
	private static final int AT_DOUBLE = 7;
	private static final int AT_BYTE = 8;
	private static final int AT_SHORT = 9;
	private static final int AT_INT = 10;
	private static final int AT_LONG = 11;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		this.operand = reader.readUint8();
	}

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		int length = stack.popInt();
		if (length < 0) {
			throw new NegativeArraySizeException(length + "");
		}
		ClassInfoLoader loader = frame.getMethod().getClassInfo().getClassInfoLoader();
		ObjectRef ref = new ObjectRef(loader.load(getTypeName(operand)), length);
		stack.pushRef(ref);
	}

	private static String getTypeName(int type) {
		switch (type) {
			case AT_BOOLEAN :
			case AT_BYTE :
				return arrayType(TypeDesc.BOOLEAN);
			case AT_CHAR :
				return arrayType(TypeDesc.CHAR);
			case AT_SHORT :
				return arrayType(TypeDesc.SHORT);
			case AT_INT :
				return arrayType(TypeDesc.INT);
			case AT_LONG :
				return arrayType(TypeDesc.LONG);
			case AT_FLOAT :
				return arrayType(TypeDesc.FLOAT);
			case AT_DOUBLE :
				return arrayType(TypeDesc.DOUBLE);
			default:
				throw new IllegalArgumentException(type + "");
		}
	}

	private static String arrayType(TypeDesc t) {
		return ClassConst.ARRAY_PREFIX + t.getValue();
	}

}
