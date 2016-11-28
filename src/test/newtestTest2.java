package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domin.Expression;


public class newtestTest2 {
	private static Expression  test = new Expression();
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
		test.out(case1_1);
		output=test.SimplifyOut(case1_2);
		assertEquals(case_1_out,output);
	}
	@Test
	public void testSimplify2() {
		test.out(case2_1);
		output=test.SimplifyOut(case2_2);
		assertEquals(case_2_out,output);
	}
	@Test
	public void testSimplify3() {
		test.out(case3_1);
		output=test.SimplifyOut(case3_2);
		assertEquals(case_3_out,output);
	}
	@Test
	public void testSimplify4() {
		test.out(case4_1);
		output=test.SimplifyOut(case4_2);
		assertEquals(case_4_out,output);
	}

}
