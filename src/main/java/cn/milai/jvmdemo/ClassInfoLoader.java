package cn.milai.jvmdemo;

import java.util.HashMap;
import java.util.Map;

import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link ClassInfo} 加载器
 * @author milai
 * @date 2021.08.26
 */
public class ClassInfoLoader {

	private Map<String, ClassInfo> loaded;

	private ClassInfoLoader parent;

	public ClassInfoLoader() {
		this(null);
	}

	public ClassInfoLoader(ClassInfoLoader parent) {
		this.parent = parent;
		loaded = new HashMap<>();
	}

	public synchronized ClassInfo load(String name) throws ClassInfoNotFoundException {
		if (loaded.containsKey(name)) {
			return loaded.get(name);
		}
		ClassInfo classInfo = null;
		if (parent == null) {
			classInfo = loadFromDefaultLoader(name);
		} else {
			try {
				classInfo = parent.load(name);
			} catch (ClassInfoNotFoundException e) {
				// TODO 日志
			}
		}
		if (classInfo == null) {
			classInfo = loadClass(name);
		}
		return classInfo;
	}

	protected ClassInfo loadClass(String name) {
		throw new ClassInfoNotFoundException(name);
	}

	private static ClassInfo loadFromDefaultLoader(String name) {
		return DefaultClassInfoLoader.getInstance().load(name);
	}

	public ClassInfoLoader getParent() { return parent; }

}
