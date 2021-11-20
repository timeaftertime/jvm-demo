package cn.milai.jvmdemo.instruction.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link IINC} 测试类·
 * @author milai
 * @date 2021.11.20
 */
public class IINCTest {

	private Instruction iinc;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 1, 10 };

	@Before
	public void setUp() {
		iinc = new IINC();
		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readerOperandsAndExecute() throws IOException {
		iinc.readOperands(reader);
		assertEquals(2, reader.getPc());
		frame.getLocalVarsTable().setInt(1, 2);
		iinc.execute(frame);
		assertEquals(12, frame.getLocalVarsTable().getInt(1));
	}
}
