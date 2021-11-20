package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.SubInstructions.DSUB;
import cn.milai.jvmdemo.instruction.math.SubInstructions.FSUB;
import cn.milai.jvmdemo.instruction.math.SubInstructions.ISUB;
import cn.milai.jvmdemo.instruction.math.SubInstructions.LSUB;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link SubInstructions}  测试类
 * @author milai
 * @date 2021.11.20
 */
public class SubInstructionsTest {

	private Instruction[] subs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		subs = new Instruction[] { new ISUB(), new LSUB(), new FSUB(), new DSUB() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		for (int i = 0; i < 4; i++) {
			subs[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(1);
		subs[0].execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushLong(11111111111L);
		subs[1].execute(frame);
		assertEquals(11111111111L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushFloat(2.1f);
		frame.getOperandStack().pushFloat(1.1f);
		subs[2].execute(frame);
		assertEquals(1.0f, frame.getOperandStack().popFloat(), delta);
		frame.getOperandStack().pushDouble(2.11111111);
		frame.getOperandStack().pushDouble(1.11111111);
		subs[3].execute(frame);
		assertEquals(1.00000000, frame.getOperandStack().popDouble(), delta);
	}

}
