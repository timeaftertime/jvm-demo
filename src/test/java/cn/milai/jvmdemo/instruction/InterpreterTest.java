package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.EmptyStackException;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.DefaultClassInfoLoaderInitializer;
import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.MethodConst;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.OperandStack;

/**
 * {@link Interpreter} 测试类
 * @author milai
 * @date 2021.11.28
 */
public class InterpreterTest {

	private static ClassInfoLoader loader;

	@BeforeClass
	public static void init() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		loader = DefaultClassInfoLoader.getInstance();
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
		return executeMethod(new Frame(null, MockFactory.newMethod(0, 1)), className, methodName, methodDesc, isStatic);
	}

	private Frame executeMethod(Frame mockFrame, String className, String methodName, String methodDesc,
		boolean isStatic) throws EmptyStackException, IOException {
		ClassInfo c = DefaultClassInfoLoader.getInstance().load(className);
		ThreadSpace threadSpace = mockThreadFrom(mockFrame);
		threadSpace.invoke(c.getMethod(methodName, methodDesc, isStatic));
		if (MethodConst.isClinit(threadSpace.currentFrame().getMethod())) {
			threadSpace.popFrame();
		}
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

	@Test
	public void testCall() throws EmptyStackException, IOException {
		{
			OperandStack s = new OperandStack(3);
			s.pushRef(new ObjectRef(loader.load(Classes.CALL_METHODS)));
			s.pushInt(3);
			s.pushInt(2);
			Frame frame = MockFactory.newFrame(s, null, MockFactory.newMethod(""));
			frame = executeMethod(frame, Classes.CALL_METHODS, "call", "(II)D", false);
			assertEquals(13.5, frame.getOperandStack().popDouble(), 0.1);
		}
		{
			OperandStack s = new OperandStack(3);
			s.pushRef(new ObjectRef(loader.load(Classes.CALL_METHODS)));
			s.pushInt(1);
			s.pushInt(-1);
			Frame frame = MockFactory.newFrame(s, null, MockFactory.newMethod(""));
			frame = executeMethod(frame, Classes.CALL_METHODS, "call", "(II)D", false);
			assertEquals(0, frame.getOperandStack().popDouble(), 0.1);
		}
	}

}
