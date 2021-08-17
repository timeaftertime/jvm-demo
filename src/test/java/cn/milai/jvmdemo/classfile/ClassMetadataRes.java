package cn.milai.jvmdemo.classfile;

import java.io.IOException;

/**
 * {@link ClassMetadata} 测试资源类
 * @author milai
 * @date 2021.08.17
 */
public class ClassMetadataRes {

	public static ClassMetadata helloWorld() throws IOException {
		return new ClassMetadata(ClassFileRes.helloWorld());
	}

	public static ClassMetadata classTest() throws IOException {
		return new ClassMetadata(ClassFileRes.classTest());
	}

	public static ClassMetadata constantWrapper() throws IOException {
		return new ClassMetadata(ClassFileRes.constantWrapper());
	}

}
