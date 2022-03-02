package cn.milai.jvmdemo.instruction.constant;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ClassPool;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.StringPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.ref.ClassRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * ldc_X 指令
 * @author milai
 * @date 2021.11.14
 */
public class LdcInstructions {

	private static abstract class LDCX implements Instruction {

		protected int operand;

		@Override
		public void readOperands(BytecodeReader reader) throws IOException {
			this.operand = readOperand(reader);
		}

		protected abstract int readOperand(BytecodeReader reader) throws IOException;
	}

	public static class LDC extends LDCX {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		}

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			ClassInfo classInfo = frame.getMethod().getClassInfo();
			Object val = frame.getMethod().getClassInfo().getConstantPool().get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			if (val instanceof String) {
				stack.pushRef(StringPool.getString(classInfo.getClassInfoLoader(), (String) val));
				return;
			}
			if (val instanceof ClassRef) {
				stack.pushRef(ClassPool.getRef(((ClassRef) val).resolvedClass()));
				return;
			}
			throw new RuntimeException("尚未实现");
		}

	}

	public static class LDC_W extends LDCX {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint16();
		}

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			ClassInfo classInfo = frame.getMethod().getClassInfo();
			RTConstantPool pool = classInfo.getConstantPool();
			Object val = pool.get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			if (val instanceof String) {
				stack.pushRef(StringPool.getString(classInfo.getClassInfoLoader(), (String) val));
				return;
			}
			if (val instanceof ClassRef) {
				stack.pushRef(ClassPool.getRef(((ClassRef) val).resolvedClass()));
				return;
			}
			throw new RuntimeException("尚未实现");
		}

	}

	public static class LDC2_W extends LDCX {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint16();
		}

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			ClassInfo classInfo = frame.getMethod().getClassInfo();
			RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
			Object val = pool.get(operand);
			if (val instanceof Long) {
				stack.pushLong((long) val);
				return;
			}
			if (val instanceof Double) {
				stack.pushDouble((double) val);
				return;
			}
			if (val instanceof String) {
				stack.pushRef(StringPool.getString(classInfo.getClassInfoLoader(), (String) val));
				return;
			}
			if (val instanceof ClassRef) {
				stack.pushRef(ClassPool.getRef(((ClassRef) val).resolvedClass()));
				return;
			}
			throw new RuntimeException("尚未实现");
		}

	}

}