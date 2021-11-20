package cn.milai.jvmdemo.instruction.conversion;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.conversion.F2XInstructions.F2D;
import cn.milai.jvmdemo.instruction.conversion.F2XInstructions.F2I;
import cn.milai.jvmdemo.instruction.conversion.F2XInstructions.F2L;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link F2XInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class F2XInstructionsTest {

	private Instruction[] f2xs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		f2xs = new Instruction[] { new F2I(), new F2L(), new F2D() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 3; i++) {
			f2xs[i].readOperands(null);
		}
		frame.getOperandStack().pushFloat(3.14f);
		f2xs[0].execute(frame);
		assertEquals((int) 3.14f, frame.getOperandStack().popInt(), delta);
		frame.getOperandStack().pushFloat(3.14f);
		f2xs[1].execute(frame);
		assertEquals((long) 3.14f, frame.getOperandStack().popLong(), delta);
		frame.getOperandStack().pushFloat(3.14f);
		f2xs[2].execute(frame);
		assertEquals((double) 3.14f, frame.getOperandStack().popDouble(), delta);
	}
}
