package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class IntegerConstant extends ValueConstant<Integer> {

	IntegerConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.INTEGER; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException { this.value = data.readInt(); }

}
