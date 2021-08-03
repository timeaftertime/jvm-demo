package cn.milai.jvmdemo.classfile;

import static org.junit.Assert.assertEquals;

import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Test;

import cn.milai.jvmdemo.classfile.constant.ConstantPool;
import cn.milai.jvmdemo.classfile.constant.FieldrefConstant;
import cn.milai.jvmdemo.classfile.constant.MethodrefConstant;
import cn.milai.jvmdemo.classfile.constant.NameAndTypeConstant;

/**
 * {@link ConstantPool} 测试类
 * @author milai
 * @date 2021.07.29
 */
public class ConstantPoolTest {

	@Test
	public void testParseConstantPool() throws IOException {
		ConstantPool pool = createConstantPool(ClassFiles.classTest());
		assertEquals(59, pool.size());
		MethodrefConstant methodRef = pool.getMethodref(1);
		assertEquals(6, methodRef.getClassIndex());
		assertEquals(44, methodRef.getNameAndTypeIndex());
		FieldrefConstant fieldRef = pool.getFieldref(2);
		assertEquals(45, fieldRef.getClassIndex());
		assertEquals(46, fieldRef.getNameAndTypeIndex());
		assertEquals(47, pool.getString(3).getIndex());
		assertEquals(50, pool.getClass(5).getIndex());
		assertEquals("FLAG", pool.getUTF8(7).getValue());
		assertEquals(Integer.valueOf(1), pool.getInteger(10).getValue());
		assertEquals(Long.valueOf(12345678901L), pool.getLong(25).getValue());
		assertEquals(Float.valueOf(Float.intBitsToFloat(0x4048F5C3)), pool.getFloat(29).getValue());
		assertEquals(Double.valueOf(Double.longBitsToDouble(0x4005BF0995AAF790L)), pool.getDouble(32).getValue());
		NameAndTypeConstant nameAndType = pool.getNameAndType(44);
		assertEquals(34, nameAndType.getNameIndex());
		assertEquals(35, nameAndType.getDescriptorIndex());
	}

	@Test
	public void testClassIndex() throws IOException {
		ConstantPool pool = createConstantPool(ClassFiles.helloWorld());
		assertEquals("cn/milai/jvmdemo/classfile/HelloWorld", pool.getUTF8(pool.getClass(5).getIndex()).getValue());
		assertEquals("java/lang/Object", pool.getUTF8(pool.getClass(6).getIndex()).getValue());
	}

	private static ConstantPool createConstantPool(DataInputStream in) throws IOException {
		skipMagicNumber(in);
		skipVersion(in);
		return new ConstantPool(in);
	}

	private static void skipMagicNumber(DataInputStream in) throws IOException {
		for (int i = 0; i < 4; i++) {
			in.readUnsignedByte();
		}
	}

	private static void skipVersion(DataInputStream in) throws IOException {
		in.readUnsignedShort();
		in.readUnsignedShort();
	}

}
