package cn.milai.jvmdemo.classfile;

import java.io.DataInputStream;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * 解析 {@link Attribute} 的工具类 
 * @author milai
 * @date 2021.08.03
 */
public class Attributes {

	/**
	 * 从 data 中解析属性列表
	 * @param data
	 * @param constantPool
	 * @return
	 */
	public static Attribute[] readAttributes(DataInputStream data, ConstantPool constantPool) { return null; }

}
