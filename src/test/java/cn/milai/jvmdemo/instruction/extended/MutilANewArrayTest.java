package cn.milai.jvmdemo.instruction.extended;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link MutilAnewArray} 测试类
 * @author milai
 * @date 2022.02.01
 */
public class MutilANewArrayTest {

	private Instruction mutilAnewArray;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2, 3 };

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		mutilAnewArray = new MutilAnewArray();
		reader = new BytecodeReader(codes);
	}

	@Test
	public void createMultiDemensionArray() throws IOException {
		mutilAnewArray.readOperands(reader);
		assertEquals(3, reader.getPC());
		Frame frame = new Frame(new ThreadSpace(), TestClassInfoLoader.get().load(Classes.ARRAY_TEST).getMethods()[0]);
		OperandStack stack = frame.getOperandStack();
		stack.pushInt(5);
		stack.pushInt(3);
		stack.pushInt(4);
		mutilAnewArray.execute(frame);
		ObjectRef array = stack.popRef();
		assertEquals(5, array.length());
		assertEquals("[[[L" + Classes.OBJECT + ";", array.getClassInfo().getName());
		assertEquals(3, array.getElements().getRef(0).length());
		assertEquals("[[L" + Classes.OBJECT + ";", array.getElements().getRef(0).getClassInfo().getName());
		assertEquals(4, array.getElements().getRef(0).getElements().getRef(0).length());
		assertEquals(
			"[L" + Classes.OBJECT + ";", array.getElements().getRef(0).getElements().getRef(0).getClassInfo().getName()
		);
		assertNull(array.getElements().getRef(0).getElements().getRef(0).getElements().getRef(0));
	}
}
