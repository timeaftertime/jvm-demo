package cn.milai.jvmdemo.runtime;

/**
 * 将方法符号引用转换为直接引用的解析器
 * @author milai
 * @date 2021.12.15
 */
public class MethodResolver {

	private MethodResolver() {
	}

	private static interface Resolver {
		Method resolve();
	}

	private static Method resolveIfNot(Resolver r, AbstractMethodRef ref) {
		if (ref.getMethod() == null) {
			synchronized (ref) {
				if (ref.getMethod() == null) {
					ref.setMethod(r.resolve());
				}
			}
		}
		return ref.getMethod();
	}

	/**
	 * 解析指定静态方法
	 * @param ref
	 * @return
	 */
	public static Method resolveStatic(AbstractMethodRef ref) {
		return resolveIfNot(() -> findMethod(ref.resolvedClass(), ref, true), ref);
	}

	/**
	 * 在指定类型中解析非静态方法
	 * @param c
	 * @param ref
	 * @return
	 */
	public static Method resolve(ClassInfo c, AbstractMethodRef ref) {
		return resolveIfNot(() -> findMethod(c, ref, false), ref);
	}

	private static Method findMethod(ClassInfo classInfo, AbstractMethodRef ref, boolean isStatic) {
		Method method = findInClass(classInfo, ref, isStatic);
		if (method == null) {
			method = findInInterfaces(classInfo, ref, isStatic);
		}
		return method;
	}

	private static Method findInClass(ClassInfo classInfo, AbstractMethodRef ref, boolean isStatic) {
		for (ClassInfo info = classInfo; info != null; info = info.getSuperClassInfo()) {
			for (Method m : info.getMethods()) {
				if (match(m, ref, isStatic)) {
					return m;
				}
			}
		}
		return null;
	}

	private static Method findInInterfaces(ClassInfo classInfo, AbstractMethodRef ref, boolean isStatic) {
		for (ClassInfo info : classInfo.getInterfacesClassInfo()) {
			for (Method m : info.getMethods()) {
				if (match(m, ref, isStatic)) {
					return m;
				}
			}
			Method m = findInInterfaces(info, ref, isStatic);
			if (m != null) {
				return m;
			}
		}
		return null;
	}

	private static boolean match(Method m, AbstractMethodRef ref, boolean isStatic) {
		return m.getName().equals(ref.getName())
			&& m.getDescriptor().equals(ref.getDescriptor())
			&& m.isStatic() == isStatic;
	}

}
