package cn.milai.jvmdemo.instruction.stack;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP2;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP2_X1;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP2_X2;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP_X1;
import cn.milai.jvmdemo.instruction.stack.DupInstructions.DUP_X2;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link DupInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class DupInstructionsTest {

	private Instruction[] dups;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		dups = new Instruction[] { new DUP(), new DUP_X1(), new DUP_X2(), new DUP2(), new DUP2_X1(), new DUP2_X2() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 6; i++) {
			dups[i].readOperands(null);
		}
		OperandStack stack = frame.getOperandStack();
		pushInt(1);
		dups[0].execute(frame);
		assertEquals(2, stack.size());
		assertEquals(1, stack.popInt());
		assertEquals(1, stack.popInt());
		pushInt(2);
		dups[1].execute(frame);
		assertEquals(3, stack.size());
		assertEquals(2, stack.popInt());
		assertEquals(1, stack.popInt());
		assertEquals(2, stack.popInt());
		pushInt(3);
		dups[2].execute(frame);
		assertEquals(4, stack.size());
		assertEquals(3, stack.popInt());
		assertEquals(2, stack.popInt());
		assertEquals(1, stack.popInt());
		assertEquals(3, stack.popInt());
		pushInt(2);
		dups[3].execute(frame);
		assertEquals(4, stack.size());
		assertEquals(2, stack.popInt());
		assertEquals(1, stack.popInt());
		assertEquals(2, stack.popInt());
		assertEquals(1, stack.popInt());
		pushInt(3);
		dups[4].execute(frame);
		assertEquals(5, stack.size());
		assertEquals(3, stack.popInt());
		assertEquals(2, stack.popInt());
		assertEquals(1, stack.popInt());
		assertEquals(3, stack.popInt());
		assertEquals(2, stack.popInt());
		pushInt(4);
		dups[5].execute(frame);
		assertEquals(6, stack.size());
		assertEquals(4, stack.popInt());
		assertEquals(3, stack.popInt());
		assertEquals(2, stack.popInt());
		assertEquals(1, stack.popInt());
		assertEquals(4, stack.popInt());
		assertEquals(3, stack.popInt());
		assertEquals(0, stack.size());
	}

	private void pushInt(int n) {
		for (int i = 1; i <= n; i++) {
			frame.getOperandStack().pushInt(i);
		}
	}

}
