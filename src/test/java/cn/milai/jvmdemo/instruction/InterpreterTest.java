package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.DefaultClassInfoLoaderInitializer;
import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link Interpreter} 测试类
 * @author milai
 * @date 2021.11.28
 */
public class InterpreterTest {

	@Test
	public void testShowAddTest() throws IOException {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		ClassInfo addTest = DefaultClassInfoLoader.getInstance().load(Classes.ADD_TEST);
		Frame mockFrame = new Frame(null, MockFactory.newMethod(0, 1));
		ThreadSpace threadSpace = new ThreadSpace() {
			@Override
			public Frame currentFrame() {
				if (super.finished()) {
					return mockFrame;
				}
				return super.currentFrame();
			}
		};
		threadSpace.pushFrame(addTest.getMethod("addAndMul2", "()F", false));
		Interpreter.interpret(threadSpace);
		assertEquals(6, mockFrame.getOperandStack().popFloat(), 0.1);
	}
}
