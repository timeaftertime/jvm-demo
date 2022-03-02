package cn.milai.jvmdemo;

import cn.milai.jvmdemo.runtime.classes.ClassInfo;

/**
 * {@link ClassInfo} 加载失败的异常
 * @author milai
 * @date 2021.08.28
 */
public class ClassInfoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassInfoNotFoundException(String name) {
		super(String.format("加载类失败: %s", name));
	}
}
