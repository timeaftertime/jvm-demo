package cn.milai.jvmdemo.classfile;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.jvmdemo.classfile.constant.ConstantPoolTest;

@RunWith(Suite.class)
@SuiteClasses(
	{
		AccessMaskTest.class, ClassFileTest.class, ClassMetadataTest.class, ConstantPoolTest.class
	}
)
public class ClassFileTestSuite {

}
