package cn.milai.jvmdemo.classfile.constant;

public abstract class ValueConstant<T> implements Constant {

	protected T value;

	public T getValue() { return value; }

	@Override
	public String toString() { return "[value=" + value + "]"; }
}
