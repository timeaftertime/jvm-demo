package cn.milai.jvmdemo.instruction.compare;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * ifXX 指令
 * @author milai
 * @date 2021.12.02
 */
public class IfInstructions {

	public abstract static class IFX extends BranchInstruction {

		@Override
		protected boolean fitCondition(Frame frame) {
			return fitCondition(0, frame.getOperandStack().popInt());
		}

		public abstract boolean fitCondition(int op1, int op2);

	}

	public static class IFEQ extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 == op1;
		}

	}

	public static class IFNE extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 != op1;
		}

	}

	public static class IFLT extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 < op1;
		}

	}

	public static class IFLE extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 <= op1;
		}

	}

	public static class IFGT extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 > op1;
		}

	}

	public static class IFGE extends IFX {

		@Override
		public boolean fitCondition(int op1, int op2) {
			return op2 >= op1;
		}

	}

}
