package domin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import experiment_1.Expressions;

public class Expression {
	private String expression;
	private String simplifyexpression;
	private String derivativeexpression;
	private item[] items;
	private ArrayList<String> fuhao;
	private ArrayList<String>  number = new ArrayList<String> () ; //记录赋值函数的值
	private ArrayList<String>  letter = new ArrayList<String> () ; //赋值函数的类型
//	ArrayList<String>  fuhao = new ArrayList<String> () ; //存放表达式中的+号或者是-号的位置顺序
	public String getSimplifyexpression() {
		return simplifyexpression;
	}
	public void setSimplifyexpression(String simplifyexpression) {
		this.simplifyexpression = simplifyexpression;
	}
	public String getDerivativeexpression() {
		return derivativeexpression;
	}
	public void setDerivativeexpression(String derivativeexpression) {
		this.derivativeexpression = derivativeexpression;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public item[] getItems() {
		return items;
	}
	public void setItems(item[] items) {
		this.items = items;
	}
	public ArrayList<String> getFuhao() {
		return fuhao;
	}
	public void setFuhao(ArrayList<String> fuhao) {
		this.fuhao = fuhao;
	}
	public String changeExpression (String expression) {
		String changeexpression=null ; 
		String endexpression = "" ; 
		String[] str ;  
		String[] xiang ; 
		String newstr = null ; 
		String newxiang = null ; 
		int xishu = 0 ; 
		int fuhao1 = 0 ; //标记到第几个符号了
		changeexpression=expression.replace("\t", "") ; //将tab删掉
		changeexpression=changeexpression.replace(" ", "") ; //将空格删掉

		for (int i = 0 ; i < changeexpression.length() ; i++){
			if (i < changeexpression.length()-1) {
				if (changeexpression.charAt(i) >= '0'&&changeexpression.charAt(i) <= '9') {
					if (changeexpression.charAt(i+1) >= 'a'&&changeexpression.charAt(i+1) <= 'z'||changeexpression.charAt(i+1) >= 'A'&&changeexpression.charAt(i+1) <= 'Z') {
						changeexpression = changeexpression.substring(0,i+1) + "*" + changeexpression.substring(i + 1) ; 
					}
				}
			}
		}
		
		endexpression = changeexpression ; 
		str = endexpression.split("\\+|\\-") ; 
		items = new item[str.length];
		System.out.println(items.length);
		for (int i = 0 ; i < str.length ; i++){//依次访问每个项
			newstr = str[i] ; 
			xiang = str[i].split("\\*") ; 
//			xiang = items[i].getFactors();
			for (int j = 0 ; j < xiang.length ; j++) {//对每个项访问
				newxiang=xiang[j] ; 
				for (int k = 0 ; k < xiang[j].length() ; k++) {
					if (xiang[j].charAt(k) == '^') {
						for (int l = k+1 ; l < xiang[j].length() ; l++) {//计算^后面的系数是多少
							if (xiang[j].charAt(l) < '0'||xiang[j].charAt(l) > '9') {//出现错误，直接返回，在judge中会判断为错误表达式
								return endexpression ; 
							} else {
								xishu = xishu * 10 + (xiang[j].charAt(l) - 48) ; 
							}
						}
						if(k==xiang[j].length()-1)
							return endexpression;
						newxiang = newxiang.substring(0, newxiang.indexOf("^")) ; 
						for (int m = 0 ; m < xishu - 1 ; m++) {
							newxiang += "*" + xiang[j].substring(0, xiang[j].indexOf("^")) ; 
						}
						xiang[j] = newxiang ; 
					}
				}
			}
			items[i]=new item();
			items[i].setFactors(xiang);
			xishu = 0 ; 
			newxiang = xiang[0] ; 
			for (int n = 1 ; n < xiang.length ; n++) {
				newxiang += "*" + xiang[n] ; 
			}
			str[i] = newxiang ; 
			items[i].setItemStr(newxiang);
		}
		//每个项都处理完了，该合并项了
		endexpression = "" ; 
		for (int i = 0 ; i < str.length ; i++){
			endexpression += str[i] ; 
			if (i<str.length-1) {
				endexpression += fuhao.get(fuhao1++) ; 
			}
		}
//		this.expression = endexpression;
		return endexpression;
	}
	public void myfuhao(final String endexpression) {
		ArrayList<String> fuhao=new ArrayList<String>() ; 
		for (int i=0 ; i<endexpression.length() ; i++) {
			if(endexpression.charAt(i) == '+') {
				fuhao.add("+") ; 
			} else if(endexpression.charAt(i) == '-') {
				fuhao.add("-") ; 
			}
		}
		this.fuhao = fuhao ; 
	}
	public String simplify(ArrayList<String> letter,ArrayList<String> number) {
		String end=null;
		end =expression;
			/*for (int i=0;i<letter.size();i++){
				System.out.println(letter.get(i)+' '+number.get(i));
			}*/
		for (int i=0;i<letter.size();i++){
			end=end.replace(letter.get(i), number.get(i));
			//System.out.println(end);
		}
		return end;
	}
	public String derivative(item[] itemCount,char var,String expression,ArrayList<String> fuhao){//求导初始化
		int varNum[];//变量的个数
		/**
		 * @return data manager
		 */
		/**
		* set default mock parameter.（方法说明）
		* @param additionalParameters parameter additional(参数名称)
		* @return data manager(返回值说明)
		* @throws Exception if has error(异常说明)
		*/
		ArrayList<String>  newitemCount = new ArrayList<String> ();
		ArrayList<String>  newfuhao = new ArrayList<String> ();
		String item=null;
		String end="";
		int o=0;
		varNum = new int [itemCount.length];
		if(expression.indexOf(var)==-1){
//			System.out.println("0");
			return null;
		}
		else{
			for (int i=0;i<itemCount.length;i++){
				item=itemCount[i].getItemStr();
				for (int j=0;j<item.length();j++){//计算变量的个数
					if(var==item.charAt(j)){
						varNum[i]++;
					}
				}
			}

			for (int i=0;i<itemCount.length;i++){//合成求导后的项
				if(varNum[i]!=0){
					if(i<itemCount.length-1){
						//System.out.println(fuhao);
						//System.out.println(fuhao.get(i));
						newfuhao.add(fuhao.get(i));
					}
					//System.out.println(Integer.toString(xishu[i])+"*"+itemCount[i].replaceFirst(String.valueOf(var), String.valueOf(varNum[i])));
					newitemCount.add(item.replaceFirst(String.valueOf(var), String.valueOf(varNum[i])));
					//System.out.println(newitemCount);
				}
			}
			for (int i=0;i<newitemCount.size();i++){//将合成的项连起来
				if(i==0){
					end+=newitemCount.get(i);
				}
				else {
					end+=newfuhao.get(o++)+newitemCount.get(i);
				}
			}
		}
		return end;
	}
	public boolean checkExpressionByRE(String expression){
    	String reNum="[0-9]+(\\.[0-9]*)?";//匹配整数或者小数（正数）
		String reFactor=reNum+"+|[a-z]|[A-Z]";//匹配因子
		String reItem="("+reFactor+")((\\*)("+reFactor+"))*";//匹配多项式
		String reExp="("+reItem+")((\\+|-)("+reItem+"))*";//匹配表达式
		Pattern pattern = Pattern.compile(reExp);
        return pattern.matcher(expression).matches();
    }
	public String makeSimple(String expression,ArrayList<String> items ,int[] signArr){
    	String[] factors = null;//保存多项式的因子
        String newExpression = null;//保存最终化简结果
        int[] newXishu  = new int[items.size()];//化简后的系数数组
        ArrayList<String> newItems = items;//化简后的多项式
        int tmpXishu = 0;//临时系数
        String tmpItem = null;//临时多项式
        
        for (int i = 0; i < newXishu.length; i++)
        {
            newXishu[i]=1;
        }
        for (int i=0;i<newItems.size();i++)//对每个多项式内部的数字相乘化简
        {
            factors = newItems.get(i).split("\\*");//用*分离出多项式的每个因子
            for (int j = 0; j < factors.length; j++)//计算系数
            {
                if (isInteger(factors[j]))//如果是数字
                {
                    newXishu[i] *= Integer.parseInt(factors[j]);
                }
            }
            
            tmpItem = Integer.toString(newXishu[i]);
            for (int k = 0; k < factors.length; k++)//重新对多项式组合
            {
                if (!isInteger(factors[k]))//如果不是数字
                {
                    tmpItem+=("*"+factors[k]);
                }
            }
            newItems.set(i, tmpItem);
        }
        for (int i=0;i<newItems.size();i++)//合并同类项
        {
        	for(int j=i+1;j<newItems.size();j++)
        	{
        		if(isSameItem(newItems.get(i),newItems.get(j)))//如果是同类项
        		{
        			tmpXishu=signArr[i]*newXishu[i]+signArr[j]*newXishu[j];//计算新的系数
        			tmpItem=Integer.toString(tmpXishu);
        			
        			factors = newItems.get(i).split("\\*");//用*分离出多项式的每个因子
                    for (int k = 0; k < factors.length; k++)//重新对多项式组合
                    {
                        if (!isInteger(factors[k]))//如果不是数字
                        {
                            tmpItem+=("*"+factors[k]);
                        }
                    }
                    newItems.set(i, tmpItem);//更新i位置项
                    newItems.remove(j);//删除j位置项
                    
                    newXishu[i]=tmpXishu;//更新i位置系数
                    
                    for(int l = j; l<newItems.size();l++){
                    	signArr[l]=signArr[l+1];//更新符号数组，j位置前移
                    	newXishu[l]=newXishu[l+1];//更新系数数组，j位置前移
                    }                  
                    
                    j-=1;//删除后，新的项占了原来位置，需要减一，否则判断同类项会露项。
                    
        		}
        	}
        }
        if(newItems.size()==0){
        	return "";
        }
        else{
        	newExpression=newItems.get(0);
	        for (int i = 1; i < newItems.size(); i++) {//合成最终化简结果
	        	newExpression += "+" + newItems.get(i);
			}
        }
        return newExpression;
    }
	private static boolean isSameItem(String string1, String string2){
    	String[] factor1,factor2;
		ArrayList<String> newFactors1 = new ArrayList<String>();
		ArrayList<String> newFactors2 = new ArrayList<String>();
		factor1=string1.split("\\*");//按*号或者数字拆分
		factor2=string2.split("\\*");
		
		Arrays.sort(factor1);
		Arrays.sort(factor2);
		for (int i = 0; i < factor1.length; i++) {
			if(!isInteger(factor1[i])){
				newFactors1.add(factor1[i]);
			}
		}
		for (int i = 0; i < factor2.length; i++) {
			if(!isInteger(factor2[i])){
				newFactors2.add(factor2[i]);
			}
		}
		
		if(newFactors1.size() != newFactors2.size()){
			return false;
		}
		for (int i = 0; i < newFactors2.size(); i++) {
			if(!newFactors1.get(i).equals(newFactors2.get(i))){
				return false;
			}
		}
    	return true;
    }
    /**
    * @param fact为多项式中的因子
    * @return 是数字返回1，否则返回0。
    */
    private static boolean isInteger(String fact)
    {
        Pattern pattern = Pattern.compile("[0-9]*|-[0-9]*");
        return pattern.matcher(fact).matches();
    }
    public ArrayList<String> setItems(String expression)
    {
    	String[] tmpItems = expression.split("\\+|\\-");
    	ArrayList<String> items =  new ArrayList<String>();
    	for (int i = 0; i < tmpItems.length; i++) {
			items.add(tmpItems[i]);
		}
        return items;
    }
    public int[] setSign(String expression, ArrayList<String> items)
    {
    	int[] sign = new int[items.size()];
    	int j=0;
    	if(expression.charAt(0) == '-'){//处理第一个多项式
    		sign[j++] = -1;
    	}
    	else{
    		sign[j++] = 1;
    	}
    
    	for(int i = 1;i<expression.length();i++)//处理后续多项式
    	{
    		if(expression.charAt(i) == '+')
    		{
    			sign[j++]=1;
    		}
    		else if (expression.charAt(i) == '-')
    		{
    			sign[j++]=-1;
    		}
    	}
    	
    	return sign;
    }
//    public String SimplifyOut(String input){
//    	String tmpExpression = "" ; 
//    	int j = 0;
////		tmpExpression = clsExpression.getExpression() ; 
//    	tmpExpression = expression ; 
//		if (tmpExpression.equals("")) {
//			return "Error There is no expression!"; //在没有表达式的情况下无法赋值
//		} else {
//			for (int i=input.length()-1 ; i>=0 ; i--) {//获得要赋值的变量
//				
//				if(input.charAt(i)=='=') {
//					j=i ; 
//					number.add(input.substring(i+1,j+2)) ; //获得要赋值变量的值
//				} else if(input.charAt(i)==' ') {
//					letter.add(input.substring(i+1, j)) ; //获得要赋值变量的名称
//				}
//			}
////			clsExpression.setSimplifyexpression(clsExpression.simplify(letter, number));
//			simplifyexpression=simplify(letter, number);
//			if (simplifyexpression!=null){
//				ArrayList<String> newItem = setItems(simplifyexpression);//获取多项式
//				int[] newSign = setSign(simplifyexpression, newItem);//获取多项式符号数组
////				String newExpression = makeSimple(end, newItem, newSign);
//				simplifyexpression=makeSimple(simplifyexpression, newItem, newSign);
//				//计算赋值后的结果，在函数中输出
//			}
//			number.clear() ; 
//			letter.clear() ; 
//			return simplifyexpression; 
//		}
//    }
    public String SimplifyOut(String input){
    	ArrayList<String>  number = new ArrayList<String> () ; //记录赋值函数的值
		ArrayList<String>  letter = new ArrayList<String> () ; //赋值函数的类型
		String tmpExpression = "" ; 
		int j = 0 ; 
		String simplifyexpression = "";
		String reNum="[0-9]+(\\.[0-9]*)?";
		String re="!simplify ([a-z]|[A-Z])="+reNum;
		Pattern pattern = Pattern.compile(re);
		//tmpExpression = clsExpression.getExpression() ; 
		tmpExpression = expression ; 
		if (pattern.matcher(input).matches()){
			if (tmpExpression.equals("")) {
				return "Error There is no expression!"; //在没有表达式的情况下无法赋值
			} else {
				for (int i=input.length()-1 ; i>=0 ; i--) {//获得要赋值的变量
					if(input.charAt(i)=='=') {
						j=i ; 
						number.add(input.substring(i+1,j+2)) ; //获得要赋值变量的值
					} else if(input.charAt(i)==' ') {
						letter.add(input.substring(i+1, j)) ; //获得要赋值变量的名称
					}
				}
				simplifyexpression = simplify(letter,number);
				ArrayList<String> newItem = setItems(simplifyexpression);//获取多项式
				int[] newSign = setSign(simplifyexpression, newItem);//获取多项式符号数组
				simplifyexpression = makeSimple(simplifyexpression, newItem, newSign);
				number.clear() ; 
				letter.clear() ; 
				return simplifyexpression ; //计算赋值后的结果，在函数中输出
			}
		}
		else{
			return "Wrong input!";
		}
    }
    public void DerivativeOut(String input){
    	String tmpExpression ="" ; 
		tmpExpression = expression ; 
		if(expression.equals("")) {
			System.out.println("Error There is no expression!") ; //在没有表达式的情况下无法求导
		} else {//有了表达式
			//首先要将求导的变量求出来 变量只能是在 !d/d()括号里面的值 "()"是不能存在的 且变量只能有字母组成
			char varDerivative = input.charAt(4) ; //获得要求导的变量 这里变量只能是单个字符
			int j = 0 ; 
			item[] xiang = items;
			tmpExpression=derivative(xiang,varDerivative,tmpExpression,fuhao);
			if (tmpExpression != null){
				ArrayList<String> newItem = setItems(tmpExpression);//获取多项式
				int[] newSign = setSign(tmpExpression, newItem);//获取多项式符号数组
				tmpExpression=makeSimple(tmpExpression, newItem, newSign);
				derivativeexpression=tmpExpression;
				System.out.println(derivativeexpression) ; //输出求导后的结果，在函数中输出
			}
			else {
				System.out.println("0");
			}
		}
    }
    public void out(String input){
    	myfuhao(input);
		expression = changeExpression(input);
		if (checkExpressionByRE(expression)){
			setExpression(expression);
			System.out.println(getExpression());
		}
		else{
			System.out.println("The expression is wrong!Stupid!");
		}
    }
}
