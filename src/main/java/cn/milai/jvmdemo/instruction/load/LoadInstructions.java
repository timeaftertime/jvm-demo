package cn.milai.jvmdemo.instruction.load;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * Xload_X 指令
 * @author milai
 * @date 2021.11.14
 */
public class LoadInstructions {

	public static class ILOAD_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(0));
		}

	}

	public static class ILOAD_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(1));
		}

	}

	public static class ILOAD_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(2));
		}

	}

	public static class ILOAD_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(3));
		}

	}

	public static class LLOAD_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(0));
		}

	}

	public static class LLOAD_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(1));
		}

	}

	public static class LLOAD_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(2));
		}

	}

	public static class LLOAD_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(3));
		}

	}

	public static class FLOAD_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(0));
		}

	}

	public static class FLOAD_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(1));
		}

	}

	public static class FLOAD_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(2));
		}

	}

	public static class FLOAD_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(3));
		}

	}

	public static class DLOAD_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(0));
		}

	}

	public static class DLOAD_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(1));
		}

	}

	public static class DLOAD_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(2));
		}

	}

	public static class DLOAD_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(3));
		}

	}

	public static class ALOAD_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(0));
		}

	}

	public static class ALOAD_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(1));
		}

	}

	public static class ALOAD_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(2));
		}

	}

	public static class ALOAD_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(3));
		}

	}

	private static abstract class XLOAD implements Instruction {
		protected int operand;

		@Override
		public final void readOperands(BytecodeReader reader) throws IOException {
			operand = readOperand(reader);
		}

		protected abstract int readOperand(BytecodeReader reader) throws IOException;
	}

	public static class ILOAD extends XLOAD {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(frame.getLocalVarsTable().getInt(operand));
		}

		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		};

	}

	public static class LLOAD extends XLOAD {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushLong(frame.getLocalVarsTable().getLong(operand));
		}

		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		};

	}

	public static class FLOAD extends XLOAD {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushFloat(frame.getLocalVarsTable().getFloat(operand));
		}

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		}

	}

	public static class DLOAD extends XLOAD {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushDouble(frame.getLocalVarsTable().getDouble(operand));
		}

		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		};

	}

	public static class ALOAD extends XLOAD {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushRef(frame.getLocalVarsTable().getRef(operand));
		}

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		}

	}
}
