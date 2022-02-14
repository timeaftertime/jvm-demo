package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.DivInstructions.DDIV;
import cn.milai.jvmdemo.instruction.math.DivInstructions.FDIV;
import cn.milai.jvmdemo.instruction.math.DivInstructions.IDIV;
import cn.milai.jvmdemo.instruction.math.DivInstructions.LDIV;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link DivInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class DivInstructionsTest {

	private Instruction[] divs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		divs = new Instruction[] { new IDIV(), new LDIV(), new FDIV(), new DDIV() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 4; i++) {
			divs[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(1);
		divs[0].execute(frame);
		assertEquals(2, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushLong(11111111111L);
		divs[1].execute(frame);
		assertEquals(22222222222L / 11111111111L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(2.1f);
		frame.getOperandStack().pushFloat(1.1f);
		divs[2].execute(frame);
		assertEquals(2.1f / 1.1f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(2.11111111);
		frame.getOperandStack().pushDouble(1.11111111);
		divs[3].execute(frame);
		assertEquals(2.11111111 / 1.1111111, frame.getOperandStack().popDouble(), delta);
	}

	@Test
	public void divZero() {
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(0);
		try {
			divs[0].execute(frame);
		} catch (ArithmeticException e1) {
			frame.getOperandStack().pushLong(1);
			frame.getOperandStack().pushLong(0);
			try {
				divs[1].execute(frame);
			} catch (ArithmeticException e2) {
				frame.getOperandStack().pushFloat(1);
				frame.getOperandStack().pushFloat(0);
				divs[2].execute(frame);
				assertTrue(Float.isInfinite(frame.getOperandStack().popFloat()));
				frame.getOperandStack().pushDouble(1);
				frame.getOperandStack().pushDouble(0);
				divs[3].execute(frame);
				assertTrue(Double.isInfinite(frame.getOperandStack().popDouble()));
				return;
			}
			fail();
		}
	}

	@Test
	public void divZeroWithZero() {
		frame.getOperandStack().pushFloat(0);
		frame.getOperandStack().pushFloat(0);
		divs[2].execute(frame);
		assertTrue(Float.isNaN(frame.getOperandStack().popFloat()));
		frame.getOperandStack().pushDouble(0);
		frame.getOperandStack().pushDouble(0);
		divs[3].execute(frame);
		assertTrue(Double.isNaN(frame.getOperandStack().popDouble()));
	}

}
