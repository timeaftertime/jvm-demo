package cn.milai.jvmdemo.runtime.stack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{
		JVMStackTest.class, LocalVarsTableTest.class, OperandStackTest.class
	}
)
public class StackTestSuite {

}
