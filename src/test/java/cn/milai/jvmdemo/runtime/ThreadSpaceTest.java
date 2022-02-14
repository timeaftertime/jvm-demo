package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link ThreadSpace} 测试类
 * @author milai
 * @date 2021.09.27
 */
public class ThreadSpaceTest {

	@Test
	public void pushAndPopFrame() {
		ThreadSpace space = new ThreadSpace();
		int len = 10;
		for (int i = 0; i < len; i++) {
			space.invoke(MockFactory.newMethod(i, i));
		}
		for (int i = len - 1; i >= 0; i--) {
			Frame frame = space.popFrame();
			assertSame(i, frame.getLocalVarsTable().getCapacity());
			assertSame(i, frame.getOperandStack().getCapacity());
		}
	}

}
