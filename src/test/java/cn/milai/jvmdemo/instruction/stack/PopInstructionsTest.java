package cn.milai.jvmdemo.instruction.stack;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.stack.PopInstructions.POP;
import cn.milai.jvmdemo.instruction.stack.PopInstructions.POP2;
import cn.milai.jvmdemo.runtime.slot.Slot;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link PopInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class PopInstructionsTest {

	private Instruction pop;
	private Instruction pop2;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		pop = new POP();
		pop2 = new POP2();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		pop.readOperands(null);
		pop2.readOperands(null);
		frame.getOperandStack().pushSlot(new Slot(0));
		frame.getOperandStack().pushSlot(new Slot(0));
		frame.getOperandStack().pushSlot(new Slot(0));
		assertEquals(3, frame.getOperandStack().size());
		pop.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		pop2.execute(frame);
		assertEquals(0, frame.getOperandStack().size());
	}

}
