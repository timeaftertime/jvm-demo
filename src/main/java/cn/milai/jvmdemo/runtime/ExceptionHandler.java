package cn.milai.jvmdemo.runtime;

import cn.milai.jvmdemo.classfile.attribute.ExceptionTable;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Method;
import cn.milai.jvmdemo.runtime.ref.ClassRef;

/**
 * 异常处理器
 * @author milai
 * @date 2022.02.28
 */
public class ExceptionHandler {

	private Method method;
	private ExceptionTable[] tables;

	public ExceptionHandler(Method method, ExceptionTable[] exceptionAttributes) {
		this.method = method;
		tables = exceptionAttributes;
	}

	/**
	 * 获取异常指定异常应该跳转的 pc，若不存在，返回 -1
	 * @param exception
	 * @param pc
	 * @return
	 */
	public int getRecoverPC(ClassInfo exception, int pc) {
		for (ExceptionTable table : tables) {
			int start = table.getStartPC();
			int end = table.getEndPC();
			if (pc < start || pc >= end) {
				continue;
			}
			if (table.getCatchType() == 0) {
				return table.getHandlerPC();
			}
			ClassRef classRef = method.getClassInfo().getConstantPool().getClassRef(table.getCatchType());
			ClassInfo canHandle = classRef.resolvedClass();
			if (canHandle == exception || exception.isSubClassOf(canHandle)) {
				return table.getHandlerPC();
			}
		}
		return -1;
	}

}
