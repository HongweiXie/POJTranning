package dp;

import java.util.*;
public class Balance1837 {

	static int hook[];
	static int weight[];
	static int cnt;
	static int result[][];
	static int min;
	static int max;
	static int len;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int c=in.nextInt();
		int g=in.nextInt();
		hook=new int[c];
		weight=new int[g];
		int sum=0;
		for(int i=0;i<c;i++)
		{
			hook[i]=in.nextInt();
		}
		for(int i=0;i<g;i++)
		{
			weight[i]=in.nextInt();
			sum+=weight[i];
		}
		min=hook[0]*sum;
		max=hook[hook.length-1]*sum;
		len=max-min+10;
		result=new int[len][weight.length];
		for(int i=0;i<len;i++)
			Arrays.fill(result[i], -1);
		//cnt=0;
		cnt=find(0,0);
		System.out.println(cnt);
	}
	private static int find(int sum, int index) {
		if(index==weight.length)
		{
			if(sum==0)
			{
				//cnt++;
				return 1;
			}
			return 0;
		}
		if(result[sum-min][index]!=-1)
			return result[sum-min][index];
		int c=0;
		for(int i=0;i<hook.length;i++)
		{
			c+=find(sum+hook[i]*weight[index],index+1);
		}
		result[sum-min][index]=c;
		return c;
		
	}

}
