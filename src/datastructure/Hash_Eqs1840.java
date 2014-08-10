package datastructure;

import java.util.*;
public class Hash_Eqs1840 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int c[]=new int[5];
		for(int i=0;i<5;i++)
			c[i]=in.nextInt();
		
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		for(int i=-50;i<=50;i++)
		{
			if(i==0)
				continue;
			for(int j=-50;j<=50;j++)
			{
				if(j!=0)
				{
					int k=c[0]*i*i*i+c[1]*j*j*j;
					Integer temp=map.get(k);
					if(temp==null)
						map.put(k,1);
					else
					{
						map.put(k, temp+1);
					}
				}
			}
		}
		
		int cnt=0;
		for(int i=-50;i<=50;i++)
		{
			if(i==0)
				continue;
			for(int j=-50;j<=50;j++)
			{
				if(j==0)
					continue;
				for(int k=-50;k<=50;k++)
				{
					if(k!=0)
					{
						int v=-c[2]*i*i*i-c[3]*j*j*j-c[4]*k*k*k;
						Integer temp=map.get(v);
						if(temp!=null)
						{
							cnt+=temp;
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}

}
