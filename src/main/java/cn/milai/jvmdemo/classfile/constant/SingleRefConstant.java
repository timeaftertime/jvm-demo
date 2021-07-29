package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class SingleRefConstant implements Constant {

	protected int index;

	protected ConstantPool pool;

	@Override
	public void init(DataInputStream data, ConstantPool pool) throws IOException {
		index = data.readUnsignedShort();
		this.pool = pool;
	}

	public int getIndex() { return index; }

}
