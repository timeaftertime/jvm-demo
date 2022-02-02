package cn.milai.jvmdemo.constants;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.runtime.Method;

/**
 * 方法相关常量
 * @author milai
 * @date 2021.12.15
 */
public class MethodConst {

	public static final String INIT = "<init>";

	public static final String CLINIT = "<clinit>";

	public static final String RETURN_VOID = "()" + TypeDesc.VOID.getValue();

	public static final String MAIN = "main";

	public static final String MAIN_DESC = "([Ljava/lang/String;)V";

	/**
	 * 判断指定方法是否为实例初始化方法(构造方法)
	 * @param m
	 * @return
	 */
	public static boolean isInit(Method m) {
		if (m == null || m.getName() == null) {
			return false;
		}
		return m.getName().equals(INIT);
	}

	/**
	 * 判断指定方法是否为类初始化方法
	 * @param m
	 * @return
	 */
	public static boolean isClinit(Method m) {
		if (m == null || m.getName() == null) {
			return false;
		}
		return m.getName().equals(CLINIT);
	}

}
