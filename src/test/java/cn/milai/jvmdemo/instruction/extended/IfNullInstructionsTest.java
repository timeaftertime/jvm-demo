package cn.milai.jvmdemo.instruction.extended;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.extended.IfNullInstructions.IfNonNull;
import cn.milai.jvmdemo.instruction.extended.IfNullInstructions.IfNull;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link IfNullInstructions} 测试类
 * @author milai
 * @date 2022.02.01
 */
public class IfNullInstructionsTest {

	private Instruction[] ifXNulls;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 6, 0, 5 };

	@Before
	public void setUp() {
		ifXNulls = new Instruction[2];
		ifXNulls[0] = new IfNull();
		ifXNulls[1] = new IfNonNull();
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < ifXNulls.length; i++) {
			ifXNulls[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPC());
		}
		OperandStack stack = frame.getOperandStack();
		stack.pushRef(new ObjectRef(TestClassInfoLoader.get().load(Classes.ADD_TEST)));
		stack.pushRef(null);
		assertEquals(0, frame.getThreadSpace().getPC());
		ifXNulls[0].execute(frame);
		assertEquals(6, frame.getThreadSpace().getPC());
		ifXNulls[1].execute(frame);
		assertEquals(5, frame.getThreadSpace().getPC());
	}

}
