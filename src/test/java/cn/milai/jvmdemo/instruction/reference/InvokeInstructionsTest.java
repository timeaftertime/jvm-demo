package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.DefaultClassInfoLoaderInitializer;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.reference.InvokeInstructions.INVOKESTATIC;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link InvokeInstructions} 测试类
 * @author milai
 * @date 2021.12.15
 */
public class InvokeInstructionsTest {

	private static Instruction invokestatic;
	//	private static Instruction invokespecial;
	//	private static Instruction invokevirtual;
	//	private static Instruction invokeinterface;

	private ThreadSpace threadSpace;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = { 0, 5, 0, 4, 0, 7, 0, 0 };

	@BeforeClass
	public static void setUpClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		invokestatic = new INVOKESTATIC();
		//		invokespecial = new INVOKESPECIAL();
		//		invokevirtual = new INVOKEVIRTUAL();
		//		invokeinterface = new INVOKEINTERFACE();
	}

	@Before
	public void setUp() {
		threadSpace = new ThreadSpace();
		ClassInfo c = DefaultClassInfoLoader.getInstance().load(Classes.METHOD_INVOKE);
		threadSpace.invoke(c.getMethods()[1]);
		frame = threadSpace.currentFrame();
		reader = new BytecodeReader(codes);
	}

	@Test
	public void invokeStatic() throws IOException {
		invokestatic.readOperands(reader);
		OperandStack stack = frame.getOperandStack();
		ObjectRef ref = new ObjectRef(null, 0);
		stack.pushRef(ref);
		stack.pushInt(1);
		stack.pushInt(2);
		invokestatic.execute(frame);
		Frame frame = threadSpace.currentFrame();
		assertEquals("max", frame.getMethod().getName());
		assertEquals("(II)I", frame.getMethod().getDescriptor());
		assertEquals(1, frame.getLocalVarsTable().getInt(0));
		assertEquals(2, frame.getLocalVarsTable().getInt(1));
		invokestatic.readOperands(reader);
		try {
			invokestatic.execute(frame);
		} catch (IncompatibleClassChangeError e) {
			return;
		}
		fail();
	}

}
