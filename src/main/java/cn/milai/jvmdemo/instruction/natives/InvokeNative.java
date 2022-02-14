package cn.milai.jvmdemo.instruction.natives;

import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 调用本地方法，该指令并非 JVM 规范的字节码指令，只是为了 mock 本地方法的调用自定义的指令
 * @author milai
 * @date 2022.02.13
 */
public class InvokeNative implements Instruction {

	@Override
	public void execute(Frame frame) {
		NativeMethodRegistry.invoke(frame);
	}

}
