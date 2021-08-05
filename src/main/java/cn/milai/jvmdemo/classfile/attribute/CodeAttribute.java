package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * Code 属性，包含代码信息
 * @author milai
 * @date 2021.08.05
 */
public class CodeAttribute extends Attribute {

	private int maxStack;
	private int maxLocal;
	private byte[] codes;
	private ExceptionTable[] exceptionTables;
	private Attribute[] attributes;

	public CodeAttribute(int nameIndex, int length) { super(nameIndex, length); }

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		maxStack = data.readUnsignedShort();
		maxLocal = data.readUnsignedShort();
		int codeLength = data.readInt();
		codes = new byte[codeLength];
		data.read(codes);
		readExceptionTables(data);
		attributes = Attributes.readAttributes(data, constantPool);
	}

	private void readExceptionTables(DataInputStream data) throws IOException {
		int cnt = data.readUnsignedShort();
		exceptionTables = new ExceptionTable[cnt];
		for (int i = 0; i < cnt; i++) {
			int startPc = data.readUnsignedShort();
			int endPc = data.readUnsignedShort();
			int handlerPc = data.readUnsignedShort();
			int catchType = data.readUnsignedShort();
			exceptionTables[i] = new ExceptionTable(startPc, endPc, handlerPc, catchType);
		}
	}

	public int getMaxLocal() { return maxLocal; }

	public int getMaxStack() { return maxStack; }

	public byte[] getCodes() { return codes; }

	public ExceptionTable[] getExceptionAttributes() { return exceptionTables; }

	public LineNumberTableAttribute getLineNumberAttribute() {
		for (Attribute attr : attributes) {
			if (attr instanceof LineNumberTableAttribute)
				return (LineNumberTableAttribute) attr;
		}
		return null;
	}
}
