package cn.milai.jvmdemo.instruction.extended;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.instruction.BytecodeReader;

/**
 * goto_w 指令
 * @author milai
 * @date 2022.02.01
 */
public class GotoW extends BranchInstruction {

	@Override
	protected int readOffset(BytecodeReader reader) throws IOException {
		return reader.readInt32();
	}

}
