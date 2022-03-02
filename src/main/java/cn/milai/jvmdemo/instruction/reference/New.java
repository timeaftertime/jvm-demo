package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.ref.ClassRef;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * new 指令
 * @author milai
 * @date 2021.12.25
 */
public class New implements Instruction {

	private int operand;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		operand = reader.readUint16();
	}

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		ClassRef ref = pool.getClassRef(operand);
		ClassInfo classInfo = ref.resolvedClass();
		if (!classInfo.isInitialized()) {
			frame.getThreadSpace().setPC(frame.getCurrentPC());
			classInfo.init(frame.getThreadSpace());
			return;
		}
		if (classInfo.isAbstract() || classInfo.isInterface()) {
			throw new InstantiationError(classInfo.getName());
		}
		frame.getOperandStack().pushRef(new ObjectRef(classInfo));
	}

}
