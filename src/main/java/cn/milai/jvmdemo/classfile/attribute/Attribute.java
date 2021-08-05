package cn.milai.jvmdemo.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * 属性
 * @author milai
 * @date 2021.08.03
 */
public abstract class Attribute {

	protected int nameIndex;
	protected int length;

	public Attribute(int nameIndex, int length) {
		this.nameIndex = nameIndex;
		this.length = length;
	}

	public abstract void init(int attrLen, DataInputStream data, ConstantPool constantPool) throws IOException;

}
