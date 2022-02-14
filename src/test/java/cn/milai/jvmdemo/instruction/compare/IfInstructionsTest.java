package cn.milai.jvmdemo.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.BranchInstruction;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFEQ;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFGE;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFGT;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFLE;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFLT;
import cn.milai.jvmdemo.instruction.compare.IfInstructions.IFNE;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link BranchInstruction} 测试类
 * @author milai
 * @date 2021.12.02
 */
public class IfInstructionsTest {

	private Instruction[] ifCmps;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1 };

	@Before
	public void setUp() {
		ifCmps = new Instruction[] { new IFEQ(), new IFNE(), new IFLT(), new IFLE(), new IFGT(), new IFGE() };
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < ifCmps.length; i++) {
			ifCmps[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPC());
		}
		OperandStack stack = frame.getOperandStack();
		for (int i = 0; i < ifCmps.length; i++) {
			stack.pushInt(1);
		}
		assertEquals(0, frame.getThreadSpace().getPC());
		ifCmps[0].execute(frame);
		assertEquals(0, frame.getThreadSpace().getPC());
		ifCmps[1].execute(frame);
		assertEquals(5, frame.getThreadSpace().getPC());
		ifCmps[2].execute(frame);
		assertEquals(5, frame.getThreadSpace().getPC());
		ifCmps[3].execute(frame);
		assertEquals(5, frame.getThreadSpace().getPC());
		ifCmps[4].execute(frame);
		assertEquals(2, frame.getThreadSpace().getPC());
		ifCmps[5].execute(frame);
		assertEquals(1, frame.getThreadSpace().getPC());
	}

}
