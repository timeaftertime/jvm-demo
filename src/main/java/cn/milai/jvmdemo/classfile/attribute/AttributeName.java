package cn.milai.jvmdemo.classfile.attribute;

/**
 * {@link Attribute} name 枚举
 * @author milai
 * @date 2021.08.05
 */
public enum AttributeName {

	CODE("Code"),
	CONSTANT_VALUE("ConstantValue"),
	DEPRECATED("Deprecated"),
	EXCEPTIONS("Exceptions"),
	LINE_NUMBER_TABLE("LineNumberTable"),
	LOCAL_VARIABLE_TABLE("LocalVariableTable"),
	SOURCE_FILE("SourceFile"),
	SYNTHETIC("Synthetic"),
	;

	AttributeName(String name) { this.name = name; }

	private String name;

	public static AttributeName find(String name) {
		for (AttributeName a : AttributeName.values()) {
			if (a.name.equals(name)) {
				return a;
			}
		}
		return null;
	}

}
