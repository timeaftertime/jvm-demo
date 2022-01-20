package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertSame;
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

/**
 * {@link CheckCast} 测试类
 * @author milai
 * @date 2022.01.19
 */
public class CheckCastTest {

	private Instruction checkCast;
	private BytecodeReader reader;
	private byte[] codes = { 0, 2 };
	private Frame frame;
	private ClassInfoLoader loader = TestClassInfoLoader.get();

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		checkCast = new CheckCast();
		reader = new BytecodeReader(codes);
		frame = new Frame(
			new ThreadSpace(), loader.load(Classes.ADD_TEST).getMethod(MethodConst.INIT, MethodConst.RETURN_VOID, false)
		);
	}

	@Test
	public void readOperandsAndExecute() throws IOException, ClassNotFoundException {
		checkCast.readOperands(reader);
		ObjectRef ref = new ObjectRef(frame.getMethod().getClassInfo());
		frame.getOperandStack().pushRef(ref);
		checkCast.execute(frame);
		assertSame(ref, frame.getOperandStack().popRef());
		frame.getOperandStack().pushRef(new ObjectRef(loader.load(Classes.HELLO_WORLD)));
		try {
			checkCast.execute(frame);
		} catch (ClassCastException e) {
			return;
		}
		fail();
	}

}
