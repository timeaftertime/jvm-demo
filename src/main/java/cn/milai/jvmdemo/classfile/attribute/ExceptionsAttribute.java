package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * Exceptions 属性，记录方法 throws 后面的异常
 * @author milai
 * @date 2021.08.05
 */
public class ExceptionsAttribute extends Attribute {

	private int[] exceptionIndexTable;

	public ExceptionsAttribute(int nameIndex, int length) { super(nameIndex, length); }

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		int cnt = data.readUnsignedShort();
		exceptionIndexTable = new int[cnt];
		for (int i = 0; i < cnt; i++) {
			exceptionIndexTable[i] = data.readUnsignedShort();
		}
	}

}
