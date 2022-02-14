package cn.milai.jvmdemo.instruction.compare;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.compare.IfAcmpInstructions.IF_ACMPEQ;
import cn.milai.jvmdemo.instruction.compare.IfAcmpInstructions.IF_ACMPNE;
import cn.milai.jvmdemo.instruction.compare.IfAcmpInstructions.IF_ACMPX;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link IfAcmpInstructions} 测试类
 * @author milai
 * @date 2022.01.19
 */
public class IfAcmpInstructionsTest {

	private Instruction[] ifAcmps;
	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 0, 6, 0, 5 };
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() {
		ifAcmps = new IF_ACMPX[2];
		ifAcmps[0] = new IF_ACMPEQ();
		ifAcmps[1] = new IF_ACMPNE();
		frame = new Frame(
			new ThreadSpace(),
			MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY)
		);
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		for (int i = 0; i < ifAcmps.length; i++) {
			ifAcmps[i].readOperands(reader);
			assertEquals((i + 1) * 2, reader.getPC());
		}
		OperandStack stack = frame.getOperandStack();
		stack.pushRef(new ObjectRef(loader.load(Classes.ADD_TEST)));
		stack.pushRef(new ObjectRef(loader.load(Classes.ADD_TEST)));
		ObjectRef ref = new ObjectRef(loader.load(Classes.ADD_TEST));
		stack.pushRef(ref);
		stack.pushRef(ref);
		assertEquals(0, frame.getThreadSpace().getPC());
		ifAcmps[0].execute(frame);
		assertEquals(6, frame.getThreadSpace().getPC());
		ifAcmps[1].execute(frame);
		assertEquals(5, frame.getThreadSpace().getPC());
	}

}
