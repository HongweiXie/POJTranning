package simulation;

import java.util.*;
public class Parencodings1068 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for(int t=0;t<T;t++)
		{
			int n=in.nextInt();
			int[] perenthese=new int[n];
			for(int i=0;i<n;i++)
				perenthese[i]=in.nextInt();
			String result=covertToWStr(perenthese,n);
			System.out.println(result);
		}
	}

	private static String covertToWStr(int[] perenthese,int n) {

		List<Integer> list=new ArrayList<Integer>();
		for(int j=0;j<perenthese[0];j++)
		{
			list.add(-1);
		}
		list.add(1);
		for(int i=1;i<n;i++)
		{
			int d=perenthese[i]-perenthese[i-1];
			for(int j=0;j<d;j++)
				list.add(-1);
			list.add(1);
		}
		//System.out.println(list);
		List<Integer> result=new ArrayList<Integer>();
		for(int i=perenthese[0];i<list.size();i++)
		{
			if(list.get(i)==1)
			{
				int total=0;
				int sum=0;
				int p=i-1;
				while(p>=0&&sum>=0)
				{
					if(list.get(p)==-1)
						total++;
					sum+=list.get(p);
					p--;
				}
				result.add(total);
			}
		}
		String str="";
		for(int num:result)
			str+=num+" ";
		return str.substring(0,str.length()-1);
	}

}
