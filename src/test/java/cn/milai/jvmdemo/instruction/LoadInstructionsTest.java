package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.load.LoadInstructions;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ALOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.DLOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.FLOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.ILOAD_3;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_0;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_1;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_2;
import cn.milai.jvmdemo.instruction.load.LoadInstructions.LLOAD_3;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;
import cn.milai.jvmdemo.runtime.stack.LocalVarsTable;

/**
 * {@link LoadInstructions} 测试类
 * @author milai
 * @date 2021.11.14
 */
public class LoadInstructionsTest {

	private Instruction iload;
	private Instruction[] iload_xs;
	private Instruction lload;
	private Instruction[] lload_xs;
	private Instruction fload;
	private Instruction[] fload_xs;
	private Instruction dload;
	private Instruction[] dload_xs;
	private Instruction aload;
	private Instruction[] aload_xs;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 10;
	private static final int MAX_OPERAND_STACK_CAPACITY = 10;
	private Frame frame;
	private BytecodeReader reader;
	private byte[] codes = new byte[] { 5, 5, 5, 5, 5 };
	private static ObjectRef[] refs = new ObjectRef[5];

	@Before
	public void setUp() {
		for (int i = 0; i < 5; i++) {
			refs[i] = new ObjectRef(null, 0);
		}

		iload = new ILOAD();
		lload = new LLOAD();
		fload = new FLOAD();
		dload = new DLOAD();
		aload = new ALOAD();

		iload_xs = new Instruction[] { new ILOAD_0(), new ILOAD_1(), new ILOAD_2(), new ILOAD_3() };
		lload_xs = new Instruction[] { new LLOAD_0(), new LLOAD_1(), new LLOAD_2(), new LLOAD_3() };
		fload_xs = new Instruction[] { new FLOAD_0(), new FLOAD_1(), new FLOAD_2(), new FLOAD_3() };
		dload_xs = new Instruction[] { new DLOAD_0(), new DLOAD_1(), new DLOAD_2(), new DLOAD_3() };
		aload_xs = new Instruction[] { new ALOAD_0(), new ALOAD_1(), new ALOAD_2(), new ALOAD_3() };

		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}

	@Test
	public void testExecute() throws IOException {
		readOperands();
		execute();
	}

	private void readOperands() throws IOException {
		readUint8Test();
		readNoOperandsTest();
	}

	private void execute() {
		double delta = 0.001;
		initLocalVarsTableWithInt();
		iload.execute(frame);
		assertEquals(5, frame.getOperandStack().popInt());
		for (int i = 0; i < 4; i++) {
			iload_xs[i].execute(frame);
			assertEquals(i, frame.getOperandStack().popInt());
		}
		lload.execute(frame);
		assertEquals(0x0000000500000006L, frame.getOperandStack().popLong());
		for (int i = 0; i < 4; i++) {
			lload_xs[i].execute(frame);
			assertEquals(i * 0x0000000100000000L + (i + 1) * 0x0000000000000001L, frame.getOperandStack().popLong());
		}
		fload.execute(frame);
		assertEquals(Float.intBitsToFloat(0x00050006), frame.getOperandStack().popFloat(), delta);
		for (int i = 0; i < 4; i++) {
			fload_xs[i].execute(frame);
			assertEquals(
				Float.intBitsToFloat(i * 0x00010000 + (i + 1) * 0x00000001), frame.getOperandStack().popFloat(), delta
			);
		}
		dload.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000500000006L), frame.getOperandStack().popDouble(), delta);
		for (int i = 0; i < 4; i++) {
			dload_xs[i].execute(frame);
			assertEquals(
				Double.longBitsToDouble(i * 0x0000000100000000L + (i + 1) * 0x0000000000000001L),
				frame.getOperandStack().popDouble(), delta
			);
		}
		initLocalVarsTableWithRef();
		aload.execute(frame);
		assertSame(refs[4], frame.getOperandStack().popRef());
		for (int i = 0; i < 4; i++) {
			aload_xs[i].execute(frame);
			assertEquals(refs[i], frame.getOperandStack().popRef());
		}
	}

	private void readUint8Test() throws IOException {
		iload.readOperands(reader);
		assertEquals(1, reader.getPc());
		lload.readOperands(reader);
		assertEquals(2, reader.getPc());
		fload.readOperands(reader);
		assertEquals(3, reader.getPc());
		dload.readOperands(reader);
		assertEquals(4, reader.getPc());
		aload.readOperands(reader);
		assertEquals(5, reader.getPc());
	}

	private void readNoOperandsTest() throws IOException {
		for (int i = 0; i < 4; i++) {
			iload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			lload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			fload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			dload_xs[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
	}

	private void initLocalVarsTableWithInt() {
		LocalVarsTable table = frame.getLocalVarsTable();
		table.setInt(0, 0);
		table.setInt(1, 1);
		table.setInt(2, 2);
		table.setInt(3, 3);
		table.setInt(4, 4);
		table.setInt(5, 5);
		table.setInt(6, 6);
		table.setRef(9, refs[0]);
	}

	private void initLocalVarsTableWithRef() {
		LocalVarsTable table = frame.getLocalVarsTable();
		for (int i = 0; i < 5; i++) {
			table.setRef(i, refs[i]);
		}
		table.setRef(5, refs[4]);
	}
}