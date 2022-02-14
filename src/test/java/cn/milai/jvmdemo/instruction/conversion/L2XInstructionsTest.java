package cn.milai.jvmdemo.instruction.conversion;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.conversion.L2XInstructions.L2D;
import cn.milai.jvmdemo.instruction.conversion.L2XInstructions.L2F;
import cn.milai.jvmdemo.instruction.conversion.L2XInstructions.L2I;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link L2XInstructions} 测试类
 */
public class L2XInstructionsTest {

	private Instruction[] l2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		l2xs = new Instruction[] { new L2I(), new L2F(), new L2D() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 3; i++) {
			l2xs[i].readOperands(null);
		}
		frame.getOperandStack().pushLong(11111111111L);
		l2xs[0].execute(frame);
		assertEquals((int) 11111111111L, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(11111111111L);
		l2xs[1].execute(frame);
		assertEquals((float) 11111111111L, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushLong(11111111111L);
		l2xs[2].execute(frame);
		assertEquals((double) 11111111111L, frame.getOperandStack().popDouble(), delta);
	}

}
