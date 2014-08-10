package datastructure;

import java.util.*;
import java.io.*;
public class Hash_Snowflake3349 {

	static int array[]=new int[6];
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		Set<String> set=new HashSet<String>();
		boolean flag=true;
		for(int i=0;i<n;i++)
		{
			String str=br.readLine();
			String hash=hash(str);
			if(set.contains(hash))
			{
				System.out.println("Twin snowflakes found.");
				flag=false;
				break;
			}
			set.add(hash);
		}
		if(flag)
			System.out.println("No two snowflakes are alike.");
		br.close();
	}

	private static String hash(String str) {

		String terms[]=str.split(" ");
		int h=0;
		
		for(int i=0;i<terms.length;i++)
		{
			array[i]=Integer.parseInt(terms[i]);
		}
		Arrays.sort(array);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<terms.length;i++)
		{
			sb.append(array[i]).append(" ");
		}
		return sb.toString();
	}
}
