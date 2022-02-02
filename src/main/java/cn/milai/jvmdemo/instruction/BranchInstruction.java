package cn.milai.jvmdemo.instruction;

import java.io.IOException;

import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 实现分支的指令
 * @author milai
 * @date 2021.12.02
 */
public abstract class BranchInstruction implements Instruction {

	private int operand;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		operand = readOffset(reader);
	}

	/**
	 * 读取并返回跳转偏移量
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	protected int readOffset(BytecodeReader reader) throws IOException {
		return reader.readInt16();
	}

	@Override
	public void execute(Frame frame) {
		if (fitCondition(frame)) {
			frame.getThreadSpace().setPC(frame.getCurrentPC() + operand);
		}
	}

	/**
	 * 是否满足跳转的条件
	 * @param frame
	 * @return
	 */
	protected boolean fitCondition(Frame frame) {
		return true;
	}

}
