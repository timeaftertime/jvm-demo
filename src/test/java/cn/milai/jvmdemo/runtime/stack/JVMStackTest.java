package cn.milai.jvmdemo.runtime.stack;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;

/**
 * {@link JVMStack} 测试类
 * @author milai
 * @date 2021.09.24
 */
public class JVMStackTest {

	private static final int MAX_STACK_SIZE = 10;
	private JVMStack stack;

	@Before
	public void setUp() {
		stack = new JVMStack(MAX_STACK_SIZE);
	}

	@Test
	public void pushAndPop() {
		int len = 10;
		Frame[] frames = new Frame[len];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Frame(null, MockFactory.newMethod(i, i));
		}
		for (int i = 0; i < frames.length; i++) {
			stack.push(frames[i]);
		}
		for (int i = frames.length - 1; i >= 0; i--) {
			assertSame(frames[i], stack.pop());
		}
	}

	@Test(expected = StackOverflowError.class)
	public void stackOverflow() {
		for (int i = 0; i <= MAX_STACK_SIZE; i++) {
			stack.push(null);
		}
	}

}
