package cn.milai.jvmdemo.classfile;

import static org.junit.Assert.assertEquals;

import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * 验证 class 文件的测试类
 * @author milai
 * @date 2021.07.25
 */
public class ClassFileTest {

	@Test
	public void testClassFileMagicNumberAndVersion() throws IOException {
		DataInputStream in = ClassFiles.helloWorld();
		String[] magicNumber = new String[] { "CA", "FE", "BA", "BE" };
		for (int i = 0; i < 4; i++) {
			assertEquals(Integer.parseInt(magicNumber[i], 16), in.readUnsignedByte());
		}
		assertEquals(0, in.readUnsignedShort());
		assertEquals(52, in.readUnsignedShort());
	}

}
