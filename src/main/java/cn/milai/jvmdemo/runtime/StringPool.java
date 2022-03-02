package cn.milai.jvmdemo.runtime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.classes.Field;

/**
 * 字符串常量池
 * @author milai
 * @date 2022.03.01
 */
public class StringPool {

	private static final String VALUE_DESC = "[C";

	private static final String VALUE_NAME = "value";

	private static final Map<String, ObjectRef> internedStrings = new ConcurrentHashMap<>();

	/**
	 * 获取指定常量字符串的对象引用
	 * @param loader
	 * @param string
	 * @return
	 */
	public static synchronized ObjectRef getString(ClassInfoLoader loader, String string) {
		return internedStrings.computeIfAbsent(string, s -> newRef(loader, s));
	}

	private static ObjectRef newRef(ClassInfoLoader loader, String s) {
		ClassInfo strClass = loader.load(ClassConst.STRING);
		ObjectRef ref = new ObjectRef(strClass);
		Field field = strClass.getField(VALUE_NAME, VALUE_DESC, false);
		ObjectRef charArray = new ObjectRef(loader.load(VALUE_DESC), s.length());
		for (int i = 0; i < s.length(); i++) {
			charArray.getElements().setInt(i, s.charAt(i));
		}
		ref.getFields().setRef(field.getSlotId(), charArray);
		return ref;
	}

}
