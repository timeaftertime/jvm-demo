package cn.milai.jvmdemo.runtime.ref;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.runtime.RTConstantPool;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;

/**
 * 引用链接
 * @author milai
 * @date 2021.08.16
 */
public abstract class SymRef {

	protected RTConstantPool pool;
	private ClassInfo target;

	public SymRef(ConstantPool pool, RTConstantPool rtPool) {
		this.pool = rtPool;
	}

	/**
	 * 获取目标类类名
	 * @return
	 */
	public abstract String targetClassName();

	/**
	 * 获取当前引用的目标 {@link ClassInfo}
	 * @return
	 */
	public ClassInfo resolvedClass() {
		if (target == null) {
			target = resolveClass();
		}
		return target;
	}

	private ClassInfo resolveClass() {
		ClassInfo from = pool.getOwner();
		ClassInfo to = from.getClassInfoLoader().load(targetClassName());
		// TODO 类访问校验
		return to;
	}

}
