package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * SourceFile 属性，保存源文件名
 * @author milai
 * @date 2021.08.05
 */
public class SourceFileAttribute extends Attribute {

	private int sourceFileIndex;

	public SourceFileAttribute(int nameIndex, int length) {
		super(nameIndex, length);
		if (length != 2)
			throw new AssertionError("length 必须为 2：" + length);
	}

	@Override
	public void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException {
		sourceFileIndex = data.readUnsignedShort();
	}

	public int getSourceFileIndex() { return sourceFileIndex; }

}
