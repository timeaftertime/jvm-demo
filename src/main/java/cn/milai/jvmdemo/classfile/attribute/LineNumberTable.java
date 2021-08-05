package cn.milai.jvmdemo.classfile.attribute;

/**
 * 字节码、源码行对应关系表
 * @author milai
 * @date 2021.08.05
 */
public class LineNumberTable {

	private int startPC;
	private int lineNumber;

	public LineNumberTable(int startPC, int lineNumber) {
		this.startPC = startPC;
		this.lineNumber = lineNumber;
	}

	public int getStartPC() { return startPC; }

	public int getLineNumber() { return lineNumber; }

}
