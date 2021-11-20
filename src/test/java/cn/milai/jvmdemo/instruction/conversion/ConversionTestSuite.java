package cn.milai.jvmdemo.instruction.conversion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ D2XInstructionsTest.class, F2XInstructionsTest.class, I2XInstructionsTest.class, L2XInstructionsTest.class }
)
public class ConversionTestSuite {

}
