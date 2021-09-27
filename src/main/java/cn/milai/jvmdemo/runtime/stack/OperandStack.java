package cn.milai.jvmdemo.runtime.stack;

import java.util.EmptyStackException;

import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.slot.Slot;
import cn.milai.jvmdemo.runtime.slot.SlotList;

/**
 * 操作数栈
 * @author milai
 * @date 2021.09.26
 */
public class OperandStack extends SlotList {

	private int size;

	public OperandStack(int capacity) {
		super(capacity);
		this.size = 0;
	}

	public void pushInt(int value) {
		pushCheck(0);
		setInt(size, value);
		size++;
	}

	public void pushLong(long value) {
		pushCheck(1);
		setLong(size, value);
		size += 2;
	}

	public void pushFloat(float value) {
		pushInt(Float.floatToIntBits(value));
	}

	public void pushDouble(double value) {
		pushLong(Double.doubleToLongBits(value));
	}

	public void pushRef(ObjectRef ref) {
		pushCheck(0);
		setRef(size, ref);
		size++;
	}

	public int popInt() {
		popCheck(1);
		size--;
		return getInt(size);
	}

	public long popLong() {
		popCheck(2);
		size -= 2;
		return getLong(size);
	}

	public float popFloat() {
		return Float.intBitsToFloat(popInt());
	}

	public double popDouble() {
		return Double.longBitsToDouble(popLong());
	}

	public ObjectRef popRef() {
		popCheck(1);
		size--;
		return getRef(size);
	}

	private void pushCheck(int space) {
		if (size + space >= getCapacity()) {
			throw new StackOverflowError();
		}
	}

	private void popCheck(int space) {
		if (size - space < 0) {
			throw new EmptyStackException();
		}
	}

	public int size() {
		return size;
	}

	public void pushSlot(Slot slot) {
		pushCheck(0);
		setSlot(size, slot);
		size++;
	}

	public Slot popSlot() {
		popCheck(1);
		size--;
		return getSlot(size);
	}

	/**
	 * 获取从栈顶开始第 index（>0） 个Slot 的 ObjectRef （不弹出栈）
	 * 
	 * @param index
	 * @return
	 */
	public ObjectRef getRefFromTop(int index) {
		return getRef(size - index);
	}

	public void clear() {
		size = 0;
		super.clear();
	}
}
