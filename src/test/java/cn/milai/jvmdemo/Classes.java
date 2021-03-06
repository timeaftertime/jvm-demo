package cn.milai.jvmdemo;

import cn.milai.jvmdemo.constants.ClassConst;

/**
 * 类名常量类
 * @author milai
 * @date 2021.10.10
 */
public class Classes {

	private Classes() {
	}

	public static final String HELLO_WORLD = "cn.milai.jvmdemo.classfile.HelloWorld";

	public static final String CLASS_TEST = "cn.milai.jvmdemo.classfile.ClassTest";

	public static final String CONSTANT_WRAPPER = "cn.milai.jvmdemo.runtime.ConstantWrapper";

	public static final String STRING_LEN_COMPARATOR = "cn.milai.jvmdemo.runtime.StringLenComparator";

	public static final String CONSTANT = "cn.milai.jvmdemo.classfile.constant.Constant";

	public static final String PARENT = "cn.milai.jvmdemo.runtime.Parent";

	public static final String CHILD = "cn.milai.jvmdemo.runtime.Child";

	public static final String ADD_TEST = "cn.milai.jvmdemo.instruction.AddTest";

	public static final String SWITCH_TEST = "cn.milai.jvmdemo.instruction.SwitchTest";

	public static final String METHOD_INVOKE = "cn.milai.jvmdemo.instruction.MethodInvoke";

	public static final String CALL_METHODS = "cn.milai.jvmdemo.instruction.CallMethods";

	public static final String PUT_AND_GET_FIELD = "cn.milai.jvmdemo.instruction.PutAndGetField";

	public static final String ARRAY_TEST = "cn.milai.jvmdemo.instruction.ArrayTest";

	public static final String CALCULATOR = "cn.milai.jvmdemo.instruction.Calculator";

	public static final String NATIVE_CALLER = "cn.milai.jvmdemo.instruction.NativeCaller";

	public static final String EXCEPTION_HANDLE = "cn.milai.jvmdemo.instruction.ExceptionHandle";

	public static final String OBJECT = ClassConst.OBJECT;

	public static final String COMPARATOR = "java.util.Comparator";

	public static final String COMPARABLE = "java.lang.Comparable";

	public static final String CHAR_SEQUENCE = "java.lang.CharSequence";

	public static final String SERIABLIZABLE = "java.io.Serializable";

	public static final String STRING = ClassConst.STRING;

	public static final String STRING_BUILDER = "java.lang.StringBuilder";

	public static final String COLLECTIONS = "java.util.Collections";

	public static final String COLLECTION = "java.util.Collection";

	public static final String LIST = "java.util.List";

	public static final String ARRAY_LIST = "java.util.ArrayList";

	public static final String INPUT_STREAM = "java.io.InputStream";

	public static final String FILE_INPUT = "java.io.FilterInputStream";

	public static final String FUNCTION = "java.util.function.Function";

	public static final String RUNTIME_EXCEPTION = "java.lang.RuntimeException";
}
