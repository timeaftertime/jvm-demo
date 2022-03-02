package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.ref.ClassRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * checkcast 指令
 * @author milai
 * @date 2022.01.19
 */
public class CheckCast implements Instruction {

	private int operand;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		operand = reader.readUint16();
	}

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef objectRef = stack.popRef();
		stack.pushRef(objectRef);
		if (objectRef == null) {
			return;
		}
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassRef classRef = pool.getClassRef(operand);
		ClassInfo classInfo = objectRef.getClassInfo();
		ClassInfo targetClass = classRef.resolvedClass();
		if (classInfo != targetClass && !classInfo.isSubClassOf(targetClass)) {
			throw new ClassCastException();
		}
	}

}
