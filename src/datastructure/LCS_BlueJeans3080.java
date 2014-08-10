package datastructure;

import java.util.*;
public class LCS_BlueJeans3080 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for(int t=0;t<T;t++)
		{
			int m=in.nextInt();
			String strs[]=new String[m];
			for(int i=0;i<m;i++)
				strs[i]=in.next();
			String result=commonStr(strs);
			System.out.println(result);
		}
	}

	private static String commonStr(String[] strs) {

		if(strs==null||strs.length<1)
			return "";
		if(strs.length==1)
			return strs[0];
		String str=strs[0];
		int len=str.length();
		List<String> list=new ArrayList<String>();
		for(int i=len;i>=3;i--)
		{
			for(int s=0;s<=len-i;s++)
			{
				String sub=str.substring(s,s+i);
				int cnt=0;
				for(int j=1;j<strs.length;j++)
				{
					if(strs[j].contains(sub))
						cnt++;
				}
				if(cnt==strs.length-1)
				{
					list.add(sub);
				}
			}
			if(list.size()>0)
			{
				Collections.sort(list);
				return list.get(0);
			}
		}
		return "no significant commonalities";
	}

	
	
}
