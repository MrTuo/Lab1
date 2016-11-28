package test ; 

import java.util.ArrayList ; 
import java.util.Scanner ;

import control.expressioncontrol;
import domin.Expression;
import domin.item; 

/**
* 
*
* @author HBY
*/
public class newtest {
	//letter numbser新表达式需要初始化
	/**
	 * .
	 * @param args .
	 */
	public static void  main (String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in) ; 
		String expression = null ; //输入的表达式或者是！命令行
		int choice = 0 ; //选择
		expressioncontrol control = new expressioncontrol();
		
		Expression clsExpression = new Expression() ; //创建表达式对象
		
		while(true) {
			choice = 0 ; 
			expression = in.nextLine() ; //输入
			
			if (control.SimplifyControl(expression)) {//如果是赋值选择2
				choice=2 ; 
			} else if (control.DerivativeControl(expression)) {//如果是求导选择3
				choice=3 ; 
			} else if (control.inputControl(expression)) {
				choice=1 ; //其他情况选择1
			} else {
				continue ; 
			}
			switch(choice) {
			case 1:
				control.OutControl(clsExpression, expression);
				break ; 
			case 2:
				control.SimplifyOutControl(clsExpression, expression);
				break ; 
			case 3:
				control.DerivativeOutControl(clsExpression, expression);
				break ; 
			default:
				break; 
			}
		}
	}
}


