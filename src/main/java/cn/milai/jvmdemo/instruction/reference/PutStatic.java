package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.MethodConst;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.FieldResolver;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Field;
import cn.milai.jvmdemo.runtime.ref.FieldRef;
import cn.milai.jvmdemo.runtime.slot.MemberSlots;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * putfield
 */
public class PutStatic implements Instruction {

	private int operand;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		operand = reader.readUint16();
	}

	@Override
	public void execute(Frame frame) {
		ClassInfo currentClass = frame.getMethod().getClassInfo();
		RTConstantPool pool = currentClass.getConstantPool();
		FieldRef fieldRef = pool.getFieldRef(operand);
		Field field = FieldResolver.resolve(fieldRef, true);
		if (field.isFinal()) {
			if (currentClass != field.getClassInfo() || MethodConst.isInit(frame.getMethod())) {
				throw new IllegalAccessError();
			}
		}
		MemberSlots staticVars = field.getClassInfo().getStaticSlots();
		switch (TypeDesc.of(field.getDescriptor())) {
			case BOOLEAN :
			case BYTE :
			case CHAR :
			case SHORT :
			case INT :
				staticVars.setInt(field.getSlotId(), frame.getOperandStack().popInt());
				break;
			case LONG :
				staticVars.setLong(field.getSlotId(), frame.getOperandStack().popLong());
				break;
			case FLOAT :
				staticVars.setFloat(field.getSlotId(), frame.getOperandStack().popFloat());
				break;
			case DOUBLE :
				staticVars.setDouble(field.getSlotId(), frame.getOperandStack().popDouble());
				break;
			case REF :
			case ARRAY :
				staticVars.setRef(field.getSlotId(), frame.getOperandStack().popRef());
				break;
			default:
				throw new IllegalArgumentException("非法描述符：" + field.getDescriptor());
		}
	}

}
