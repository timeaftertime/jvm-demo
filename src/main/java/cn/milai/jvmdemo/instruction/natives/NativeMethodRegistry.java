package cn.milai.jvmdemo.instruction.natives;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.Method;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 本地方法注册中心
 * @author milai
 * @date 2022.02.13
 */
public class NativeMethodRegistry {

	private static Map<String, NativeMethod> nativeMethods = new ConcurrentHashMap<>();

	public static void invoke(Frame frame) {
		Method method = frame.getMethod();
		String key = buildKey(method.getClassInfo().getName(), method.getName(), method.getDescriptor());
		NativeMethod m = nativeMethods.get(key);
		if (m == null) {
			throw new NoSuchMethodError("未注册 native 方法 ：" + key);
		}
		m.invoke(frame);
	}

	public static String buildKey(String className, String name, String descriptor) {
		return String.format("%s %s %s", className, name, descriptor);
	}

	public static void register(String className, String name, String descriptor, NativeMethod method) {
		nativeMethods.put(buildKey(className, name, descriptor), method);
	}

	static {
		register(ClassConst.OBJECT, "hashCode", "()I", new ObjectHashCode());
		register(ClassConst.OBJECT, "registerNatives", "()V", NativeMethod.EMPTY);
		register(ClassConst.SYSTEM, "registerNatives", "()V", NativeMethod.EMPTY);
		register(ClassConst.SYSTEM, "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", new SystemArrayCopy());
	}

}
