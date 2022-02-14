package cn.milai.jvmdemo.instruction.natives;

import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link Object#hashCode()} 实现
 * @author milai
 * @date 2022.02.13
 */
public class ObjectHashCode implements NativeMethod {

	@Override
	public void invoke(Frame frame) {
		frame.getOperandStack().pushInt(frame.getLocalVarsTable().getRef(0).hashCode());
	}

}
