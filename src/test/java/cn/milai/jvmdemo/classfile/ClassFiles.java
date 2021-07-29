package cn.milai.jvmdemo.classfile;

import java.io.DataInputStream;

/**
 * 测试 Class 文件资源
 * @author milai
 * @date 2021.07.29
 */
public class ClassFiles {

	public static DataInputStream helloWorld() { return read("/cn/milai/jvmdemo/classfile/HelloWorld.class"); }

	public static DataInputStream classTest() { return read("/cn/milai/jvmdemo/classfile/ClassTest.class"); }

	private static DataInputStream read(String res) {
		return new DataInputStream(ClassFiles.class.getResourceAsStream(res));
	}
}
