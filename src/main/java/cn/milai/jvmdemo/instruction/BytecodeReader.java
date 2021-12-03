package cn.milai.jvmdemo.instruction;

import java.io.EOFException;
import java.io.IOException;

/**
 * 字节数组读取器
 * @author milai
 * @date 2021.11.14
 */
public class BytecodeReader {

	private int pc;
	private byte[] codes;

	public BytecodeReader(byte[] codes) {
		pc = 0;
		this.codes = codes;
	}

	public int readUint8() throws IOException {
		if (pc >= codes.length) {
			throw new EOFException();
		}
		int ch = codes[pc++];
		return ch & 0xff;
	}

	public byte readInt8() throws IOException {
		return (byte) readUint8();
	}

	public short readInt16() throws IOException {
		int ch1 = readUint8();
		int ch2 = readUint8();
		return (short) ((ch1 << 8) + (ch2 << 0));
	}

	public int readUint16() throws IOException {
		int ch1 = readUint8();
		int ch2 = readUint8();
		return (ch1 << 8) + (ch2 << 0);
	}

	public int readInt32() throws IOException {
		int ch1 = readUint8();
		int ch2 = readUint8();
		int ch3 = readUint8();
		int ch4 = readUint8();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	public int getPC() { return pc; }

	public void setPC(int pc) { this.pc = pc; }

	public void reset(byte[] codes, int pc) {
		this.codes = codes;
		this.pc = pc;
	}
	
	public void skipPadding(int padding) throws IOException {
		if (padding == 0) {
			return;
		}
		while (getPC() % padding != 0) {
			readInt8();
		}
	}
}