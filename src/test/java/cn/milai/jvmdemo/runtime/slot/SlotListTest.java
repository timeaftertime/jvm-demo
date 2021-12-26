package cn.milai.jvmdemo.runtime.slot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.runtime.ObjectRef;

/**
 * {@link SlotList} 测试类
 * @author milai
 * @date 2021.09.15
 */
public class SlotListTest {

	private static final int SLOTS_CAPACITY = 10;
	private SlotList slots;

	@Before
	public void setUp() {
		slots = new SlotList(SLOTS_CAPACITY) {};
	}

	@Test
	public void setAndGet() {
		Slot slot = new Slot(1);
		slots.setSlot(0, slot);
		assertSame(slot, slots.getSlot(0));
	}

	@Test
	public void setAndGetSlots() {
		int index = 0;
		int var1 = 10;
		long var2 = 123456789123L;
		float var3 = 1234.56f;
		double var4 = 98765432.1;
		ObjectRef var5 = new ObjectRef(MockFactory.classInfoWithSlotCnt(0));
		slots.setInt(index, var1);
		index++;
		slots.setLong(index, var2);
		index += 2;
		slots.setFloat(index, var3);
		index++;
		slots.setDouble(index, var4);
		index += 2;
		slots.setRef(index, var5);
		index++;
		double delta = 0.01;
		index--;
		assertEquals(var5, slots.getRef(index));
		index -= 2;
		assertEquals(var4, slots.getDouble(index), delta);
		index--;
		assertEquals(var3, slots.getFloat(index), delta);
		index -= 2;
		assertEquals(var2, slots.getLong(index));
		index--;
		assertEquals(var1, slots.getInt(index));
	}

	@Test
	public void illegalIndexAccessError() {
		try {
			slots.getInt(-1);
		} catch (IndexOutOfBoundsException e2) {
			try {
				slots.getInt(SLOTS_CAPACITY);
			} catch (IndexOutOfBoundsException e3) {
				return;
			}
		}
		fail();
	}

}