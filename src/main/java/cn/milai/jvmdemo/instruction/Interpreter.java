package cn.milai.jvmdemo.instruction;

import java.io.IOException;
import java.util.EmptyStackException;

import cn.milai.jvmdemo.runtime.ThreadSpace;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * 字节码解析器
 * @author milai
 * @date 2021.11.26
 */
public class Interpreter {
	
	/**
	 * 解释执行指定 {@link ThreadSpace}
	 * @param thread
	 * @throws IOException 若字节码格式异常
	 * @throws {@link EmptyStackException} 若 {@link ThreadSpace} 为空
	 */
	public static void interpret(ThreadSpace thread) throws IOException, EmptyStackException {
		BytecodeReader reader = new BytecodeReader(thread.currentFrame().getMethod().getCodes());
		while (!thread.finished()) {
			Frame currentFrame = thread.currentFrame();
			currentFrame.setCurrentPC(reader.getPC());
			int opCode = reader.readUint8();
			Instruction ins = InstructionFactory.create(opCode);
			ins.readOperands(reader);
			thread.setPC(reader.getPC());
			System.out.println(currentFrame.getMethod() + " " + Opcode.find(opCode));
			ins.execute(currentFrame);
			reader.reset(thread.currentFrame().getMethod().getCodes(), thread.getPC());
		}
	}
}
