package cn.milai.jvmdemo.runtime.ref;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.MethodrefConstant;
import cn.milai.jvmdemo.runtime.RTConstantPool;

/**
 * 方法符号引用
 * @author milai
 * @date 2021.12.15
 */
public class MethodRef extends AbstractMethodRef {

	public MethodRef(ConstantPool pool, RTConstantPool rtPool, MethodrefConstant c) {
		super(pool, rtPool, c);
	}

}
