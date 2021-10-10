package cn.milai.jvmdemo;

import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link ClassInfo} 加载器
 * @author milai
 * @date 2021.10.08
 */
public interface ClassInfoLoader {

	/**
	 * 加载指定 {@code name} 的 {@link ClassInfo}
	 * @param name
	 * @return
	 * @throws ClassInfoNotFoundException
	 */
	ClassInfo load(String name) throws ClassInfoNotFoundException;

}
