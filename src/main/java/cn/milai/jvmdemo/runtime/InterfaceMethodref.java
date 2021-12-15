package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.InterfaceMethodrefConstant;

/**
 * 接口方法符号引用
 * @author milai
 * @date 2021.12.15
 */
public class InterfaceMethodref extends AbstractMethodRef {

	public InterfaceMethodref(ConstantPool pool, RTConstantPool rtPool, InterfaceMethodrefConstant c) {
		super(pool, rtPool, c);
	}

}
