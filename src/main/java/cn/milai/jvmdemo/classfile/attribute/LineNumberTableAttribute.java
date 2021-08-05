package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * LineNumberTable 属性，保存字节码、源码行对应关系列表
 * @author milai
 * @date 2021.08.05
 */
public class LineNumberTableAttribute extends Attribute {

	private LineNumberTable[] lineNumberTables;

	public LineNumberTableAttribute(int nameIndex, int length) { super(nameIndex, length); }

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		int lineNumberTableLength = data.readUnsignedShort();
		lineNumberTables = new LineNumberTable[lineNumberTableLength];
		for (int i = 0; i < lineNumberTableLength; i++) {
			int startPc = data.readUnsignedShort();
			int lineNumber = data.readUnsignedShort();
			lineNumberTables[i] = new LineNumberTable(startPc, lineNumber);
		}
	}

	public LineNumberTable[] getLineNumberTables() { return lineNumberTables; }

}
