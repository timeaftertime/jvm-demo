package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.EmptyStackException;

import org.junit.BeforeClass;
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

	@BeforeClass
	public static void init() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
	}

	private ThreadSpace mockThreadFrom(Frame mockFrame) {
		return new ThreadSpace() {
			@Override
			public Frame currentFrame() {
				if (super.finished()) {
					return mockFrame;
				}
				return super.currentFrame();
			}
		};
	}

	private Frame executeMethod(String className, String methodName, String methodDesc, boolean isStatic)
		throws EmptyStackException, IOException {
		ClassInfo c = DefaultClassInfoLoader.getInstance().load(className);
		Frame mockFrame = new Frame(null, MockFactory.newMethod(0, 1));
		ThreadSpace threadSpace = mockThreadFrom(mockFrame);
		threadSpace.pushFrame(c.getMethod(methodName, methodDesc, isStatic));
		Interpreter.interpret(threadSpace);
		return mockFrame;
	}

	@Test
	public void testShowAddTest() throws IOException {
		Frame mockFrame = executeMethod(Classes.ADD_TEST, "addAndMul2", "()F", false);
		assertEquals(6, mockFrame.getOperandStack().popFloat(), 0.1);
	}

	@Test
	public void testSwitch() throws EmptyStackException, IOException {
		Frame mockFrame = executeMethod(Classes.SWITCH_TEST, "tableSwitch", "()I", true);
		assertEquals(6, mockFrame.getOperandStack().popInt(), 11);

		mockFrame = executeMethod(Classes.SWITCH_TEST, "lookupSwitch", "()I", true);
		assertEquals(6, mockFrame.getOperandStack().popInt(), 5);
	}
}
