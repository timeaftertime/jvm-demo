package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.DefaultClassInfoLoaderInitializer;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.MethodConst;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link New} 测试类
 * @author milai
 * @date 2021.12.25
 */
public class NewTest {

	private Instruction newIns;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;

	private static ClassInfo info;

	@BeforeClass
	public static void setUpClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		info = DefaultClassInfoLoader.getInstance().load(Classes.ADD_TEST);
	}

	@Before
	public void setUp() {
		newIns = new New();
		reader = new BytecodeReader(codes);
		frame = new Frame(new ThreadSpace(), info.getMethod(MethodConst.INIT, MethodConst.RETURN_VOID, false));
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		newIns.readOperands(reader);
		if (!info.isInitialized()) {
			newIns.execute(frame);
			assertEquals(0, frame.getOperandStack().size());
		}
		newIns.execute(frame);
		ObjectRef ref = frame.getOperandStack().popRef();
		assertEquals(Classes.ADD_TEST, ref.getClassInfo().getName());
		assertEquals(0, ref.getFields().getCapacity());
	}
}
