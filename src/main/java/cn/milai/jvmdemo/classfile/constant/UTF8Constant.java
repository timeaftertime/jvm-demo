package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public class UTF8Constant extends ValueConstant<String> {

	UTF8Constant() {}

	@Override
	public ConstantTag tag() { return ConstantTag.UTF8; }

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException { value = data.readUTF(); }

}
