package cn.milai.jvmdemo.constants;

import java.util.Arrays;
import java.util.List;

import cn.milai.jvmdemo.classfile.TypeDesc;

/**
 * 类相关常量
 * @author milai
 * @date 2022.01.05
 */
public class ClassConst {

	public static final String ARRAY_PREFIX = "[";

	public static final String REF_SUFIX = ";";

	public static final String VOID = "void";

	public static final String BOOLEAN = "boolean";

	public static final String BYTE = "byte";

	public static final String CHAR = "char";

	public static final String SHORT = "short";

	public static final String INT = "int";

	public static final String LONG = "long";

	public static final String FLOAT = "float";

	public static final String DOUBLE = "double";

	public static final List<String> PRIMITIVE_NAMES = Arrays.asList(
		VOID, BOOLEAN, BYTE, CHAR, SHORT, INT, LONG, FLOAT, DOUBLE
	);
	public static final List<String> PRIMITIVE_DESCRIPTORS = Arrays.asList(
		TypeDesc.VOID.getValue(), TypeDesc.BOOLEAN.getValue(), TypeDesc.BYTE.getValue(), TypeDesc.CHAR.getValue(),
		TypeDesc.SHORT.getValue(), TypeDesc.INT.getValue(), TypeDesc.LONG.getValue(), TypeDesc.FLOAT.getValue(),
		TypeDesc.DOUBLE.getValue()
	);

	public static final String OBJECT = "java.lang.Object";

	public static final String STRING = "java.lang.String";

	public static final String CLONEABLE = "java.lang.Cloneable";

	public static final String SERIALIZABLE = "java.io.Serializable";

	/**
	 * 判断指定类名是否表示数组类
	 * @param className
	 * @return
	 */
	public static boolean isArray(String className) {
		return className != null && className.startsWith(ARRAY_PREFIX);
	}

	/**
	 * 判断指定类名是否为原始类型
	 * @param className
	 * @return
	 */
	public static boolean isPrimitive(String className) {
		return className != null && PRIMITIVE_NAMES.contains(className);
	}

	/**
	 * 获取原始类型描述符对应的原始类型
	 * @param primitiveDesc
	 * @return
	 */
	public static String findPrimitive(String primitiveDesc) {
		for (int i = 0; i < PRIMITIVE_DESCRIPTORS.size(); i++) {
			if (PRIMITIVE_DESCRIPTORS.get(i).equals(primitiveDesc)) {
				return PRIMITIVE_NAMES.get(i);
			}
		}
		return null;
	}

	public static String findPrimitiveDesc(String primitive) {
		for (int i = 0; i < PRIMITIVE_NAMES.size(); i++) {
			if (PRIMITIVE_NAMES.get(i).equals(primitive)) {
				return PRIMITIVE_DESCRIPTORS.get(i);
			}
		}
		return null;
	}

	/**
	 * 获取指定类名的数组类描述符
	 * @param className
	 * @return
	 */
	public static String arrayDescOf(String className) {
		String primitiveDesc = findPrimitiveDesc(className);
		if (primitiveDesc != null) {
			className = primitiveDesc;
		}
		return ARRAY_PREFIX + className;
	}

	/**
	 * 获取数组类的元素类型
	 * @param arrayClassName
	 * @return
	 */
	public static String elementClassNameOf(String arrayClassName) {
		if (!isArray(arrayClassName)) {
			throw new IllegalArgumentException("不是数组类型:" + arrayClassName);
		}
		return arrayClassName.substring(1);
	}

}
