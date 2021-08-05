package cn.milai.jvmdemo.classfile.attribute;

/**
 * 异常处理表
 * @author milai
 * @date 2021.08.05
 */
public class ExceptionTable {

	private int startPC;
	private int endPC;
	private int handlerPC;
	private int catchType;

	public ExceptionTable(int startPC, int endPC, int handlerPC, int catchType) {
		this.startPC = startPC;
		this.endPC = endPC;
		this.handlerPC = handlerPC;
		this.catchType = catchType;
	}

	public int getStartPC() { return startPC; }

	public int getEndPC() { return endPC; }

	public int getHandlerPC() { return handlerPC; }

	public int getCatchType() { return catchType; }

}
