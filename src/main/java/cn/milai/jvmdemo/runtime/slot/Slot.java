package cn.milai.jvmdemo.runtime.slot;

import cn.milai.jvmdemo.runtime.ObjectRef;

/**
 * Slot 存储单元
 * @author milai
 * @date 2021.09.15
 */
public class Slot {

	private int value;
	private ObjectRef ref;

	public Slot(int value) {
		this.value = value;
	}

	public Slot(ObjectRef ref) {
		this.ref = ref;
	}

	public int getValue() { return value; }

	public ObjectRef getRef() { return ref; }

	@Override
	public String toString() {
		return "Slot [value=" + value + ", ref=" + ref + "]";
	}

}