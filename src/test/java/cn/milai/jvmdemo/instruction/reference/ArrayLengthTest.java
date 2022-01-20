package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.constants.MethodConst;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link ArrayLength} 测试类
 * @author milai
 * @date 2022.01.19
 */
public class ArrayLengthTest {

	private Instruction array_length;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 10 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		array_length = new ArrayLength();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.ADD_TEST).getMethod(MethodConst.INIT, MethodConst.RETURN_VOID, false)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException, ClassNotFoundException {
		OperandStack stack = frame.getOperandStack();
		array_length.readOperands(reader);
		stack.pushRef(new ObjectRef(loader.load("[" + Classes.ADD_TEST + ";"), 6));
		array_length.execute(frame);
		assertEquals(6, stack.popInt());
		stack.pushRef(new ObjectRef(loader.load("[D"), 6));
		array_length.execute(frame);
		assertEquals(6, stack.popInt());
	}

}
