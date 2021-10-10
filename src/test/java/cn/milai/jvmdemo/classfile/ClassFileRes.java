package cn.milai.jvmdemo.classfile;

import java.io.DataInputStream;
import java.io.InputStream;

import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.util.ClassNames;

/**
 * 测试 Class 文件资源
 * @author milai
 * @date 2021.07.29
 */
public class ClassFileRes {

	private static final String PREFIX = "/";

	private static final String SUFFIX = ".class";

	public static DataInputStream helloWorld() {
		return read(PREFIX + ClassNames.toSlash(Classes.HELLO_WORLD) + SUFFIX);
	}

	public static DataInputStream classTest() {
		return read(PREFIX + ClassNames.toSlash(Classes.CLASS_TEST) + SUFFIX);
	}

	public static DataInputStream constantWrapper() {
		return read(PREFIX + ClassNames.toSlash(Classes.CONSTANT_WRAPPER) + SUFFIX);
	}

	public static InputStream stringLenComparator() {
		return read(PREFIX + ClassNames.toSlash(Classes.STRING_LEN_COMPARATOR) + SUFFIX);
	}

	private static DataInputStream read(String res) {
		return new DataInputStream(ClassFileRes.class.getResourceAsStream(res));
	}

}
