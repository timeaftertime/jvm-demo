package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link GetStatic} 测试类
 * @author milai
 * @date 2022.01.20
 */
public class GetStaticTest {

	private Instruction getstatic;
	private BytecodeReader reader;
	private byte[] codes = { 0, 3 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		getstatic = new GetStatic();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.PUT_AND_GET_FIELD).getMethod("incB", "(I)V", true)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		getstatic.readOperands(reader);
		classInfo.getStaticSlots().setInt(0, 10);
		getstatic.execute(frame);
		assertEquals(10, frame.getOperandStack().popInt());
	}

}
