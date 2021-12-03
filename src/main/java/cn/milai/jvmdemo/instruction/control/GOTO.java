package cn.milai.jvmdemo.instruction.control;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * goto 指令
 * @author milai
 * @date 2021.12.02
 */
public class GOTO extends BranchInstruction {

	@Override
	protected boolean fitCondition(Frame frame) {
		return true;
	}

}
