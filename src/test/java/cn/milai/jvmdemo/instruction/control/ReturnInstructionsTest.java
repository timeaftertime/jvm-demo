package cn.milai.jvmdemo.instruction.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.ARETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.DRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.FRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.IRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.LRETURN;
import cn.milai.jvmdemo.instruction.control.ReturnInstructions.RETURN;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.util.MockFactory;

/**
 * {@link ReturnInstructions} 测试类
 * @author milai
 * @date 2021.11.28
 */
public class ReturnInstructionsTest {

	private Instruction[] x_returns;

	@Before
	public void setUp() {
		x_returns = new Instruction[] {
			new RETURN(), new IRETURN(), new FRETURN(), new LRETURN(), new DRETURN(), new ARETURN()
		};
	}

	@Test
	public void readOperands() throws IOException {
		for (int i = 0; i < x_returns.length; i++) {
			x_returns[i].readOperands(null);
		}
	}

	@Test
	public void execute() {
		double delta = 0.01;
		ThreadSpace space = new ThreadSpace();
		for (int i = 0; i < x_returns.length; i++) {
			space.invoke(MockFactory.newMethod(0, 2));
		}
		space.currentFrame().getOperandStack().pushInt(10);
		space.currentFrame().setReturnPC(99);
		x_returns[1].execute(space.currentFrame());
		assertEquals(10, space.currentFrame().getOperandStack().popInt());
		assertEquals(99, space.getPC());

		space.currentFrame().getOperandStack().pushFloat(3.14f);
		space.currentFrame().setReturnPC(1);
		x_returns[2].execute(space.currentFrame());
		assertEquals(3.14f, space.currentFrame().getOperandStack().popFloat(), delta);
		assertEquals(1, space.getPC());

		space.currentFrame().getOperandStack().pushLong(9876543210L);
		space.currentFrame().setReturnPC(9);
		x_returns[3].execute(space.currentFrame());
		assertEquals(9876543210L, space.currentFrame().getOperandStack().popLong());
		assertEquals(9, space.getPC());

		space.currentFrame().getOperandStack().pushDouble(999.666);
		space.currentFrame().setReturnPC(22);
		x_returns[4].execute(space.currentFrame());
		assertEquals(999.666, space.currentFrame().getOperandStack().popDouble(), delta);
		assertEquals(22, space.getPC());

		ObjectRef ref = new ObjectRef(MockFactory.classInfoWithSlotCnt(0));
		space.currentFrame().getOperandStack().pushRef(ref);
		space.currentFrame().setReturnPC(7);
		x_returns[5].execute(space.currentFrame());
		assertSame(ref, space.currentFrame().getOperandStack().popRef());
		assertEquals(7, space.getPC());

		x_returns[0].execute(space.currentFrame());
		assertNull(space.currentFrame());
	}

}
