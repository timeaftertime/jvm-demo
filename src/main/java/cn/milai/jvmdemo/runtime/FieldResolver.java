package cn.milai.jvmdemo.runtime;

/**
 * 将字段符号引用转换为直接引用的工具类
 * @author milai
 * @date 2022.01.19
 */
public class FieldResolver {

	private FieldResolver() {
	}

	/**
	 * 解析指定 {@link FieldRef} 为 {@link Field}
	 * @param ref
	 * @param isStatic
	 * @return
	 */
	public static Field resolve(FieldRef ref, boolean isStatic) {
		if (ref.getField() == null) {
			synchronized (ref) {
				if (ref.getField() == null) {
					ref.setField(doResolve(ref, isStatic));
				}
			}
		}
		return ref.getField();
	}

	private static Field doResolve(FieldRef ref, boolean isStatic) {
		for (ClassInfo c = ref.resolvedClass(); c != null; c = c.getSuperClassInfo()) {
			Field field = c.getField(ref.getName(), ref.getDescriptor(), isStatic);
			if (field != null) {
				return field;
			}
		}
		return null;
	}

}
