package cn.milai.jvmdemo.instruction;

/**
 * 操作指令码
 * @author milai
 * @date 2021.11.14
 */
public enum Opcode {

	NOP(0x0);

	private int value;

	Opcode(int value) {
		this.value = value;
	}

	public int getValue() { return value; }

	/**
	 * 获取 {@code value} 对应的 {@link Opcode}，如不存在，返回 {@code null}
	 * @param value
	 * @return
	 */
	public static Opcode find(int value) {
		for (Opcode o : values()) {
			if (o.value == value) {
				return o;
			}
		}
		return null;
	}
}
