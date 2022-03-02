package cn.milai.jvmdemo.instruction.reference;

import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.runtime.stack.StackTrace;

/**
 * athrow
 * @author milai
 * @date 2022.03.01
 */
public class Athrow implements Instruction {

	@Override
	public void execute(Frame frame) {
		OperandStack stack = frame.getOperandStack();
		ObjectRef ref = stack.popRef();
		if (ref == null) {
			throw new NullPointerException();
		}
		ClassInfo c = ref.getClassInfo();
		if (!c.isSubClassOf(c.getClassInfoLoader().load(ClassConst.THROWABLE))) {
			throw new IllegalArgumentException(
				String.format("%s 不是 %s 的子类", c.getName(), ClassConst.THROWABLE)
			);
		}
		ThreadSpace threadSpace = frame.getThreadSpace();
		if (!recoverFrom(threadSpace, ref)) {
			handleUncaughtException(threadSpace, ref);
		}
	}

	private boolean recoverFrom(ThreadSpace threadSpace, ObjectRef ex) {
		while (!threadSpace.finished()) {
			Frame frame = threadSpace.currentFrame();
			int recoverPC = frame.getMethod().getExceptionHandler().getRecoverPC(
				ex.getClassInfo(), frame.getCurrentPC()
			);
			if (recoverPC > 0) {
				frame.setCurrentPC(recoverPC);
				frame.getOperandStack().clear();
				frame.getOperandStack().pushRef(ex);
				return true;
			}
			threadSpace.popFrame();
		}
		return false;
	}

	private void handleUncaughtException(ThreadSpace threadSpace, ObjectRef ref) {
		threadSpace.clearStack();
		System.err.println(ref + " : " + ref.getMessage());
		for (StackTrace trace : ref.getStackTraces()) {
			System.err.println("\t" + trace);
		}
	}

}
