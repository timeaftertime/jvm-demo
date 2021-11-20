package cn.milai.jvmdemo.instruction.math;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.LocalVarsTable;

/**
 * inc 指令
 * @author milai
 * @date 2021.11.20
 */
public class IINC implements Instruction {

	private int localVarsTableIndex;
	private int constant;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		localVarsTableIndex = reader.readUint8();
		constant = reader.readInt8();
	}

	@Override
	public void execute(Frame frame) {
		LocalVarsTable table = frame.getLocalVarsTable();
		table.setInt(localVarsTableIndex, table.getInt(localVarsTableIndex) + constant);
	}

	public void setIndex(int index) { localVarsTableIndex = index; }

	public void setConstant(int value) { constant = value; }

}
