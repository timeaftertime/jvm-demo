package cn.milai.jvmdemo.instruction.constant;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * ldc_X 指令
 * @author milai
 * @date 2021.11.14
 */
public class LdcInstructions {

	private static abstract class LDCInstruction implements Instruction {

		protected int operand;

		@Override
		public void readOperands(BytecodeReader reader) throws IOException {
			this.operand = readOperand(reader);
		}

		protected abstract int readOperand(BytecodeReader reader) throws IOException;
	}

	public static class LDC extends LDCInstruction {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		}

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			Object val = frame.getMethod().getClassInfo().getConstantPool().get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			// TODO 引用类型
			throw new RuntimeException("暂未实现");
		}

	}

	public static class LDC_W extends LDCInstruction {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint16();
		}

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			RTConstantPool pool = frame.getMethod().getClassInfo().getConstantPool();
			Object val = pool.get(operand);
			if (val instanceof Integer) {
				stack.pushInt((int) val);
				return;
			}
			if (val instanceof Float) {
				stack.pushFloat((float) val);
				return;
			}
			throw new RuntimeException("暂未实现");
		}

	}

	public static class LDC2_W extends LDCInstruction {

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint16();
		}

		@Override
		public void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
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
			throw new ClassFormatError();
		}

	}

}