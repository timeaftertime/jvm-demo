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
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link PutField} 测试类
 * @author milai
 * @date 2022.01.20
 */
public class PutFieldTest {

	private Instruction putfield;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		putfield = new PutField();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.PUT_AND_GET_FIELD).getMethod("incA", "(I)V", false)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		putfield.readOperands(reader);
		ObjectRef ref = new ObjectRef(classInfo);
		frame.getOperandStack().pushRef(ref);
		frame.getOperandStack().pushInt(10);
		putfield.execute(frame);
		assertEquals(10, ref.getFields().getInt(0));
	}

}
