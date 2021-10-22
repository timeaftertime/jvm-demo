package cn.milai.jvmdemo.classfile;

/**
 * 类型描述
 * @author milai
 * @date 2021.09.02
 */
public enum TypeDesc {

	VOID("V"),
	INT("I"),
	FLOAT("F"),
	LONG("J"),
	DOUBLE("D"),
	CHAR("C"),
	BOOLEAN("Z"),
	BYTE("B"),
	SHORT("S"),

	ARRAY("["),
	REF("L");

	private String value;

	TypeDesc(String value) {
		this.value = value;
	}

	public String getValue() { return value; }

	/**
	 * 获取指定类型描述字符串对应的 {@link TypeDesc} 枚举
	 * @param desc
	 * @return
	 * @throws 若参数不是合法类型描述字符串
	 */
	public static TypeDesc of(String desc) {
		if (desc.startsWith(REF.value) && desc.endsWith(";")) {
			return REF;
		}
		if (desc.startsWith(ARRAY.value)) {
			return ARRAY;
		}
		for (TypeDesc t : values()) {
			if (t != REF && t != ARRAY && t.value.equals(desc)) {
				return t;
			}
		}
		throw new IllegalArgumentException("未知类型描述:" + desc);
	}

	/**
	 * 当前类型是否需要 2 个相邻 slot
	 * @return
	 */
	public boolean needDoubleSlot() {
		return this == LONG || this == DOUBLE;
	}

}
