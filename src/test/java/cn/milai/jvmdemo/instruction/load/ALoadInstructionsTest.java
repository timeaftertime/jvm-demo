package cn.milai.jvmdemo.instruction.load;

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
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.AALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.BALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.CALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.DALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.FALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.IALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.LALOAD;
import cn.milai.jvmdemo.instruction.load.ALoadInstructions.SALOAD;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link ALoadInstructions} 测试类
 * @author milai
 * @date 2022.01.06
 */
public class ALoadInstructionsTest {

	private Instruction[] xaload;
	private static ClassInfoLoader loader;
	ThreadSpace threadSpace;

	@BeforeClass
	public static void setUpClass() {
		TestClassInfoLoader.initDefaultClassInfoLoader();
		loader = DefaultClassInfoLoader.getInstance();
	}

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		xaload = new Instruction[8];
		xaload[0] = new BALOAD();
		xaload[1] = new CALOAD();
		xaload[2] = new SALOAD();
		xaload[3] = new IALOAD();
		xaload[4] = new FALOAD();
		xaload[5] = new LALOAD();
		xaload[6] = new DALOAD();
		xaload[7] = new AALOAD();
		threadSpace = new ThreadSpace();
		threadSpace.invoke(MockFactory.newMethod(0, 2));
	}

	@Test
	public void readNoOperand() throws IOException {
		for (int i = 0; i < xaload.length; i++) {
			xaload[i].readOperands(null);
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
		stack.pushInt(2);
		xaload[0].execute(frame);
		assertEquals(2, stack.popInt());
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
		stack.pushInt(2);
		xaload[0].execute(frame);
		assertEquals(2, stack.popInt());
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
		stack.pushInt(2);
		xaload[0].execute(frame);
		assertEquals(2, stack.popInt());
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
		stack.pushInt(2);
		xaload[0].execute(frame);
		assertEquals(2, stack.popInt());
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
		xaload[4].execute(frame);
		assertEquals(3.14f, stack.popFloat(), delta);
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
		xaload[5].execute(frame);
		assertEquals(1234567893L, stack.popLong());
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
		xaload[6].execute(frame);
		assertEquals(3.999, stack.popDouble(), delta);
	}

	@Test
	public void aaload() throws ClassNotFoundException, IOException {
		Frame frame = threadSpace.currentFrame();
		OperandStack stack = frame.getOperandStack();
		ObjectRef array = new ObjectRef(loader.load("[L" + Classes.STRING + ClassConst.REF_SUFIX), 5);
		stack.pushRef(array);
		stack.pushInt(3);
		for (int i = 0; i < array.length(); i++) {
			array.getElements().setRef(i, new ObjectRef(loader.load(Classes.STRING)));
		}
		xaload[7].execute(frame);
		assertSame(array.getElements().getRef(3), stack.popRef());
	}

}
