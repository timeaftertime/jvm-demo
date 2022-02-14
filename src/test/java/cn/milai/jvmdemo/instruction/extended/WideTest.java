package cn.milai.jvmdemo.instruction.extended;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.LocalVarsTable;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link Wide} 测试类
 * @author milai
 * @date 2022.02.01
 */
public class WideTest {

	private Instruction wide;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 258;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0x15, 1, 0, 0x16, 1, 0, 0x17, 1, 0, 0x18, 1, 0, 0x36, 1, 0, 0x37, 1, 0, 0x38, 1,
		0, 0x39, 1, 0, -0x7c, 1, 0, 1, 0, -0x57, 0x19, 1, 0, 0x3a, 1, 0 };

	@Before
	public void setUp() {
		wide = new Wide();
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		double delta = 0.01;
		OperandStack stack = frame.getOperandStack();
		LocalVarsTable table = frame.getLocalVarsTable();

		wide.readOperands(reader);
		assertEquals(3, reader.getPC());
		table.setInt(256, 10);
		wide.execute(frame);
		assertEquals(10, stack.popInt());
		wide.readOperands(reader);
		assertEquals(6, reader.getPC());
		table.setLong(256, 123456789123L);
		wide.execute(frame);
		assertEquals(123456789123L, stack.popLong());
		wide.readOperands(reader);
		assertEquals(9, reader.getPC());
		table.setFloat(256, 3.1415f);
		wide.execute(frame);
		assertEquals(3.1415f, stack.popFloat(), delta);
		wide.readOperands(reader);
		assertEquals(12, reader.getPC());
		table.setDouble(256, 3.1415926);
		wide.execute(frame);
		assertEquals(3.1415926, stack.popDouble(), delta);

		wide.readOperands(reader);
		assertEquals(15, reader.getPC());
		stack.pushInt(100);
		wide.execute(frame);
		assertEquals(100, table.getInt(256));
		wide.readOperands(reader);
		assertEquals(18, reader.getPC());
		stack.pushLong(100);
		wide.execute(frame);
		assertEquals(100, table.getLong(256));
		wide.readOperands(reader);
		assertEquals(21, reader.getPC());
		stack.pushFloat(100.11f);
		wide.execute(frame);
		assertEquals(100.11f, table.getFloat(256), delta);
		wide.readOperands(reader);
		assertEquals(24, reader.getPC());
		stack.pushDouble(100.9999999);
		wide.execute(frame);
		assertEquals(100.9999999, table.getDouble(256), delta);

		wide.readOperands(reader);
		assertEquals(29, reader.getPC());
		table.setInt(256, 10);
		wide.execute(frame);
		assertEquals(266, table.getInt(256));
		try {
			wide.readOperands(reader);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail();
	}

}
