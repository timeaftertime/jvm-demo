package cn.milai.jvmdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.milai.jvmdemo.classfile.ClassMetadata;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.util.ClassNames;

/**
 * 默认 {@link ClassInfo} 加载器
 * @author milai
 * @date 2021.08.26
 */
public final class DefaultClassInfoLoader implements ClassInfoLoader {

	public static final String LIB_PATH = Env.JAVA_HOME + "lib/";

	public static final String EXT_PATH = Env.JAVA_HOME + "lib/ext/";

	public static final String JAR = ".jar";

	public static final String CLASS = ".class";

	private static final String PROTOCAL_JAR_FILE = "jar:file:";

	private List<String> classPaths;

	private Map<String, ClassInfo> loaded;

	private static DefaultClassInfoLoader INSTANCE;

	private DefaultClassInfoLoader() {
		loaded = new HashMap<>();
	}

	public static void init(List<String> classPaths) {
		if (INSTANCE != null) {
			throw new IllegalStateException("重复初始化");
		}
		INSTANCE = new DefaultClassInfoLoader();
		INSTANCE.classPaths = new ArrayList<>(classPaths);
		INSTANCE.loadPrimitiveClasses();
	}

	private void loadPrimitiveClasses() {
		for (String desc : ClassConst.PRIMITIVE_DESCRIPTORS) {
			loaded.put(desc, ClassInfo.primitive(ClassConst.findPrimitive(desc), this));
		}
	}

	public static DefaultClassInfoLoader getInstance() {
		if (INSTANCE == null) {
			throw new IllegalStateException("尚未初始化");
		}
		return INSTANCE;
	}

	public synchronized ClassInfo load(String name) {
		return loaded.computeIfAbsent(name, this::doLoad);
	}

	private ClassInfo doLoad(String name) {
		if (ClassConst.isArray(name)) {
			return ClassInfo.array(ClassNames.fromSlash(name), this);
		}
		// Bootstrap ClassLoader
		ClassInfo classInfo = loadFromJars(LIB_PATH, name);
		// Extension ClassLoader
		if (classInfo == null) {
			classInfo = loadFromJars(EXT_PATH, name);
		}
		// Application ClassLoader
		if (classInfo == null) {
			for (String classPath : classPaths) {
				classInfo = loadFromPath(classPath, name);
				if (classInfo == null) {
					classInfo = loadFromJars(classPath, name);
				}
				if (classInfo != null) {
					return classInfo;
				}
			}
		}
		return classInfo;
	}

	private ClassInfo loadFromJars(String path, String name) {
		String classFilePath = ClassNames.toSlash(name) + CLASS;
		for (File file : new File(path).listFiles()) {
			if (file.getName().endsWith(JAR)) {
				ClassInfo classInfo = findInJar(file, classFilePath);
				if (classInfo != null) {
					return classInfo;
				}
			}
		}
		return null;
	}

	private ClassInfo loadFromPath(String path, String name) {
		File file = new File(path + ClassNames.toSlash(name) + CLASS);
		if (file.exists()) {
			try {
				return new ClassInfo(new ClassMetadata(new FileInputStream(file)), this);
			} catch (IOException e) {
				// TODO 日志
			}
		}
		return null;
	}

	private ClassInfo findInJar(File jar, String path) {
		String url = PROTOCAL_JAR_FILE + jar.getAbsolutePath().replace("\\", "/") + "!/" + path;
		try (InputStream in = new URL(url).openConnection().getInputStream()) {
			return new ClassInfo(new ClassMetadata(in), this);
		} catch (IOException e) {
			// TODO 日志
		}
		return null;
	}

}
