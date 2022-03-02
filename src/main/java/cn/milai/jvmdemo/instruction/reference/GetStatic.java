package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.FieldResolver;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.Field;
import cn.milai.jvmdemo.runtime.ref.FieldRef;
import cn.milai.jvmdemo.runtime.slot.MemberSlots;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * getstatic
 * @author milai
 * @date 2022.01.20
 */
public class GetStatic implements Instruction {

	private int operand;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		operand = reader.readUint16();
	}

	@Override
	public void execute(Frame frame) {
		RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
		FieldRef fieldRef = pool.getFieldRef(operand);
		Field field = FieldResolver.resolve(fieldRef, true);
		MemberSlots staticVars = field.getClassInfo().getStaticSlots();
		switch (TypeDesc.of(field.getDescriptor())) {
			case BOOLEAN :
			case BYTE :
			case CHAR :
			case SHORT :
			case INT :
				frame.getOperandStack().pushInt(staticVars.getInt(field.getSlotId()));
				break;
			case LONG :
				frame.getOperandStack().pushLong(staticVars.getLong(field.getSlotId()));
				break;
			case FLOAT :
				frame.getOperandStack().pushFloat(staticVars.getFloat(field.getSlotId()));
				break;
			case DOUBLE :
				frame.getOperandStack().pushDouble(staticVars.getDouble(field.getSlotId()));
				break;
			case REF :
			case ARRAY :
				frame.getOperandStack().pushRef(staticVars.getRef(field.getSlotId()));
				break;
			default:
				throw new RuntimeException("非法类型描述符：" + field.getDescriptor());
		}
	}

}
