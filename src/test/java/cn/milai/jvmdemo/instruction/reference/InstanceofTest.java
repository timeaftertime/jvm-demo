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
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link Instruction} 测试类
 * @author milai
 * @date 2022.01.19
 */
public class InstanceofTest {

	private Instruction instanceOf;
	private BytecodeReader reader;
	private byte[] codes = { 0, 3 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		instanceOf = new Instanceof();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.ADD_TEST).getMethod(MethodConst.INIT, MethodConst.RETURN_VOID, false)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		ClassInfo classInfo = frame.getMethod().getClassInfo();
		instanceOf.readOperands(reader);
		frame.getOperandStack().pushRef(new ObjectRef(classInfo));
		instanceOf.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		frame.getOperandStack().pushRef(null);
		instanceOf.execute(frame);
		assertEquals(0, frame.getOperandStack().popInt());
	}
}
