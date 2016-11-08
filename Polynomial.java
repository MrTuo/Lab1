package lab1;
import java.util.*;
public class Polynomial {
	private  static int expression(String s)
	{
		int num=0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)<42 || (s.charAt(i)>43 && s.charAt(i)<48) || (s.charAt(i)>57 && s.charAt(i)<97) || s.charAt(i)>122)
			{
				num++;
			}
			else
			{
				String[] newstr=s.split("\\+");
				for(int x=0;x<newstr.length;x++)
				{
					for(int j=0;j<newstr[x].split("\\*").length;j++)
					{
						if(newstr[x].split("\\*")[j].length()>1)
						{
							if(newstr[x].split("\\*")[j].charAt(0)>=97 && newstr[x].split("\\*")[j].charAt(0)<=122)
							{
								num++;
							}
						}
						
					}
				}
			}
			
		}
		return num;
	}
	
	private static void simplify(String s,String s1)
	{
		if(s1.equals("!simplify"))
		{
			System.out.println(s);
		}
		else
		{
			String[] str1=s1.split(" ");
			String s2=s;
			for(int i=1;i<str1.length;i++)
			{
			    s2=s2.replace(str1[i].split("=")[0],str1[i].split("=")[1]);
			}
			System.out.println(s2);
		}
	}
	private static void derivative(String s,String s2)
	{
		String[] str1=s.split("\\+");
		String sb="";
		for(int i=0;i<str1.length;i++)
		{
			int num=0;
			for(int j=0;j<str1[i].length();j++)
			{
				if(str1[i].charAt(j)==s2.charAt(4))
				{
					num++;
				}
			}
				if(num==0)
				{
					sb=sb+"";
				}
				else if(num==1)
				{
					if(str1[i].length() == 1)
					{
						sb=sb+"+1";
					}
					else
					{
						if(str1[i].charAt(0)==s2.charAt(4))
						{
							str1[i]=str1[i].replace((s2.charAt(4)+("*")),"");
						}
						else
						{
					        str1[i]=str1[i].replace((("*")+s2.charAt(4)),"");
						}
					sb=sb+"+"+str1[i];
					}
				}
				else
				{		
					str1[i]=str1[i].replaceFirst(((""+s2.charAt(4))),String.valueOf(num));
					sb=sb+"+"+str1[i];
				}
		}
		System.out.println(sb.substring(1, sb.length()));
	}
	public static void main(String args[])
	{
		Scanner x = new Scanner(System.in);
		String s =x.nextLine();
		if(expression(s) != 0)
		{
			System.out.println("Error!");
		}
		else
		{
		System.out.println(s);
		}
		String s1 =x.nextLine();
		simplify(s,s1);
		String s2 =x.nextLine();
		derivative(s,s2);
		System.exit(0);
	}
}
