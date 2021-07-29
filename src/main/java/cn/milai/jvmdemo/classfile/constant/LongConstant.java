package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class LongConstant extends ValueConstant<Long> {

	LongConstant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.LONG; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException { this.value = data.readLong(); }

}
