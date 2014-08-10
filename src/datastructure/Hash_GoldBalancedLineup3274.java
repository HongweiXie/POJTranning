package datastructure;

import java.util.*;
import java.io.*;

public class Hash_GoldBalancedLineup3274 {

	public static final int MOD=10007;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//while(br.readLine()!=null)
		String strs[]=br.readLine().split(" ");
			int N=Integer.parseInt(strs[0]);
			int K=Integer.parseInt(strs[1]);
			
			if(K==1)
			{
				System.out.println(N);
				return;
			}
			
			int num[][]=new int[N+1][K];
			LinkedList<Integer> list[]=new LinkedList[MOD];
			
			for(int i=0;i<N+1;i++)
				Arrays.fill(num[i],0);
			
			for(int i=1;i<=N;i++)
			{
				int id=Integer.parseInt(br.readLine());
				for(int j=0;j<K;j++)
				{
					num[i][j]=num[i-1][j]+id%2;
					id=id>>1;
				}
			}
			
			for(int i=0;i<=N;i++)
			{
				for(int j=1;j<K;j++)
				{
					num[i][j]=num[i][j]-num[i][0];
				}
				num[i][0]=0;
				int h=hash(num[i]);
				num[i][0]=h;
				
				if(list[h]==null)
					list[h]=new LinkedList<Integer>();
				list[h].add(i);
				
			}
			
			int max=0;
			for(int i=1;i<=N;i++)
			{
				List<Integer> temp=list[num[i][0]];
				for(int index:temp)
				{
					if(isSame(num,index,i))
					{
						int dis=Math.abs(index-i);
						if(dis>max)
							max=dis;
					}
				}
			}
			System.out.println(max);
			
		
		br.close();
		
	}

	private static boolean isSame(int[][] num, int m,int n) {

		for(int i=1;i<num[m].length;i++)
		{
			if(num[m][i]!=num[n][i])
				return false;
		}
		return true;
	}

	private static int hash(int[] array) {

		int temp=0;
		for(int i=0;i<array.length;i++)
		{
			temp+=(array[i]);
			temp%=MOD;
		}
		return (temp+MOD)%MOD;
	}

}
