package cn.milai.jvmdemo.instruction.compare;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * ifcmpX 指令
 * @author milai
 * @date 2021.12.02
 */
public class IfIcmpInstructions {

	public static abstract class IF_ICMPX extends BranchInstruction {

		@Override
		protected boolean fitCondition(Frame frame) {
			return fitCondition(frame.getOperandStack().popInt(), frame.getOperandStack().popInt());
		}

		protected abstract boolean fitCondition(int op1, int op2);

	}

	public static class IF_ICMPEQ extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 == op1;
		}

	}

	public static class IF_ICMPNE extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 != op1;
		}

	}

	public static class IF_ICMPLT extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 < op1;
		}

	}

	public static class IF_ICMPLE extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 <= op1;
		}

	}

	public static class IF_ICMPGT extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 > op1;
		}

	}

	public static class IF_ICMPGE extends IF_ICMPX {

		@Override
		protected boolean fitCondition(int op1, int op2) {
			return op2 >= op1;
		}

	}

}
