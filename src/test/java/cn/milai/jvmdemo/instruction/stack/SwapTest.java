package cn.milai.jvmdemo.instruction.stack;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link Swap} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class SwapTest {

	private Instruction swap;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		swap = new Swap();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		swap.readOperands(null);
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(2);
		swap.execute(frame);
		assertEquals(2, frame.getOperandStack().size());
		assertEquals(1, frame.getOperandStack().popInt());
		assertEquals(2, frame.getOperandStack().popInt());
	}

}