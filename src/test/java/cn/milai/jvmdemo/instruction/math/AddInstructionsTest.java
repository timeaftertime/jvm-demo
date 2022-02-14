package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.AddInstructions.DADD;
import cn.milai.jvmdemo.instruction.math.AddInstructions.FADD;
import cn.milai.jvmdemo.instruction.math.AddInstructions.IADD;
import cn.milai.jvmdemo.instruction.math.AddInstructions.LADD;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link AddInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class AddInstructionsTest {

	private Instruction[] adds;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		adds = new Instruction[4];
		adds[0] = new IADD();
		adds[1] = new LADD();
		adds[2] = new FADD();
		adds[3] = new DADD();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 4; i++) {
			adds[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(2);
		adds[0].execute(frame);
		assertEquals(3, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(11111111111L);
		frame.getOperandStack().pushLong(22222222222L);
		adds[1].execute(frame);
		assertEquals(33333333333L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(1.1f);
		frame.getOperandStack().pushFloat(2.1f);
		adds[2].execute(frame);
		assertEquals(3.2f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(1.11111111);
		frame.getOperandStack().pushDouble(2.11111111);
		adds[3].execute(frame);
		assertEquals(3.22222222, frame.getOperandStack().popDouble(), delta);
	}

}
