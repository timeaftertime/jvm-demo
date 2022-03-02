package cn.milai.jvmdemo.instruction.reference;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{
		NewTest.class, AnewArrayTest.class, ArrayLengthTest.class, CheckCastTest.class, InstanceofTest.class,
		NewArrayTest.class, GetFieldTest.class, GetStaticTest.class, PutFieldTest.class, PutStaticTest.class, AthrowTest.class
	}
)
public class ReferenceTestSuite {

}
