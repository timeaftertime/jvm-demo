package cn.milai.jvmdemo.instruction.control;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * Xreturn 指令
 * @author milai
 * @date 2021.11.28
 */
public class ReturnInstructions {

	private static abstract class XRETURN implements Instruction {
		@Override
		public void execute(Frame frame) {
			ThreadSpace threadSpace = frame.getThreadSpace();
			threadSpace.popFrame(); // == frame
			doReturn(frame, getCurrentFrame(threadSpace));
			threadSpace.setPC(frame.getReturnPC());
		}

		protected Frame getCurrentFrame(ThreadSpace threadSpace) {
			return threadSpace.currentFrame();
		}

		protected void doReturn(Frame preFrame, Frame nowFrame) {
		}
	}

	public static class RETURN extends XRETURN {
		@Override
		protected Frame getCurrentFrame(ThreadSpace threadSpace) {
			return null;
		}
	}

	public static class IRETURN extends XRETURN {
		@Override
		protected void doReturn(Frame preFrame, Frame nowFrame) {
			nowFrame.getOperandStack().pushInt(preFrame.getOperandStack().popInt());
		}

	}

	public static class FRETURN extends XRETURN {
		@Override
		protected void doReturn(Frame preFrame, Frame nowFrame) {
			nowFrame.getOperandStack().pushFloat(preFrame.getOperandStack().popFloat());
		}

	}

	public static class LRETURN extends XRETURN {
		@Override
		protected void doReturn(Frame preFrame, Frame nowFrame) {
			nowFrame.getOperandStack().pushLong(preFrame.getOperandStack().popLong());
		}
	}

	public static class DRETURN extends XRETURN {
		@Override
		protected void doReturn(Frame preFrame, Frame nowFrame) {
			nowFrame.getOperandStack().pushDouble(preFrame.getOperandStack().popDouble());
		}

	}

	public static class ARETURN extends XRETURN {
		@Override
		protected void doReturn(Frame preFrame, Frame nowFrame) {
			nowFrame.getOperandStack().pushRef(preFrame.getOperandStack().popRef());
		}
	}

}
