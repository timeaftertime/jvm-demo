package cn.milai.jvmdemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.jvmdemo.classfile.ClassFileTestSuite;
import cn.milai.jvmdemo.constants.ConstantsTestSuite;
import cn.milai.jvmdemo.instruction.InstructionTestSuite;
import cn.milai.jvmdemo.runtime.RuntimeTestSuite;
import cn.milai.jvmdemo.util.ClassNamesTest;

@RunWith(Suite.class)
@SuiteClasses(
	{
		JVMTest.class, ClassFileTestSuite.class, RuntimeTestSuite.class, ClassNamesTest.class,
		DefaultClassInfoLoaderTest.class, ParentClassInfoLoaderTest.class, InstructionTestSuite.class,
		ConstantsTestSuite.class
	}
)
public class AllTestSuite {

}
