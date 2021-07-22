package cn.milai.jvmdemo;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link JVM} 测试类
 * @author milai
 * @date 2021.07.22
 */
public class JVMTest {

	private ByteArrayOutputStream out = null;

	@Before
	public void setUp() {
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@Test
	public void showVersion() {
		JVM.main(new String[] { JVM.OPTION_VERSION });
		assertEquals(JVM.VERSION + System.lineSeparator(), new String(out.toByteArray()));
	}

	@Test
	public void showError() {
		String unkownOption = JVM.UNKNOWN_OPTION;
		String option1 = "-aaa";
		String option2 = "-bbb";
		JVM.main(new String[] { option1 });
		JVM.main(new String[] { option2, "xxx" });
		assertEquals(
			unkownOption + option1 + System.lineSeparator() + unkownOption + option2 + System.lineSeparator(),
			new String(out.toByteArray())
		);
	}

	@Test
	public void showHelp() throws ClassNotFoundException, IOException {
		JVM.main(new String[] { JVM.OPTION_HELP });
		JVM.main(new String[] {});
		assertEquals(
			JVM.HELP + System.lineSeparator() + JVM.HELP + System.lineSeparator(),
			new String(out.toByteArray())
		);
	}
}
