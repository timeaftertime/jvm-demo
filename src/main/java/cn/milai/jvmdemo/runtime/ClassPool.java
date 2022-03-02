package cn.milai.jvmdemo.runtime;

import java.util.HashMap;
import java.util.Map;

import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;

/**
 * {@link Class} 的 ObjectRef 池
 * @author milai
 * @date 2022.03.02
 */
public class ClassPool {

	private static Map<ClassInfo, ObjectRef> refs = new HashMap<>();

	/**
	 * 获取指定 {@link ClassInfo} 对应 Class 的 {@link ClassInfo}，并创建该 {@link ClassInfo} 的 {@link ObjectRef}
	 * @param c
	 * @return
	 */
	public static synchronized ObjectRef getRef(ClassInfo c) {
		return refs.computeIfAbsent(c, ClassPool::newRef);
	}

	private static ObjectRef newRef(ClassInfo c) {
		return new ObjectRef(c.getClassInfoLoader().load(ClassConst.CLASS));
	}
}
