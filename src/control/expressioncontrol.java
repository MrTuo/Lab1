package control;

import domin.Expression;

public class expressioncontrol {
	public boolean SimplifyControl(String expression){
		if (expression.length() >= 9 && expression.substring(0, 9).equals("!simplify"))
			return true;
		else 
			return false;
	}
	public boolean DerivativeControl(String expression){
		if (expression.length() >= 4 && expression.substring(0, 4).equals("!d/d"))
			return true;
		else 
			return false;
	}
	public boolean inputControl(String expression){
		if (expression.length() < 0 || expression.length() >= 0)
			return true;
		else 
			return false;
	}
	
	public void SimplifyOutControl(Expression expression,String input){
		expression.SimplifyOut(input);
	}
	public void DerivativeOutControl(Expression expression,String input){
		expression.DerivativeOut(input);
	}
	public void OutControl(Expression expression,String input){
		expression.out(input);
	}
	
}
