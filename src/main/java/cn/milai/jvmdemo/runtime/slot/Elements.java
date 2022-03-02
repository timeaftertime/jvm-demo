package cn.milai.jvmdemo.runtime.slot;

/**
 * 数组元素
 * @author milai
 * @date 2022.01.06
 */
public class Elements extends TableSlots {

	public Elements(int capacity) {
		super(capacity);
	}

	@Override
	public long getLong(int index) {
		return super.getLong(index * 2);
	}

	@Override
	public void setLong(int index, long value) {
		super.setLong(index * 2, value);
	}

	@Override
	public double getDouble(int index) {
		return super.getDouble(index * 2);
	}

	@Override
	public void setDouble(int index, double value) {
		super.setDouble(index * 2, value);
	}

	@Override
	public int getCapacity() { return super.getCapacity(); }
}
