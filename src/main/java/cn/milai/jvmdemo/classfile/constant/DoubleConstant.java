package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleConstant extends ValueConstant<Double> {

	DoubleConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.DOUBLE; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException { this.value = data.readDouble(); }

}
