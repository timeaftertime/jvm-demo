package cn.milai.jvmdemo.runtime;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{
		RTConstantPoolTest.class, ClassInfoTest.class
	}
)
public class RuntimeTestSuite {

}
