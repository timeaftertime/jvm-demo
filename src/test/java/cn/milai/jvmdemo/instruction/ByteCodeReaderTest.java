package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.EOFException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link BytecodeReader} 测试类
 * @author milai
 * @date 2021.11.14
 */
public class ByteCodeReaderTest {

	private BytecodeReader reader;

	@Before
	public void setUp() {
		reader = new BytecodeReader(new byte[] { 1, 2, 3, 4, 0, 0, 1, 1, -1, -1, -1, -1 });
	}

	@Test
	public void read() throws IOException {
		assertEquals(0, reader.getPc());
		assertEquals(1, reader.readUint8());
		assertEquals(1, reader.getPc());
		assertEquals(2, reader.readInt8());
		assertEquals(2, reader.getPc());
		assertEquals(772, reader.readUint16());
		assertEquals(4, reader.getPc());
		assertEquals(257, reader.readInt32());
		assertEquals(8, reader.getPc());
		assertEquals(255, reader.readUint8());
		assertEquals(9, reader.getPc());
		assertEquals(-1, reader.readInt8());
		assertEquals(10, reader.getPc());
		assertEquals(-1, reader.readInt16());
		assertEquals(12, reader.getPc());
		try {
			reader.readInt32();
		} catch (EOFException e) {
			return;
		}
		fail();
	}

}