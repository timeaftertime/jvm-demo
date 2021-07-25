package cn.milai.jvmdemo;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * 验证 class 文件的测试类
 * @author milai
 * @date 2021.07.25
 */
public class ClassFileTest {

	@Test
	public void testClassFileMagicNumberAndVersion() throws IOException {
		byte[] classData = readClassFile("/cn/milai/jvmdemo/HelloWorld.class");
		String[] magicNumber = new String[] { "CA", "FE", "BA", "BE" };
		for (int i = 0; i < 4; i++) {
			assertEquals(
				Integer.parseInt(magicNumber[i], 16), (256 + classData[i]) % 256
			);
		}
		assertEquals(0, (classData[4] << 4) + classData[5]);
		assertEquals(52, (classData[6] << 6) + classData[7]);
	}

	private byte[] readClassFile(String path) throws IOException {
		byte[] buf = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (InputStream in = ClassFileTest.class.getResourceAsStream(path)) {
			int len = 0;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		}
		return out.toByteArray();
	}
}
