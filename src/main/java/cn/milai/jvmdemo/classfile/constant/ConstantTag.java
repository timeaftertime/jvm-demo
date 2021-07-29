package cn.milai.jvmdemo.classfile.constant;

/**
 * Class 常量 Tag
 * @author milai
 * @date 2021.07.29
 */
public enum ConstantTag {

	UTF8(1),
	INTEGER(3),
	FLOAT(4),
	LONG(5),
	DOUBLE(6),
	CLASS(7),
	STRING(8),
	FIELDREF(9),
	METHODREF(10),
	INTERFACE_METHODREF(11),
	NAME_AND_TYPE(12),
	METHOD_HANDLE(15),
	METHOD_TYPE(16),
	INVOKE_DYNAMIC(18);

	private int value;

	private ConstantTag(int value) { this.value = value; }

	public int getValue() { return value; }

	/**
	 * 获取指定 value 对应的 {@link ConstantTag}，若不存在，抛出异常
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static ConstantTag of(int value) throws IllegalArgumentException {
		for (ConstantTag c : ConstantTag.values()) {
			if (c.value == value) {
				return c;
			}
		}
		throw new IllegalArgumentException(value + "");
	}

}
