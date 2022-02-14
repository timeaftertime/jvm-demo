package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.OrInstructions.IOR;
import cn.milai.jvmdemo.instruction.math.OrInstructions.LOR;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link OrInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class OrInstructionsTest {

	private Instruction[] ors;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		ors = new Instruction[] { new IOR(), new LOR() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 2; i++) {
			ors[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(207);
		frame.getOperandStack().pushInt(182);
		ors[0].execute(frame);
		assertEquals(207 | 182, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(-8);
		frame.getOperandStack().pushLong(3);
		ors[1].execute(frame);
		assertEquals(-8L | 3L, frame.getOperandStack().popLong());
	}

}
