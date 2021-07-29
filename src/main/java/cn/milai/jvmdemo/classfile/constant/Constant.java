package cn.milai.jvmdemo.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Class 文件常量
 * @author milai
 * @date 2021.07.29
 */
public interface Constant {

	/**
	 * 获取常量所属 Tag
	 * @return
	 */
	ConstantTag tag();

	void init(DataInputStream data, ConstantPool pool) throws IOException;

}
