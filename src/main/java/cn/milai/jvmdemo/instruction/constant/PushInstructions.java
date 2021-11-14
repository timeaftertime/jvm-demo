package cn.milai.jvmdemo.instruction.constant;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * XXpush 指令
 * @author milai
 * @date 2021.11.14
 */
public class PushInstructions {

	private static abstract class PUSH implements Instruction {

		protected int operand;

		@Override
		public void readOperands(BytecodeReader reader) throws IOException {
			operand = readOperand(reader);
		}

		protected abstract int readOperand(BytecodeReader reader) throws IOException;

	}

	public static class SIPUSH extends PUSH {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(operand);
		}

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint16();
		}

	}

	public static class BIPUSH extends PUSH {

		@Override
		public void execute(Frame frame) {
			frame.getOperandStack().pushInt(operand);
		}

		@Override
		protected int readOperand(BytecodeReader reader) throws IOException {
			return reader.readUint8();
		}

	}

}