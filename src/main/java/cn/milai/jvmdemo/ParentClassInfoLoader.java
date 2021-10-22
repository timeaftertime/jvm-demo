package cn.milai.jvmdemo;

import java.util.HashMap;
import java.util.Map;

import cn.milai.jvmdemo.runtime.ClassInfo;

/**
 * {@link ClassInfo} 加载器
 * @author milai
 * @date 2021.08.26
 */
public abstract class ParentClassInfoLoader implements ClassInfoLoader {

	private Map<String, ClassInfo> loaded;

	private ParentClassInfoLoader parent;

	public ParentClassInfoLoader() {
		this(null);
	}

	public ParentClassInfoLoader(ParentClassInfoLoader parent) {
		this.parent = parent;
		loaded = new HashMap<>();
	}

	public synchronized ClassInfo load(String name) throws ClassInfoNotFoundException {
		return loaded.computeIfAbsent(name, this::doLoad);
	}

	private ClassInfo doLoad(String name) {
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
	
	protected abstract ClassInfo loadClass(String name);

	private static ClassInfo loadFromDefaultLoader(String name) {
		return DefaultClassInfoLoader.getInstance().load(name);
	}

	public ParentClassInfoLoader getParent() { return parent; }

}
