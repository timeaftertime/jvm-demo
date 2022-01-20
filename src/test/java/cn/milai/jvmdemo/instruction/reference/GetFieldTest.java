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
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link GetField} 测试类
 * @author milai
 * @date 2022.01.20
 */
public class GetFieldTest {

	private Instruction getfield;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		getfield = new GetField();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.PUT_AND_GET_FIELD).getMethod("incA", "(I)V", false)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		getfield.readOperands(reader);
		ObjectRef ref = new ObjectRef(classInfo);
		ref.getFields().setInt(0, 12);
		frame.getOperandStack().pushRef(ref);
		getfield.execute(frame);
		assertEquals(12, frame.getOperandStack().popInt());
	}
}
