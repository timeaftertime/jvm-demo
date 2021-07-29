package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class FloatConstant extends ValueConstant<Float> {

	FloatConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.FLOAT; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException { this.value = data.readFloat(); }

}
