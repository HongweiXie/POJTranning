package datastructure;

import java.util.*;
public class String_SpellChecker1035 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		String s="";
		List<String> dic=new ArrayList<String>();
		while(!(s=in.nextLine()).equals("#"))
		{
			dic.add(s);
		}
		while(!(s=in.nextLine()).equals("#"))
		{
			System.out.println(check(s,dic));
		}
	}

	public static String check(String word,List<String> dic)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<dic.size();i++)
		{
			String dw=dic.get(i);
			if(dw.length()==word.length())
			{
				String s=replace(word,dw);
				if(s!=null)
				{
					if(s.endsWith("is correct"))
						return s;
					else
					{
						sb.append(" ").append(s);
					}
				}
			}
			else if(dw.length()>word.length())
			{
				String s=insert(word,dw);
				if(s!=null)
					sb.append(" ").append(s);
			}
			else
			{
				String s=delete(word,dw);
				if(s!=null)
					sb.append(" ").append(s);
			}
				
			
		}
		
		return word+":"+sb.toString();
	}

	private static String delete(String word, String dw) {

		if(word.length()-dw.length()>1)
			return null;
		char c1[]=word.toCharArray();
		char c2[]=dw.toCharArray();
		int i=0,j=0;
		int cnt=0;
		for(;i<c2.length&&j<c1.length;i++)
		{
			if(c2[i]!=c1[j])
			{
				i--;
				cnt++;
			}
			j++;
		}
		if(cnt<=1)
			return dw;
		else
			return null;
	}

	private static String insert(String word, String dw) {

		if(dw.length()-word.length()>1)
			return null;
		char c1[]=word.toCharArray();
		char c2[]=dw.toCharArray();
		int i=0,j=0;
		int cnt=0;
		for(i=0;i<c1.length&&j<c2.length;i++)
		{
			if(c1[i]!=c2[j])
			{
				i--;
				cnt++;
			}
			j++;
		}
		if(cnt<=1)
		{
			return dw;
		}
		else
			return null;
	}

	private static String replace(String word, String dw) {

		char c1[]=word.toCharArray();
		char c2[]=dw.toCharArray();
		int cnt=0;
	
		for(int i=0;i<c1.length;i++)
		{
			if(c1[i]!=c2[i])
				cnt++;
		}
		if(cnt==0)
		{
			return word+" is correct";
		}
		else if(cnt==1)
		{
			return dw;
		}
		else
			return null;
	}
	
	
}
