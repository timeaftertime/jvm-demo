package cn.milai.jvmdemo.instruction.control;

import java.io.IOException;

import cn.milai.jvmdemo.instruction.BytecodeReader;
import cn.milai.jvmdemo.instruction.Instruction;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * Xswitch 指令
 * @author milai
 * @date 2021.12.02
 */
public class SwitchInstructions {

	public abstract static class SwitchInstruction implements Instruction {

		protected int defaultOffset;
		private int offsetMap[];

		@Override
		public final void readOperands(BytecodeReader reader) throws IOException {
			reader.skipPadding(4);
			defaultOffset = reader.readInt32();
			offsetMap = readOffsetMap(reader);
		}

		protected abstract int[] readOffsetMap(BytecodeReader reader) throws IOException;

		@Override
		public void execute(Frame frame) {
			int key = frame.getOperandStack().popInt();
			for (int i = 0; i < offsetMap.length; i += 2) {
				if (key == offsetMap[i]) {
					frame.getThreadSpace().setPC(frame.getCurrentPC() + offsetMap[i + 1]);
					return;
				}
			}
			frame.getThreadSpace().setPC(frame.getCurrentPC() + defaultOffset);
		}

	}

	public static class TABLESWITCH extends SwitchInstruction {

		@Override
		protected int[] readOffsetMap(BytecodeReader reader) throws IOException {
			int low = reader.readInt32();
			int hig = reader.readInt32();
			int mapCount = hig - low + 1;
			int[] map = new int[mapCount * 2];
			int[] offsets = readInt32s(reader, mapCount);
			for (int i = 0; i < mapCount; i++) {
				map[i * 2] = low + i;
				map[i * 2 + 1] = offsets[i];
			}
			return map;
		}

	}

	public static class LOOKUPSWITCH extends SwitchInstruction {

		@Override
		protected int[] readOffsetMap(BytecodeReader reader) throws IOException {
			return readInt32s(reader, reader.readInt32() * 2);
		}

	}

	private static int[] readInt32s(BytecodeReader reader, int count) throws IOException {
		int[] vs = new int[count];
		for (int i = 0; i < count; i++) {
			vs[i] = reader.readInt32();
		}
		return vs;
	}

}
