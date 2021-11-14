package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoaderInitializer;
import cn.milai.jvmdemo.ParentClassInfoLoader;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions.LDC;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions.LDC2_W;
import cn.milai.jvmdemo.instruction.constant.LdcInstructions.LDC_W;
import cn.milai.jvmdemo.runtime.ClassInfo;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link LdcInstructions} 测试类
 * @author milai
 * @date 2021.11.14
 */
public class LdcInstructionsTest {

	private Instruction ldc;
	private Instruction ldc_w;
	private Instruction ldc2_w;
	private BytecodeReader reader;
	private byte[] codes = { 10, 0, 29, 0, 25 };
	private Frame frame;
	private ClassInfoLoader loader;

	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		loader = new ParentClassInfoLoader() {
			@Override
			protected ClassInfo loadClass(String name) {
				return null;
			}
		};
		ldc = new LDC();
		ldc_w = new LDC_W();
		ldc2_w = new LDC2_W();
		reader = new BytecodeReader(codes);
		ClassInfo info = loader.load(Classes.CLASS_TEST);
		frame = new Frame(null, info.getMethods()[1]);
	}

	@Test
	public void testExecute() throws IOException {
		double delta = 0.01;
		ldc.readOperands(reader);
		ldc_w.readOperands(reader);
		ldc2_w.readOperands(reader);
		ldc.execute(frame);
		assertEquals(1, frame.getOperandStack().popInt());
		ldc_w.execute(frame);
		assertEquals(3.14f, frame.getOperandStack().popFloat(), delta);
		ldc2_w.execute(frame);
		assertEquals(12345678901L, frame.getOperandStack().popLong());
	}

}
