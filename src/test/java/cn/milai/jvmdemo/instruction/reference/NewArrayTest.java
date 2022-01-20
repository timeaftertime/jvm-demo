package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
 * {@link NewArray} 测试类
 * @author milai
 * @date 2022.01.19
 */
public class NewArrayTest {

	private Instruction newArray;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 10 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		newArray = new NewArray();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.ADD_TEST).getMethod(MethodConst.INIT, MethodConst.RETURN_VOID, false)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		OperandStack stack = frame.getOperandStack();
		newArray.readOperands(reader);
		stack.pushInt(9);
		newArray.execute(frame);
		ObjectRef popRef = stack.popRef();
		assertEquals("[I", popRef.getClassInfo().getName());
		assertEquals(9, popRef.length());
		stack.pushInt(-1);
		try {
			newArray.execute(frame);
		} catch (NegativeArraySizeException e) {
			assertEquals(-1 + "", e.getMessage());
			return;
		}
		fail();
	}

}
