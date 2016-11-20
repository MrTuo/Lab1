package experiment_1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class experiment_1Test {
	private static Expressions  test = new Expressions();
	//所有测试输入
	String case1_1="";
	String case1_2="!simplify error input";
	String case2_1="";
	String case2_2="!simplify x=1";
	String case3_1="x+y+z+1";
	String case3_2="!simplify m=1";
	String case4_1="x+y+z+1";
	String case4_2="!simplify x=1";
	
	//期望输出
	String case_1_out="Wrong input!";
	String case_2_out="Error There is no expression!";
	String case_3_out="1*x+1*y+1*z+1";
	String case_4_out="2+1*y+1*z";
	
	//实际输出
	String output = "";

	@Test
	public void testSimplify() {
		test.setExpressions(case1_1);
		output=experiment_1.simplify(test, case1_2);
		assertEquals(case_1_out,output);
	}
	@Test
	public void testSimplify2() {
		test.setExpressions(case2_1);
		output=experiment_1.simplify(test, case2_2);
		assertEquals(case_2_out,output);
	}
	@Test
	public void testSimplify3() {
		test.setExpressions(case3_1);
		output=experiment_1.simplify(test, case3_2);
		assertEquals(case_3_out,output);
	}
	@Test
	public void testSimplify4() {
		test.setExpressions(case4_1);
		output=experiment_1.simplify(test, case4_2);
		assertEquals(case_4_out,output);
	}

}
