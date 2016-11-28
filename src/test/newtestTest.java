package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import domin.Expression;

import org.junit.Test;

public class newtestTest {

	private static Expression  test = new Expression();
	
	String str1="1+x+ y+	2+2x+2*x-y+x^3";
	String str2="1+xhsdfjhaskfhdkashfkasdf+0-sd---";
	String str3="1/2";
	String str41="5^",str42="^",str43="5^x",str44="x^5x+2",str45="/5^";
	String str5="1+x+y";
	
	String wrong="The expression is wrong!Stupid!";
	String newstr;
	ArrayList<String> fuhao;
	@Test
	public void testSetExpressions1() {
		test.myfuhao(str1);
		newstr=test.changeExpression(str1);
		assertEquals(true,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions2() {
		test.myfuhao(str2);
		newstr=test.changeExpression(str2);
		assertEquals(false,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions3() {
		test.myfuhao(str3);
		newstr=test.changeExpression(str3);
		assertEquals(false,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions41() {
		test.myfuhao(str41);
		newstr=test.changeExpression(str41);
		assertEquals(false,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions43() {
		test.myfuhao(str43);
		newstr=test.changeExpression(str43);
		assertEquals(false,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions42() {
		test.myfuhao(str42);
		newstr=test.changeExpression(str42);
		assertEquals(false,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions44() {
		test.myfuhao(str44);
		newstr=test.changeExpression(str44);
		assertEquals(true,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions45() {
		test.myfuhao(str45);
		newstr=test.changeExpression(str45);
		assertEquals(false,test.checkExpressionByRE(newstr));
	}
	@Test
	public void testSetExpressions5() {
		test.myfuhao(str5);
		newstr=test.changeExpression(str5);
		assertEquals(true,test.checkExpressionByRE(newstr));
	}
	
}
