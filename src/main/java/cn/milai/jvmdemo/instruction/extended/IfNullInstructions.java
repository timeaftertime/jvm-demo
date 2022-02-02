package cn.milai.jvmdemo.instruction.extended;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * ifXnull 指令
 * @author milai
 * @date 2022.02.01
 */
public class IfNullInstructions {

	public static abstract class IFXNULL extends BranchInstruction {

		@Override
		protected boolean fitCondition(Frame frame) {
			return fitCondition(frame.getOperandStack().popRef());
		}

		protected abstract boolean fitCondition(Object ref);
	}

	public static class IfNull extends IFXNULL {

		@Override
		protected boolean fitCondition(Object ref) {
			return ref == null;
		}

	}

	public static class IfNonNull extends IFXNULL {

		@Override
		protected boolean fitCondition(Object ref) {
			return ref != null;
		}

	}

}
