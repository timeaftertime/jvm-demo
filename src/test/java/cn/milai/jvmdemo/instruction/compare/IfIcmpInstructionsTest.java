package cn.milai.jvmdemo.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPEQ;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPGE;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPGT;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPLE;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPLT;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPNE;
import cn.milai.jvmdemo.instruction.compare.IfIcmpInstructions.IF_ICMPX;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link IfIcmpInstructions} 测试类
 * @author milai
 * @date 2021.12.02
 */
public class IfIcmpInstructionsTest {

	private Instruction[] ifIcmps;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1 };

	@Before
	public void setUp() {
		ifIcmps = new IF_ICMPX[] { new IF_ICMPEQ(), new IF_ICMPNE(), new IF_ICMPLT(), new IF_ICMPLE(), new IF_ICMPGT(),
			new IF_ICMPGE() };
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < 6; i++) {
			ifIcmps[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPC());
		}
		OperandStack stack = frame.getOperandStack();
		for (int i = 0; i < 6; i++) {
			stack.pushInt(1);
			stack.pushInt(2);
		}
		assertEquals(0, frame.getThreadSpace().getPC());
		ifIcmps[0].execute(frame);
		assertEquals(0, frame.getThreadSpace().getPC());
		ifIcmps[1].execute(frame);
		assertEquals(5, frame.getThreadSpace().getPC());
		ifIcmps[2].execute(frame);
		assertEquals(4, frame.getThreadSpace().getPC());
		ifIcmps[3].execute(frame);
		assertEquals(3, frame.getThreadSpace().getPC());
		ifIcmps[4].execute(frame);
		assertEquals(3, frame.getThreadSpace().getPC());
		ifIcmps[5].execute(frame);
		assertEquals(3, frame.getThreadSpace().getPC());
	}

}
