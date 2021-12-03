package cn.milai.jvmdemo.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.DCMPG;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.DCMPL;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.FCMPG;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.FCMPL;
import cn.milai.jvmdemo.instruction.compare.CmpInstructions.LCMP;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link CmpInstructions} 测试类
 * @author milai
 * @date 2021.12.02
 */
public class CmpInstructionsTest {

	private Instruction[] xcmpxs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		xcmpxs = new Instruction[] { new LCMP(), new FCMPG(), new FCMPL(), new DCMPG(), new DCMPL() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 5; i++) {
			xcmpxs[i].readOperands(null);
		}
		OperandStack stack = frame.getOperandStack();
		stack.pushLong(100L);
		stack.pushLong(101L);
		xcmpxs[0].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
		stack.pushFloat(100.21f);
		stack.pushFloat(100f);
		xcmpxs[2].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(1, stack.popInt());
		stack.pushFloat(100.21f);
		stack.pushFloat(100.22f);
		xcmpxs[2].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
		stack.pushDouble(100.21);
		stack.pushDouble(100.21);
		xcmpxs[3].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(0, stack.popInt());
		stack.pushDouble(100.21);
		stack.pushDouble(100.21);
		xcmpxs[4].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(0, stack.popInt());
	}

	@Test
	public void compareNaN() {
		OperandStack stack = frame.getOperandStack();
		stack.pushFloat(2.1f);
		stack.pushFloat(Float.NaN);
		xcmpxs[1].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(1, stack.popInt());
		stack.pushFloat(2.1f);
		stack.pushFloat(Float.NaN);
		xcmpxs[2].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
		stack.pushDouble(Float.NaN);
		stack.pushDouble(Float.NaN);
		xcmpxs[3].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(1, stack.popInt());
		stack.pushDouble(Float.NaN);
		stack.pushDouble(Float.NaN);
		xcmpxs[4].execute(frame);
		assertEquals(1, stack.size());
		assertEquals(-1, stack.popInt());
	}

}
