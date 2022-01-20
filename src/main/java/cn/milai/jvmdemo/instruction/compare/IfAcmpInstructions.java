package cn.milai.jvmdemo.instruction.compare;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * if_acmpX 指令
 * @author milai
 * @date 2022.01.19
 */
public class IfAcmpInstructions {

	public static abstract class IF_ACMPX extends BranchInstruction {

		@Override
		protected boolean fitCondition(Frame frame) {
			return fitCondition(frame.getOperandStack().popRef(), frame.getOperandStack().popRef());
		}

		protected abstract boolean fitCondition(ObjectRef op1, ObjectRef op2);

	}

	public static class IF_ACMPEQ extends IF_ACMPX {

		@Override
		protected boolean fitCondition(ObjectRef op1, ObjectRef op2) {
			return op2 == op1;
		}

	}

	public static class IF_ACMPNE extends IF_ACMPX {

		@Override
		protected boolean fitCondition(ObjectRef op1, ObjectRef op2) {
			return op2 != op1;
		}

	}

}
