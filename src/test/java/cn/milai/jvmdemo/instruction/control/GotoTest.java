package cn.milai.jvmdemo.instruction.control;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link GOTO} 测试类
 * @author milai
 * @date 2021.12.02
 */
public class GotoTest {

	private Instruction gotoIns;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 20 };

	@Before
	public void setUp() {
		gotoIns = new GOTO();
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		gotoIns.readOperands(reader);
		assertEquals(2, reader.getPC());
		gotoIns.execute(frame);
		assertEquals(20, frame.getThreadSpace().getPC());
	}

}
