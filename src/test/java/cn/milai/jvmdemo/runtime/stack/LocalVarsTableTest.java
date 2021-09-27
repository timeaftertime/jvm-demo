package cn.milai.jvmdemo.runtime.stack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.runtime.ObjectRef;

/**
 * {@link LocalVarsTable} 测试类
 * @author milai
 * @date 2021.09.27
 */
public class LocalVarsTableTest {

	private int TABLE_CAPACITY = 10;
	private LocalVarsTable table;

	@Before
	public void setUp() {
		table = new LocalVarsTable(TABLE_CAPACITY);
	}

	@Test
	public void setAndGet() {
		int index = 0;
		int var1 = 2;
		long var2 = 111222333444555L;
		float var3 = 11.22f;
		double var4 = 333.444;
		ObjectRef var5 = new ObjectRef();
		table.setInt(index, var1);
		index++;
		table.setLong(index, var2);
		index += 2;
		table.setFloat(index, var3);
		index++;
		table.setDouble(index, var4);
		index += 2;
		table.setRef(index, var5);
		index++;
		double delta = 0.01;
		index--;
		assertEquals(var5, table.getRef(index));
		index -= 2;
		assertEquals(var4, table.getDouble(index), delta);
		index--;
		assertEquals(var3, table.getFloat(index), delta);
		index -= 2;
		assertEquals(var2, table.getLong(index));
		index--;
		assertEquals(var1, table.getInt(index));
	}

}
