package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.AndInstructions.IAND;
import cn.milai.jvmdemo.instruction.math.AndInstructions.LAND;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link AndInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class AndInstructionsTest {

	private Instruction[] ands;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		ands = new Instruction[2];
		ands[0] = new IAND();
		ands[1] = new LAND();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 2; i++) {
			ands[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(207);
		frame.getOperandStack().pushInt(182);
		ands[0].execute(frame);
		assertEquals(207 & 182, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(-8);
		frame.getOperandStack().pushLong(3);
		ands[1].execute(frame);
		assertEquals(-8L & 3L, frame.getOperandStack().popLong());
	}

}
