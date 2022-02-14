package cn.milai.jvmdemo.instruction.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.AASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.BASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.CASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.DASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.FASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.IASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.LASTORE;
import cn.milai.jvmdemo.instruction.store.AStoreInstructions.SASTORE;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link AStoreInstructions} 测试类
 * @author milai
 * @date 2022.01.06
 */
public class AStoreInstructionsTest {

	private Instruction[] xastore;
	private ThreadSpace threadSpace;
	private static ClassInfoLoader loader;

	@BeforeClass
	public static void setUpClass() {
		TestClassInfoLoader.initDefaultClassInfoLoader();
		loader = DefaultClassInfoLoader.getInstance();
	}

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		xastore = new Instruction[8];
		xastore[0] = new BASTORE();
		xastore[1] = new CASTORE();
		xastore[2] = new SASTORE();
		xastore[3] = new IASTORE();
		xastore[4] = new FASTORE();
		xastore[5] = new LASTORE();
		xastore[6] = new DASTORE();
		xastore[7] = new AASTORE();
		threadSpace = new ThreadSpace();
		threadSpace.invoke(MockFactory.newMethod(0, 4));
	}

	@Test
	public void readNoOperand() throws IOException {
		for (int i = 0; i < xastore.length; i++) {
			xastore[i].readOperands(null);
		}
	}

	@Test
	public void baload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[B"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setInt(i, i);
		}
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[0].execute(frame);
		assertEquals(5, array.getElements().getInt(3));
	}

	@Test
	public void caload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[C"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setInt(i, i);
		}
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[1].execute(frame);
		assertEquals(5, array.getElements().getInt(3));
	}

	@Test
	public void saload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[S"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setInt(i, i);
		}
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[1].execute(frame);
		assertEquals(5, array.getElements().getInt(3));
	}

	@Test
	public void iaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[I"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setInt(i, i);
		}
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushInt(5);
		xastore[1].execute(frame);
		assertEquals(5, array.getElements().getInt(3));
	}

	@Test
	public void faload() throws ClassNotFoundException, IOException {
		double delta = 0.01;
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[F"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setFloat(i, i + 3.14f);
		}
		stack.pushRef(array);
		stack.pushInt(0);
		stack.pushFloat(3.12222f);
		xastore[4].execute(frame);
		assertEquals(3.12222f, array.getElements().getFloat(0), delta);
	}

	@Test
	public void laload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[J"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setLong(i, i + 1234567890L);
		}
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushLong(12345654321L);
		xastore[5].execute(frame);
		assertEquals(12345654321L, array.getElements().getLong(3));
	}

	@Test
	public void daload() throws ClassNotFoundException, IOException {
		double delta = 0.01;
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[D"), 5);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setDouble(i, i + 0.999);
		}
		stack.pushRef(array);
		stack.pushInt(3);
		stack.pushDouble(666.999);
		xastore[6].execute(frame);
		assertEquals(666.999, array.getElements().getDouble(3), delta);
	}

	@Test
	public void aaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[L" + Classes.STRING + ClassConst.REF_SUFIX), 5);
		stack.pushRef(array);
		stack.pushInt(3);
		ObjectRef ref = new ObjectRef(loader.load(Classes.STRING));
		stack.pushRef(ref);
		xastore[7].execute(frame);
		assertSame(ref, array.getElements().getRef(3));
	}

}
