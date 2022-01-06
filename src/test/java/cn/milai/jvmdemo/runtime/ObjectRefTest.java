package cn.milai.jvmdemo.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.milai.jvmdemo.ClassInfoLoader;
import cn.milai.jvmdemo.Classes;
import cn.milai.jvmdemo.DefaultClassInfoLoader;
import cn.milai.jvmdemo.DefaultClassInfoLoaderInitializer;
import cn.milai.jvmdemo.classfile.TypeDesc;
import cn.milai.jvmdemo.constants.ClassConst;

/**
 * {@link ObjectRef} 测试类
 * @author milai
 * @date 2022.01.06
 */
public class ObjectRefTest {

	private static ClassInfoLoader loader;

	@BeforeClass
	public static void setUpClass() {
		DefaultClassInfoLoaderInitializer.initDefaultClassInfoLoader();
		loader = DefaultClassInfoLoader.getInstance();
	}

	@Test
	public void newArrayObjectRef() {
		ClassInfo c = loader.load(ClassConst.arrayDescOf(Classes.OBJECT));
		ObjectRef o = new ObjectRef(c, 1);
		assertEquals(ClassConst.ARRAY_PREFIX + Classes.OBJECT, c.getName());
		assertNull(o.getElements().getRef(0));
	}

	@Test
	public void newPrimitiveAndArray() {
		ClassInfo intClass = loader.load(TypeDesc.INT.getValue());
		assertNotNull(intClass);
		assertNull(intClass.getSuperClassInfo());
		ClassInfo intArray = DefaultClassInfoLoader.getInstance().load(ClassConst.arrayDescOf(intClass.getName()));
		assertNotNull(intArray);
		assertEquals(ClassConst.OBJECT, intArray.getSuperClassInfo().getName());
		ObjectRef array = new ObjectRef(intArray, 1);
		assertEquals(0, array.getElements().getInt(0));
	}

}
