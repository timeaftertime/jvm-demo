package cn.milai.jvmdemo.runtime.slot;

import cn.milai.jvmdemo.runtime.ObjectRef;

/**
 * 表类型的 {@link Slot} 管理器
 * @author milai
 * @date 2021.10.21
 */
public class TableSlots extends SlotList {

	public TableSlots(int capacity) {
		super(capacity);
	}

	@Override
	public void setInt(int index, int value) {
		super.setInt(index, value);
	}

	@Override
	public void setLong(int index, long value) {
		super.setLong(index, value);
	}

	@Override
	public void setFloat(int index, float value) {
		super.setFloat(index, value);
	}

	@Override
	public void setDouble(int index, double value) {
		super.setDouble(index, value);
	}

	@Override
	public void setRef(int index, ObjectRef ref) {
		super.setRef(index, ref);
	}

	@Override
	public int getInt(int index) {
		return super.getInt(index);
	}

	@Override
	public long getLong(int index) {
		return super.getLong(index);
	}

	@Override
	public float getFloat(int index) {
		return super.getFloat(index);
	}

	@Override
	public double getDouble(int index) {
		return super.getDouble(index);
	}

	@Override
	public ObjectRef getRef(int index) {
		return super.getRef(index);
	}
}
