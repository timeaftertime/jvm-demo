package cn.milai.jvmdemo.instruction.load;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * Xaload 系列指令
 * @author milai
 * @date 2022.01.06
 */
public class ALoadInstructions {

	public abstract static class XALOAD implements Instruction {

		@Override
		public final void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			int index = stack.popInt();
			ObjectRef ref = stack.popRef();
			check(ref, index);
			load(stack, index, ref);
		}

		protected abstract void load(OperandStack stack, int index, ObjectRef ref);

	}

	private static void check(ObjectRef ref, int index) {
		if (ref == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > ref.length()) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	public static class BALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushInt(ref.getElements().getInt(index));
		}

	}

	public static class CALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushInt(ref.getElements().getInt(index));
		}

	}

	public static class SALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushInt(ref.getElements().getInt(index));
		}

	}

	public static class IALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushInt(ref.getElements().getInt(index));
		}

	}

	public static class FALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushFloat(ref.getElements().getFloat(index));
		}

	}

	public static class LALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushLong(ref.getElements().getLong(index));
		}

	}

	public static class DALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushDouble(ref.getElements().getDouble(index));
		}

	}

	public static class AALOAD extends XALOAD {

		@Override
		protected void load(OperandStack stack, int index, ObjectRef ref) {
			stack.pushRef(ref.getElements().getRef(index));
		}

	}

}
