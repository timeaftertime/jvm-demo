package cn.milai.jvmdemo.runtime;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.jvmdemo.runtime.slot.SlotListTest;
import cn.milai.jvmdemo.runtime.stack.StackTestSuite;

@RunWith(Suite.class)
@SuiteClasses(
	{
		RTConstantPoolTest.class, ClassInfoTest.class, SlotListTest.class, StackTestSuite.class, ThreadSpaceTest.class
	}
)
public class RuntimeTestSuite {

}
