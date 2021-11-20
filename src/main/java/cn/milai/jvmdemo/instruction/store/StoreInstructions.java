package cn.milai.jvmdemo.instruction.store;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * Xstore_X 指令
 * @author milai
 * @date 2021.11.14
 */
public class StoreInstructions {

	public static class ISTORE_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(0, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(1, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(2, frame.getOperandStack().popInt());
		}

	}

	public static class ISTORE_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(3, frame.getOperandStack().popInt());
		}

	}

	public static class LSTORE_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(0, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(1, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(2, frame.getOperandStack().popLong());
		}

	}

	public static class LSTORE_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(3, frame.getOperandStack().popLong());
		}

	}

	public static class FSTORE_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(0, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(1, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(2, frame.getOperandStack().popFloat());
		}

	}

	public static class FSTORE_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(3, frame.getOperandStack().popFloat());
		}

	}

	public static class DSTORE_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(0, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(1, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(2, frame.getOperandStack().popDouble());
		}

	}

	public static class DSTORE_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(3, frame.getOperandStack().popDouble());
		}

	}

	public static class ASTORE_0 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(0, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_1 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(1, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_2 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(2, frame.getOperandStack().popRef());
		}

	}

	public static class ASTORE_3 implements Instruction {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(3, frame.getOperandStack().popRef());
		}

	}

	private static abstract class XSTORE implements Instruction {
		protected int operand;

		@Override
		public void readOperands(BytecodeReader reader) throws IOException {
			operand = reader.readUint8();
		}
	}

	public static class ISTORE extends XSTORE {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setInt(operand, frame.getOperandStack().popInt());
		}

	}

	public static class LSTORE extends XSTORE {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setLong(operand, frame.getOperandStack().popLong());
		}

	}

	public static class FSTORE extends XSTORE {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setFloat(operand, frame.getOperandStack().popFloat());
		}

	}

	public static class DSTORE extends XSTORE {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setDouble(operand, frame.getOperandStack().popDouble());
		}

	}

	public static class ASTORE extends XSTORE {

		@Override
		public void execute(Frame frame) {
			frame.getLocalVarsTable().setRef(operand, frame.getOperandStack().popRef());
		}

	}

}