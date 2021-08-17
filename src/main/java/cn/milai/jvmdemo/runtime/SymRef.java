package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;

/**
 * 引用链接
 * @author milai
 * @date 2021.08.16
 */
public abstract class SymRef {

	protected RTConstantPool pool;

	public SymRef(ConstantPool pool, RTConstantPool rtPool) {
		this.pool = rtPool;
	}

	/**
	 * 获取目标类类名
	 * @return
	 */
	public abstract String targetClassName();

}
