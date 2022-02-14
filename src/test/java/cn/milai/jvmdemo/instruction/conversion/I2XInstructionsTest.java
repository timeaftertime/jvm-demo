package cn.milai.jvmdemo.instruction.conversion;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.conversion.I2XInstructions.I2D;
import cn.milai.jvmdemo.instruction.conversion.I2XInstructions.I2F;
import cn.milai.jvmdemo.instruction.conversion.I2XInstructions.I2L;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link I2XInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class I2XInstructionsTest {

	private Instruction[] i2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		i2xs = new Instruction[] { new I2L(), new I2F(), new I2D() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 3; i++) {
			i2xs[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(1);
		i2xs[0].execute(frame);
		assertEquals(1L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushInt(1);
		i2xs[1].execute(frame);
		assertEquals(1f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushInt(1);
		i2xs[2].execute(frame);
		assertEquals(1, frame.getOperandStack().popDouble(), delta);
	}

}
