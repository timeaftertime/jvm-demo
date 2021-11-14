package cn.milai.jvmdemo.instruction;

/**
 * {@link Instruction} 工厂类
 * @author milai
 * @date 2021.11.14
 */
public class InstructionFactory {

	private InstructionFactory() {
	}

	public static final Instruction NOP = new cn.milai.jvmdemo.instruction.constant.NOP();

	/**
	 * 创建指定 {@code opcode} 的 {@link Instruction}
	 * @param opcode
	 * @return
	 */
	public static Instruction create(int opcode) {
		Opcode op = Opcode.find(opcode);
		if (op == null) {
			throw new IllegalArgumentException("未知操作码: " + opcode);
		}
		switch (op) {
			case NOP :
				return NOP;
			default:
				throw new IllegalArgumentException("未实现指令类型: " + op);
		}
	}

}
