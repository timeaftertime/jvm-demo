package cn.milai.jvmdemo.util;

/**
 * 类名工具类
 * @author milai
 * @date 2021.08.17
 */
public class ClassNames {

	private ClassNames() {
	}

	/**
	 * 将斜杠分割的全类名转换为点分割的全类名
	 * @param slashClassName
	 * @return
	 */
	public static String fromSlash(String slashClassName) {
		return slashClassName.replace("/", ".");
	}
}
