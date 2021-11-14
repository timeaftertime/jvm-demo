package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import cn.milai.jvmdemo.instruction.constant.NOP;

/**
 * {@link InstructionFactory} 测试类
 * @author milai
 * @date 2021.11.14
 */
public class InstructionFactoryTest {

	@Test
	public void testCreate() {
		assertSame(InstructionFactory.NOP, InstructionFactory.create(Opcode.NOP.getValue()));
		assertSame(InstructionFactory.NOP, InstructionFactory.create(Opcode.NOP.getValue()));
		assertTrue(InstructionFactory.create(0) instanceof NOP);
		try {
			InstructionFactory.create(0xff + 1);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail();
	}

}
