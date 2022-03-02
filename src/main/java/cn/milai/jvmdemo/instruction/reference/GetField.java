package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.FieldResolver;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Field;
import cn.milai.jvmdemo.runtime.ref.FieldRef;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * getfield
 * @author milai
 * @date 2022.01.20
 */
public class GetField implements Instruction {

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
		Field field = FieldResolver.resolve(fieldRef, false);
		if (field == null) {
			throw new NoSuchFieldError(fieldRef.toString());
		}
		ObjectRef ref = frame.getOperandStack().popRef();
		if (ref == null) {
			throw new NullPointerException();
		}
		switch (TypeDesc.of(field.getDescriptor())) {
			case BOOLEAN :
			case BYTE :
			case CHAR :
			case SHORT :
			case INT :
				frame.getOperandStack().pushInt(ref.getFields().getInt(field.getSlotId()));
				break;
			case LONG :
				frame.getOperandStack().pushLong(ref.getFields().getLong(field.getSlotId()));
				break;
			case FLOAT :
				frame.getOperandStack().pushFloat(ref.getFields().getFloat(field.getSlotId()));
				break;
			case DOUBLE :
				frame.getOperandStack().pushDouble(ref.getFields().getDouble(field.getSlotId()));
				break;
			case REF :
			case ARRAY :
				frame.getOperandStack().pushRef(ref.getFields().getRef(field.getSlotId()));
				break;
			default:
				throw new RuntimeException("非法类型描述符：" + field.getDescriptor());
		}
	}

}
