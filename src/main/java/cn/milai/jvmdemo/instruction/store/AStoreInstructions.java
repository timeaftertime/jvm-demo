package cn.milai.jvmdemo.instruction.store;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * Xastore 系列指令
 * @author milai
 * @date 2022.01.06
 */
public class AStoreInstructions {

	public abstract static class XASTORE<T> implements Instruction {

		@Override
		public final void execute(Frame frame) {
			OperandStack stack = frame.getOperandStack();
			T v = popValue(stack);
			int index = stack.popInt();
			ObjectRef array = stack.popRef();
			check(array, index);
			store(stack, array, index, v);
		}

		protected abstract T popValue(OperandStack stack);

		protected abstract void store(OperandStack stack, ObjectRef array, int index, T v);

	}

	private static void check(ObjectRef ref, int index) {
		if (ref == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > ref.length()) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	public static class BASTORE extends XASTORE<Integer> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Integer v) {
			array.getElements().setInt(index, v);
		};

		@Override
		protected Integer popValue(OperandStack stack) {
			return stack.popInt();
		}

	}

	public static class CASTORE extends XASTORE<Integer> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Integer v) {
			array.getElements().setInt(index, v);
		};

		@Override
		protected Integer popValue(OperandStack stack) {
			return stack.popInt();
		}
	}

	public static class SASTORE extends XASTORE<Integer> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Integer v) {
			array.getElements().setInt(index, v);
		};

		@Override
		protected Integer popValue(OperandStack stack) {
			return stack.popInt();
		}

	}

	public static class IASTORE extends XASTORE<Integer> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Integer v) {
			array.getElements().setInt(index, v);
		};

		@Override
		protected Integer popValue(OperandStack stack) {
			return stack.popInt();
		}

	}

	public static class FASTORE extends XASTORE<Float> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Float v) {
			array.getElements().setFloat(index, v);
		};

		@Override
		protected Float popValue(OperandStack stack) {
			return stack.popFloat();
		}

	}

	public static class LASTORE extends XASTORE<Long> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Long v) {
			array.getElements().setLong(index, v);
		};

		@Override
		protected Long popValue(OperandStack stack) {
			return stack.popLong();
		}

	}

	public static class DASTORE extends XASTORE<Double> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, Double v) {
			array.getElements().setDouble(index, v);
		}

		@Override
		protected Double popValue(OperandStack stack) {
			return stack.popDouble();
		};

	}

	public static class AASTORE extends XASTORE<ObjectRef> {

		@Override
		protected void store(OperandStack stack, ObjectRef array, int index, ObjectRef v) {
			array.getElements().setRef(index, v);
		}

		@Override
		protected ObjectRef popValue(OperandStack stack) {
			return stack.popRef();
		};
	}

}
