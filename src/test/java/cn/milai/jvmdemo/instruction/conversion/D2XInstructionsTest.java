package cn.milai.jvmdemo.instruction.conversion;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.conversion.D2XInstructions.D2F;
import cn.milai.jvmdemo.instruction.conversion.D2XInstructions.D2I;
import cn.milai.jvmdemo.instruction.conversion.D2XInstructions.D2L;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link D2XInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class D2XInstructionsTest {

	private Instruction[] d2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		d2xs = new Instruction[] { new D2I(), new D2L(), new D2F() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 3; i++) {
			d2xs[i].readOperands(null);
		}
		frame.getOperandStack().pushDouble(3.14);
		d2xs[0].execute(frame);
		assertEquals((int) 3.14, frame.getOperandStack().popInt(), delta);
		frame.getOperandStack().pushDouble(3.14);
		d2xs[1].execute(frame);
		assertEquals((long) 3.14, frame.getOperandStack().popLong(), delta);
		frame.getOperandStack().pushDouble(3.14);
		d2xs[2].execute(frame);
		assertEquals((float) 3.14, frame.getOperandStack().popFloat(), delta);
	}
}
