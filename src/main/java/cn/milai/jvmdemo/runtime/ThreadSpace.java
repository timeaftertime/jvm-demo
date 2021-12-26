package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.JVMStack;
import cn.milai.jvmdemo.runtime.stack.LocalVarsTable;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * 线程空间
 * @author milai
 * @date 2021.09.27
 */
public class ThreadSpace {

	private int pc;

	private JVMStack stack;
	private static final int DEFAULT_MAX_STACK_SIZE = 100;

	public ThreadSpace() {
		this.pc = 0;
		this.stack = new JVMStack(DEFAULT_MAX_STACK_SIZE);
	}

	public void invoke(Method method) {
		if (method.isNative()) {
			return;
		}
		Frame preFrame = currentFrame();
		stack.push(new Frame(this, method));
		Frame newFrame = currentFrame();
		newFrame.setReturnPC(pc);
		if (preFrame != null) {
			passArgs(preFrame.getOperandStack(), newFrame.getLocalVarsTable(), method.getArgsSlotCnt());
		}
		if (needInvokeClinit(method)) {
			method.getClassInfo().init(this);
		}
		pc = 0;
	}

	private boolean needInvokeClinit(Method method) {
		if (MethodConst.isClinit(method)) {
			return false;
		}
		return !method.getClassInfo().isInitialized();
	}

	private void passArgs(OperandStack operands, LocalVarsTable localVars, int argsSlotCnt) {
		for (int i = argsSlotCnt - 1; i >= 0; i--) {
			localVars.setSlot(i, operands.popSlot());
		}
	}

	public Frame popFrame() {
		return stack.pop();
	}

	public Frame currentFrame() {
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public int getPC() { return pc; }

	public void setPC(int pc) { this.pc = pc; }

	public boolean finished() {
		return stack.isEmpty();
	}

	@Override
	public String toString() {
		return "ThreadSpace [pc=" + pc + "]";
	}

	public void clearStack() {
		stack.clear();
	}

	public Frame[] getFrames() { return stack.toArray(new Frame[0]); }

}
