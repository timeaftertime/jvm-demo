package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.RemInstructions.DREM;
import cn.milai.jvmdemo.instruction.math.RemInstructions.FREM;
import cn.milai.jvmdemo.instruction.math.RemInstructions.IREM;
import cn.milai.jvmdemo.instruction.math.RemInstructions.LREM;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link RemInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class RemInstructionsTest {

	private Instruction[] rems;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		rems = new Instruction[4];
		rems[0] = new IREM();
		rems[1] = new LREM();
		rems[2] = new FREM();
		rems[3] = new DREM();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 4; i++) {
			rems[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(3);
		rems[0].execute(frame);
		assertEquals(2, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushLong(11111111111L);
		rems[1].execute(frame);
		assertEquals(0L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(2.1f);
		frame.getOperandStack().pushFloat(1.1f);
		rems[2].execute(frame);
		assertEquals(2.1f % 1.1f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(2.11111111);
		frame.getOperandStack().pushDouble(1.11111111);
		rems[3].execute(frame);
		assertEquals(2.11111111 % 1.1111111, frame.getOperandStack().popDouble(), delta);
	}

	@Test
	public void dremZero() {
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(0);
		try {
			rems[0].execute(frame);
		} catch (ArithmeticException e1) {
			frame.getOperandStack().pushLong(1);
			frame.getOperandStack().pushLong(0);
			try {
				rems[1].execute(frame);
			} catch (ArithmeticException e2) {
				frame.getOperandStack().pushFloat(1);
				frame.getOperandStack().pushFloat(0);
				rems[2].execute(frame);
				assertTrue(Float.isNaN(frame.getOperandStack().popFloat()));
				frame.getOperandStack().pushDouble(1);
				frame.getOperandStack().pushDouble(0);
				rems[3].execute(frame);
				assertTrue(Double.isNaN(frame.getOperandStack().popDouble()));
				return;
			}
			fail();
		}
	}

}
