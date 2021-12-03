package cn.milai.jvmdemo.instruction.control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.control.SwitchInstructions.LOOKUPSWITCH;
import cn.milai.jvmdemo.instruction.control.SwitchInstructions.TABLESWITCH;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link SwitchInstructions} 测试类
 * @author milai
 * @date 2021.12.03
 */
public class SwitchInstructionsTest {

	private Instruction tableSwitch;
	private Instruction lookupSwitch;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 1, 2, 3, 0, 0, 0, 0x7a, 0, 0, 0, 0x0, 0, 0, 0, 0x2, 0, 0, 0, 0x1, 0, 0, 0,
		0x2, 0, 0, 0, 0x3, 0, 1, 2, 3, 0, 0, 0, 0x7a, 0, 0, 0, 0x3, 0, 0, 0, 0x0, 0, 0, 0, 0x1, 0, 0, 0, 0x1, 0, 0,
		0, 0x2, 0, 0, 0, 0x2, 0, 0, 0, 0x3 };

	@Before
	public void setUp() {
		tableSwitch = new TABLESWITCH();
		lookupSwitch = new LOOKUPSWITCH();
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void testReadOperandsAndExecute() throws IOException {
		reader.readInt8();
		tableSwitch.readOperands(reader);
		int defaultOffset = 0x7a;
		int low = 0;
		int hig = 2;
		assertEquals(28, reader.getPC());
		reader.readInt8();
		lookupSwitch.readOperands(reader);
		assertEquals(64, reader.getPC());
		OperandStack stack = frame.getOperandStack();
		int notExistsCase = 100;
		stack.pushInt(notExistsCase);
		tableSwitch.execute(frame);
		assertEquals(defaultOffset, frame.getThreadSpace().getPC());
		for (int i = 0; i < hig - low + 1; i++) {
			stack.pushInt(i);
			tableSwitch.execute(frame);
			assertEquals(low + i + 1, frame.getThreadSpace().getPC());
		}

		int n = 3;
		frame.getThreadSpace().setPC(0);
		stack.pushInt(notExistsCase);
		lookupSwitch.execute(frame);
		assertEquals(defaultOffset, frame.getThreadSpace().getPC());
		for (int i = 0; i < n; i++) {
			stack.pushInt(i);
			lookupSwitch.execute(frame);
			assertEquals(i + 1, frame.getThreadSpace().getPC());
		}
	}

}
