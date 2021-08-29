package cn.milai.jvmdemo.classfile;

import java.io.DataInputStream;

import cn.milai.jvmdemo.util.ClassNames;

/**
 * 测试 Class 文件资源
 * @author milai
 * @date 2021.07.29
 */
public class ClassFileRes {

	private static final String PREFIX = "/";

	private static final String SUFFIX = ".class";

	public static final String HELLO_WORLD = "cn.milai.jvmdemo.classfile.HelloWorld";

	public static final String CLASS_TEST = "cn.milai.jvmdemo.classfile.ClassTest";

	public static final String CONSTANT_WRAPPER = "cn.milai.jvmdemo.runtime.ConstantWrapper";

	public static DataInputStream helloWorld() {
		return read(PREFIX + ClassNames.toSlash(HELLO_WORLD) + SUFFIX);
	}

	public static DataInputStream classTest() {
		return read(PREFIX + ClassNames.toSlash(CLASS_TEST) + SUFFIX);
	}

	public static DataInputStream constantWrapper() {
		return read(PREFIX + ClassNames.toSlash(CONSTANT_WRAPPER) + SUFFIX);
	}

	private static DataInputStream read(String res) {
		return new DataInputStream(ClassFileRes.class.getResourceAsStream(res));
	}
}
