package cn.milai.jvmdemo.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import cn.milai.jvmdemo.MockFactory;
import cn.milai.jvmdemo.instruction.store.StoreInstructions;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ASTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ASTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ASTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ASTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ASTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.DSTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.FSTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.ISTORE_3;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_0;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_1;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_2;
import cn.milai.jvmdemo.instruction.store.StoreInstructions.LSTORE_3;
import cn.milai.jvmdemo.runtime.ObjectRef;
import cn.milai.jvmdemo.runtime.stack.Frame;

/**
 * {@link StoreInstructions} 测试类
 * @author milai
 * @date 2021.11.14
 */
public class StoreInstructionsTest {

	private Instruction istore;
	private Instruction[] istore_x;
	private Instruction lstore;
	private Instruction[] lstore_x;
	private Instruction fstore;
	private Instruction[] fstore_x;
	private Instruction dstore;
	private Instruction[] dstore_x;
	private Instruction astore;
	private Instruction[] astore_x;

	private static final int MAX_LOCAL_VARS_TABLE_CAPACITY = 7;
	private static final int MAX_OPERAND_STACK_CAPACITY = 16;
	private BytecodeReader reader;
	private Frame frame;
	private byte[] codes = new byte[] { 5, 5, 5, 5, 5 };

	@Before
	public void setUp() {
		istore = new ISTORE();
		lstore = new LSTORE();
		fstore = new FSTORE();
		dstore = new DSTORE();
		astore = new ASTORE();

		istore_x = new Instruction[] { new ISTORE_0(), new ISTORE_1(), new ISTORE_2(), new ISTORE_3() };
		lstore_x = new Instruction[] { new LSTORE_0(), new LSTORE_1(), new LSTORE_2(), new LSTORE_3() };
		fstore_x = new Instruction[] { new FSTORE_0(), new FSTORE_1(), new FSTORE_2(), new FSTORE_3() };
		dstore_x = new Instruction[] { new DSTORE_0(), new DSTORE_1(), new DSTORE_2(), new DSTORE_3() };
		astore_x = new Instruction[] { new ASTORE_0(), new ASTORE_1(), new ASTORE_2(), new ASTORE_3() };

		frame = new Frame(null, MockFactory.newMethod(MAX_LOCAL_VARS_TABLE_CAPACITY, MAX_OPERAND_STACK_CAPACITY));
		reader = new BytecodeReader(codes);
	}

	@Test
	public void readOperandsAndExecute() throws IOException {
		readOperands();
		executeTest();
	}

	private void readOperands() throws IOException {
		readNoOperands();
		readUInt8();
	}

	private void readNoOperands() throws IOException {
		istore.readOperands(reader);
		assertEquals(1, reader.getPc());
		lstore.readOperands(reader);
		assertEquals(2, reader.getPc());
		fstore.readOperands(reader);
		assertEquals(3, reader.getPc());
		dstore.readOperands(reader);
		assertEquals(4, reader.getPc());
		astore.readOperands(reader);
		assertEquals(5, reader.getPc());
	}

	private void readUInt8() throws IOException {
		for (int i = 0; i < 4; i++) {
			istore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			lstore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			fstore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			dstore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
		for (int i = 0; i < 4; i++) {
			astore_x[i].readOperands(reader);
			assertEquals(5, reader.getPc());
		}
	}

	private void executeTest() {
		executeLoadTest();
		execute();
	}

	private void executeLoadTest() {
		double delta = 0.01;
		initOperandStack();
		istore.execute(frame);
		assertEquals(6, frame.getLocalVarsTable().getInt(5));
		lstore.execute(frame);
		assertEquals(0x0000000400000005L, frame.getLocalVarsTable().getLong(5));
		fstore.execute(frame);
		assertEquals(Float.intBitsToFloat(3), frame.getLocalVarsTable().getFloat(5), delta);
		dstore.execute(frame);
		assertEquals(Double.longBitsToDouble(0x0000000100000002L), frame.getLocalVarsTable().getDouble(5), delta);
		astore.execute(frame);
		assertSame(null, frame.getLocalVarsTable().getRef(5));
	}

	private void initOperandStack() {
		frame.getOperandStack().pushRef(null);
		for (int i = 1; i <= 6; i++) {
			frame.getOperandStack().pushInt(i);
		}
	}

	private void execute() {
		double delta = 0.01;
		for (int i = 0; i < 4; i++) {
			frame.getOperandStack().pushInt(3 - i);
		}
		for (int i = 0; i < 4; i++) {
			istore_x[i].execute(frame);
			assertEquals(i, frame.getLocalVarsTable().getInt(i));
		}
		for (int i = 0; i < 4; i++) {
			frame.getOperandStack().pushLong(3 - i);
		}
		for (int i = 0; i < 4; i++) {
			lstore_x[i].execute(frame);
			assertEquals(i, frame.getLocalVarsTable().getLong(i));
		}
		for (int i = 0; i < 4; i++) {
			frame.getOperandStack().pushFloat(3 - i);
		}
		for (int i = 0; i < 4; i++) {
			fstore_x[i].execute(frame);
			assertEquals(Float.valueOf(i), frame.getLocalVarsTable().getFloat(i), delta);
		}
		for (int i = 0; i < 4; i++) {
			frame.getOperandStack().pushDouble(3 - i);
		}
		for (int i = 0; i < 4; i++) {
			dstore_x[i].execute(frame);
			assertEquals(Double.valueOf(i), frame.getLocalVarsTable().getDouble(i), delta);
		}
		ObjectRef[] refs = new ObjectRef[4];
		for (int i = 0; i < 4; i++) {
			refs[i] = new ObjectRef(null, 0);
			frame.getOperandStack().pushRef(refs[i]);
		}
		for (int i = 0; i < 4; i++) {
			astore_x[i].execute(frame);
			assertEquals(refs[3 - i], frame.getLocalVarsTable().getRef(i));
		}
	}

}