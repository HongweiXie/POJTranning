package datastructure;

import java.util.*;
public class String_AllinAll1936 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		while(in.hasNext())
		{
			String s=in.next();
			String t=in.next();
			System.out.println(contains(s,t));
		}
	}

	private static String contains(String s, String t) {

		char cs[]=s.toCharArray();
		char ct[]=t.toCharArray();
		int j=0;
		for(int i=0;i<ct.length;i++)
		{
			if(ct[i]==cs[j])
				j++;
			if(j==cs.length)
				return "Yes";
		}
		return "No";
	}

}
