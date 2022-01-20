package cn.milai.jvmdemo;

import java.util.Arrays;

/**
 * {@link DefaultClassInfoLoader} 测试初始化类
 * @author milai
 * @date 2021.08.29
 */
public class TestClassInfoLoader {

	public static void initDefaultClassInfoLoader() {
		try {
			DefaultClassInfoLoader.init(
				Arrays.asList(DefaultClassInfoLoaderTest.class.getResource("/").getFile())
			);
		} catch (IllegalStateException e) {
			// TODO 日志
		}
	}

	public static DefaultClassInfoLoader get() {
		initDefaultClassInfoLoader();
		return DefaultClassInfoLoader.getInstance();
	}

}
