package cn.milai.jvmdemo.instruction.natives;

import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.ClassConst;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.classes.ClassInfo;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.LocalVarsTable;

/**
 * {@link System#arraycopy(Object, int, Object, int, int)} 实现
 * @author milai
 * @date 2022.02.14
 */
public class SystemArrayCopy implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		LocalVarsTable locals = frame.getLocalVarsTable();
		ObjectRef src = locals.getRef(0);
		int srcPos = locals.getInt(1);
		ObjectRef dest = locals.getRef(2);
		int destPos = locals.getInt(3);
		int len = locals.getInt(4);
		if (src == null || dest == null) {
			throw new NullPointerException();
		}
		ClassInfo srcClass = src.getClassInfo();
		if (!srcClass.isArray() || !dest.getClassInfo().isArray()) {
			throw new ArrayStoreException("参数不是数组");
		}
		if (srcPos < 0 || destPos < 0 || len < 0 || srcPos + len > src.length() || destPos + len > dest.length()) {
			throw new IndexOutOfBoundsException();
		}
		TypeDesc elementType = TypeDesc.of(ClassConst.elementClassNameOf(srcClass));
		if (elementType.needDoubleSlot()) {
			srcPos *= 2;
			destPos *= 2;
			len *= 2;
		}
		for (int i = 0; i < len; i++) {
			if (elementType.isPrimitive()) {
				dest.getElements().setInt(i + destPos, src.getElements().getInt(i + srcPos));
			} else {
				dest.getElements().setRef(i + destPos, src.getElements().getRef(i + srcPos));
			}
		}
	}

}
