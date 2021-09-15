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
	DOUBLE("D");

	private String value;

	TypeDesc(String value) {
		this.value = value;
	}

	public String getValue() { return value; }

}
