package cn.milai.jvmdemo.instruction.constant;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.constant.PushInstructions.BIPUSH;
import cn.milai.jvmdemo.instruction.constant.PushInstructions.SIPUSH;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link PushInstructions} 测试类
 * @author milai
 * @date 2021.11.14
 */
public class PushInstructionsTest {

	private Instruction bipush;
	private Instruction sipush;

	private Frame frame;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 10;
	private static final int MAX_OPERAND_STACK_CAPACITY = 10;

	@Before
	public void setUp() throws IOException {
		bipush = new BIPUSH();
		sipush = new SIPUSH();
		bipush.readOperands(new BytecodeReader(new byte[] { 1 }));
		sipush.readOperands(new BytecodeReader(new byte[] { 1, 1 }));
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
	}

	@Test
	public void testExecute() {
		bipush.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		sipush.execute(frame);
		assertEquals(0x0101, frame.getOperandStack().popInt());
	}

}