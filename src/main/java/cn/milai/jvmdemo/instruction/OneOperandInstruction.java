package cn.milai.jvmdemo.instruction;

/**
 * 可拓展操作数大小的 {@link Instruction}
 * @author milai
 * @date 2022.02.01
 */
public abstract class OneOperandInstruction implements Instruction {

	protected int operand;

	/**
	 * 设置操作数栈
	 * @param operand
	 */
	public void setOperand(int operand) { this.operand = operand; }

}
