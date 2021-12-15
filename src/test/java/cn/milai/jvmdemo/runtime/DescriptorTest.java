package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * {@link Descriptor} 测试类
 * @author milai
 * @date 2021.12.15
 */
public class DescriptorTest {

	@Test
	public void testParse() {
		Descriptor d1 = new Descriptor("(Ljava/lang/String;)V");
		assertEquals("V", d1.getReturnType());
		assertEquals(1, d1.getArgsSlotCnt());
		assertArrayEquals(new String[] { "Ljava/lang/String;" }, d1.getArgsType());
		Descriptor d2 = new Descriptor("()I");
		assertEquals("I", d2.getReturnType());
		assertEquals(0, d2.getArgsSlotCnt());
		assertArrayEquals(new String[] {}, d2.getArgsType());
		Descriptor d3 = new Descriptor("(DI)V");
		assertEquals("V", d3.getReturnType());
		assertEquals(3, d3.getArgsSlotCnt());
		assertArrayEquals(new String[] { "D", "I" }, d3.getArgsType());
	}
}
