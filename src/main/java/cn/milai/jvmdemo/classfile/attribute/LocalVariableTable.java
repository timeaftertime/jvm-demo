package cn.milai.jvmdemo.classfile.attribute;

/**
 * 局部变量信息表
 * @author milai
 * @date 2021.08.05
 */
public class LocalVariableTable {

	private int startPC;
	private int length;
	private int nameIndex;
	private int descriptorIndex;
	private int index;

	public LocalVariableTable(int startPC, int length, int nameIndex, int descriptorIndex, int index) {
		this.startPC = startPC;
		this.length = length;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.index = index;
	}

	public int getStartPC() { return startPC; }

	public int getLength() { return length; }

	public int getNameIndex() { return nameIndex; }

	public int getDescriptorIndex() { return descriptorIndex; }

	public int getIndex() { return index; }

}
