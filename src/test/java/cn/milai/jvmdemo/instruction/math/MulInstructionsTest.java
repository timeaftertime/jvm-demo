package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.MulInstructions.DMUL;
import cn.milai.jvmdemo.instruction.math.MulInstructions.FMUL;
import cn.milai.jvmdemo.instruction.math.MulInstructions.IMUL;
import cn.milai.jvmdemo.instruction.math.MulInstructions.LMUL;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link MulInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class MulInstructionsTest {

	private Instruction[] muls;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		muls = new Instruction[] { new IMUL(), new LMUL(), new FMUL(), new DMUL() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 4; i++) {
			muls[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(1);
		muls[0].execute(frame);
		assertEquals(2, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushLong(11111111111L);
		muls[1].execute(frame);
		assertEquals(22222222222L * 11111111111L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(2.1f);
		frame.getOperandStack().pushFloat(1.1f);
		muls[2].execute(frame);
		assertEquals(2.1f * 1.1f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(2.11111111);
		frame.getOperandStack().pushDouble(1.11111111);
		muls[3].execute(frame);
		assertEquals(2.11111111 * 1.1111111, frame.getOperandStack().popDouble(), delta);
	}

}
