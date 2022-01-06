package cn.milai.jvmdemo.runtime.slot;

import java.util.Arrays;

import cn.milai.jvmdemo.runtime.ObjectRef;

/**
 * {@link Slot} 列表抽象类
 * @author milai
 * @date 2021.09.15
 */
public abstract class SlotList {

	private Slot[] slots;

	public SlotList(int capacity) {
		this.slots = new Slot[capacity];
		reset();
	}

	protected void setInt(int index, int value) {
		accessIndexCheck(index, 0);
		slots[index] = new Slot(value);
	}

	protected void setLong(int index, long value) {
		accessIndexCheck(index, 1);
		setInt(index, (int) (value >> 32));
		setInt(index + 1, (int) value);
	}

	protected void setFloat(int index, float value) {
		setInt(index, Float.floatToIntBits(value));
	}

	protected void setDouble(int index, double value) {
		long v = Double.doubleToRawLongBits(value);
		accessIndexCheck(index, 1);
		setInt(index, (int) (v >> 32));
		setInt(index + 1, (int) v);
	}

	protected void setRef(int index, ObjectRef ref) {
		accessIndexCheck(index, 0);
		slots[index] = new Slot(ref);
	}

	protected ObjectRef getRef(int index) {
		accessIndexCheck(index, 0);
		return slots[index].getRef();
	}

	protected int getInt(int index) {
		accessIndexCheck(index, 0);
		return slots[index].getValue();
	}

	protected long getLong(int index) {
		accessIndexCheck(index, 1);
		return ((long) getInt(index) << 32) | (getInt(index + 1) & 0xFFFFFFFFL);
	}

	protected float getFloat(int index) {
		return Float.intBitsToFloat(getInt(index));
	}

	protected double getDouble(int index) {
		accessIndexCheck(index, 1);
		long v = ((long) getInt(index) << 32) | (getInt(index + 1) & 0xFFFFFFFFL);
		return Double.longBitsToDouble(v);
	}

	private void accessIndexCheck(int startIndex, int extraSpace) {
		int endIndex = startIndex + extraSpace;
		if (startIndex < 0 || startIndex >= slots.length) {
			throw new IndexOutOfBoundsException("startIndex: " + startIndex);
		}
		if (endIndex < 0 || endIndex >= slots.length) {
			throw new IndexOutOfBoundsException("endIndex: " + endIndex);
		}
	}

	public int getCapacity() { return slots.length; }

	protected void setSlot(int index, Slot slot) {
		accessIndexCheck(index, 0);
		slots[index] = slot;
	}

	protected Slot getSlot(int index) {
		accessIndexCheck(index, 0);
		return slots[index];
	}

	protected void clear() {
		reset();
	}

	private void reset() {
		for (int i = 0; i < slots.length; i++) {
			slots[i] = new Slot(null);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(slots);
	}

}