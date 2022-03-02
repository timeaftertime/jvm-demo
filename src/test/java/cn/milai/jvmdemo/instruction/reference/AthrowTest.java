package cn.milai.jvmdemo.instruction.reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.TestClassInfoLoader;
import cn.milai.jvmdemo.constants.MethodConst;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link Athrow} 测试类
 * @author milai
 * @date 2022.03.01
 */
public class AthrowTest {

	@Test
	public void athow() throws IOException {
		Instruction athow = new Athrow();
		athow.readOperands(null);
		ThreadSpace space = new ThreadSpace();
		ClassInfoLoader loader = TestClassInfoLoader.get();
		space.invoke(loader.load(Classes.EXCEPTION_HANDLE).getMethod("get", "()I", true));
		if (MethodConst.isClinit(space.currentFrame().getMethod())) {
			space.popFrame();
		}
		Frame frame = space.currentFrame();
		OperandStack stack = frame.getOperandStack();
		frame.setCurrentPC(3);
		ObjectRef reEx = new ObjectRef(loader.load(Classes.RUNTIME_EXCEPTION));
		stack.pushRef(reEx);
		athow.execute(frame);
		assertFalse(space.finished());
		assertEquals(10, frame.getCurrentPC());
		assertSame(reEx, stack.popRef());
	}

}
