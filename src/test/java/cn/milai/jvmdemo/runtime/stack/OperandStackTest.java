package cn.milai.jvmdemo.runtime.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.slot.Slot;

/**
 * {@link OperandStack} 测试类
 * @author milai
 * @date 2021.09.27
 */
public class OperandStackTest {

	private final int STACK_CAPACITY = 10;
	private OperandStack operandStack;

	@Before
	public void setUp() {
		operandStack = new OperandStack(STACK_CAPACITY);
	}

	@Test
	public void pushAndPop() {
		int var1 = 1;
		long var2 = 12345678901L;
		float var3 = 3.14f;
		double var4 = 3.1415926;
		ObjectRef var5 = new ObjectRef();
		double delta = 0.01;
		operandStack.pushInt(var1);
		operandStack.pushLong(var2);
		operandStack.pushFloat(var3);
		operandStack.pushDouble(var4);
		operandStack.pushRef(var5);
		assertEquals(var5, operandStack.popRef());
		assertEquals(var4, operandStack.popDouble(), delta);
		assertEquals(var3, operandStack.popFloat(), delta);
		assertEquals(var2, operandStack.popLong());
		assertEquals(var1, operandStack.popInt());
	}

	@Test
	public void emptyExceptionAndFullError() {
		try {
			operandStack.popInt();
		} catch (EmptyStackException e1) {
			try {
				for (int i = 0; i <= STACK_CAPACITY; i++) {
					operandStack.pushInt(i);
				}
			} catch (StackOverflowError e2) {
				return;
			}
		}
		fail();
	}

	@Test
	public void pushAndPopSlot() {
		operandStack.pushSlot(new Slot(1));
		Slot slot = operandStack.popSlot();
		assertEquals(1, slot.getValue());
		assertSame(null, slot.getRef());
	}

}
