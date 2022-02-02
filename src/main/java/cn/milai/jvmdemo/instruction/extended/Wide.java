package cn.milai.jvmdemo.instruction.extended;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.InstructionFactory;
import cn.milai.jvmdemo.instruction.OneOperandInstruction;
import cn.milai.jvmdemo.instruction.math.IINC;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * wide 指令
 * @author milai
 * @date 2022.02.01
 */
public class Wide implements Instruction {

	private Instruction wided;

	@Override
	public void readOperands(BytecodeReader reader) throws IOException {
		int opCode = reader.readUint8();
		Instruction ins = InstructionFactory.create(opCode);
		if (ins instanceof IINC) {
			IINC iinc = (IINC) ins;
			iinc.setIndex(reader.readUint16());
			iinc.setConstant(reader.readInt16());
			wided = iinc;
			return;
		}
		if (ins instanceof OneOperandInstruction) {
			OneOperandInstruction one = (OneOperandInstruction) ins;
			one.setOperand(reader.readUint16());
			wided = one;
			return;
		}
		throw new IllegalArgumentException("不支持拓展的指令:" + opCode);
	}

	@Override
	public void execute(Frame frame) {
		wided.execute(frame);
	}

}
