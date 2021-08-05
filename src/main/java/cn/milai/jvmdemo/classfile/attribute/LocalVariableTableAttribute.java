package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * LocalVariableTable 属性，保存局部变量信息表
 * @author milai
 * @date 2021.08.05
 */
public class LocalVariableTableAttribute extends Attribute {

	private LocalVariableTable[] localVariableTables;

	public LocalVariableTableAttribute(int nameIndex, int length) { super(nameIndex, length); }

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		int localVariableTableLength = data.readUnsignedShort();
		localVariableTables = new LocalVariableTable[localVariableTableLength];
		for (int i = 0; i < localVariableTableLength; i++) {
			int startPc = data.readUnsignedShort();
			int length = data.readUnsignedShort();
			int nameIndex = data.readUnsignedShort();
			int descriptorIndex = data.readUnsignedShort();
			int index = data.readUnsignedShort();
			localVariableTables[i] = new LocalVariableTable(startPc, length, nameIndex, descriptorIndex, index);
		}
	}

}
