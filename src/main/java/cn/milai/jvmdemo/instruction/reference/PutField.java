package cn.milai.jvmdemo.instruction.reference;

import java.io.IOException;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.MethodConst;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.Field;
import cn.milai.jvmdemo.runtime.FieldRef;
import cn.milai.jvmdemo.runtime.FieldResolver;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * putfield
 * @author milai
 * @date 2022.01.20
 */
public class PutField implements Instruction {

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
		if (field.isFinal()) {
			if (currentClass != field.getClassInfo() || MethodConst.isInit(frame.getMethod())) {
				throw new IllegalAccessError();
			}
		}
		switch (TypeDesc.of(field.getDescriptor())) {
			case BOOLEAN :
			case BYTE :
			case CHAR :
			case SHORT :
			case INT : {
				int val = frame.getOperandStack().popInt();
				ObjectRef ref = frame.getOperandStack().popRef();
				if (ref == null)
					throw new NullPointerException();
				ref.getFields().setInt(field.getSlotId(), val);
				break;
			}
			case LONG : {
				long val = frame.getOperandStack().popLong();
				ObjectRef ref = frame.getOperandStack().popRef();
				if (ref == null)
					throw new NullPointerException();
				ref.getFields().setLong(field.getSlotId(), val);
				break;
			}
			case FLOAT : {
				float val = frame.getOperandStack().popFloat();
				ObjectRef ref = frame.getOperandStack().popRef();
				if (ref == null)
					throw new NullPointerException();
				ref.getFields().setFloat(field.getSlotId(), val);
				break;
			}
			case DOUBLE : {
				double val = frame.getOperandStack().popDouble();
				ObjectRef ref = frame.getOperandStack().popRef();
				if (ref == null)
					throw new NullPointerException();
				ref.getFields().setDouble(field.getSlotId(), val);
				break;
			}
			case REF :
			case ARRAY : {
				ObjectRef val = frame.getOperandStack().popRef();
				ObjectRef ref = frame.getOperandStack().popRef();
				if (ref == null)
					throw new NullPointerException();
				ref.getFields().setRef(field.getSlotId(), val);
				break;
			}
			default:
				throw new IllegalArgumentException("非法描述符：" + field.getDescriptor());
		}
	}

}
