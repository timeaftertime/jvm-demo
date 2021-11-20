package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.math.ShInstructions.ISHL;
import cn.milai.jvmdemo.instruction.math.ShInstructions.ISHR;
import cn.milai.jvmdemo.instruction.math.ShInstructions.IUSHR;
import cn.milai.jvmdemo.instruction.math.ShInstructions.LSHL;
import cn.milai.jvmdemo.instruction.math.ShInstructions.LSHR;
import cn.milai.jvmdemo.instruction.math.ShInstructions.LUSHR;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link ShInstructions} 测试类
 * @author milai
 * @date 2021.11.20
 */
public class ShInstructionsTest {

	private Instruction[] shs;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;

	@Before
	public void setUp() {
		shs = new Instruction[] { new ISHL(), new ISHR(), new IUSHR(), new LSHL(), new LSHR(), new LUSHR() };
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 4; i++) {
			shs[i].readOperands(null);
		}
		frame.getOperandStack().pushInt(2);
		frame.getOperandStack().pushInt(3);
		shs[0].execute(frame);
		assertEquals(16, frame.getOperandStack().popInt());
		frame.getOperandStack().pushInt(-8);
		frame.getOperandStack().pushInt(3);
		shs[1].execute(frame);
		assertEquals(-1, frame.getOperandStack().popInt());
		frame.getOperandStack().pushInt(-8);
		frame.getOperandStack().pushInt(3);
		shs[2].execute(frame);
		assertEquals(536870911, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(22222222222L);
		frame.getOperandStack().pushInt(1);
		shs[3].execute(frame);
		assertEquals(44444444444L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushLong(-22222222222L);
		frame.getOperandStack().pushInt(1);
		shs[4].execute(frame);
		assertEquals(-11111111111L, frame.getOperandStack().popLong());
		frame.getOperandStack().pushLong(-22222222222L);
		frame.getOperandStack().pushInt(1);
		shs[5].execute(frame);
		assertEquals(9223372025743664697L, frame.getOperandStack().popLong());
	}

	@Test
	public void shMoreThan32Or64() {
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(40);
		shs[0].execute(frame);
		assertEquals(1 << 8, frame.getOperandStack().popInt());
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(40);
		shs[1].execute(frame);
		assertEquals(1 >> 8, frame.getOperandStack().popInt());
		frame.getOperandStack().pushInt(1);
		frame.getOperandStack().pushInt(40);
		shs[2].execute(frame);
		assertEquals(1 >>> 8, frame.getOperandStack().popInt());
		frame.getOperandStack().pushLong(1);
		frame.getOperandStack().pushInt(70);
		shs[3].execute(frame);
		assertEquals(1 << 6, frame.getOperandStack().popLong());
		frame.getOperandStack().pushLong(1);
		frame.getOperandStack().pushInt(70);
		shs[4].execute(frame);
		assertEquals(1 >> 6, frame.getOperandStack().popLong());
		frame.getOperandStack().pushLong(1);
		frame.getOperandStack().pushInt(70);
		shs[5].execute(frame);
		assertEquals(1 >>> 6, frame.getOperandStack().popLong());
	}
}
