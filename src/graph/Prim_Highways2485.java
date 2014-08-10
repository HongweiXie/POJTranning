package graph;

import java.util.*;

public class Prim_Highways2485 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		int[][] lengthes=new int[500][500];
		
		for(int t=0;t<T;t++)
		{
			int N=in.nextInt();
			in.nextLine();
			for(int i=0;i<N;i++)
			{
				String line=in.nextLine();
				String term[]=line.split(" ");
				for(int j=0;j<N;j++)
				{
					lengthes[i][j]=Integer.parseInt(term[j]);
				}
			}
			int parent[]=minSpanningTree(lengthes,N);
			int longest=maxLength(parent,lengthes);
			System.out.println(longest);
		}
		
	}

	
	private static int maxLength(int[] parent,int weights[][]) {

		int max=0;
		int n=parent.length;
		for(int i=0;i<n;i++)
		{
			if(parent[i]>=0&&weights[i][parent[i]]<Integer.MAX_VALUE)
			{
				if(weights[i][parent[i]]>max)
					max=weights[i][parent[i]];
			}
		}
		return max;
	}

	private static int[] minSpanningTree(int[][] weights,int N) {

		Set<Integer> queue=new HashSet<Integer>();
		int key[]=new int[N];
		int parent[]=new int[N];
		for(int i=0;i<N;i++)
		{
			queue.add(i);
			key[i]=Integer.MAX_VALUE;
			parent[i]=-1;
			
		}
		key[0]=0;
		
		
		while(!queue.isEmpty())
		{
			int u=extractMin(queue,key);
			//遍历所有相邻点，这道题特殊在是一个完全图
			for(int v=0;v<N;v++)
			{
				if(v!=u&&weights[u][v]<Integer.MAX_VALUE&&queue.contains(v))
				{
					if(weights[u][v]<key[v])
					{
						key[v]=weights[u][v];
						parent[v]=u;
					}
				}
			}
			
		}
		return parent;
	}

	private static int extractMin(Set<Integer> set,int key[]) {

		int min=Integer.MAX_VALUE;
		int value=0;
		for(int u:set)
		{
			
			if(key[u]<min)
			{
				min=key[u];
				value=u;
			}
		}
		
		set.remove((Integer)value);
		return value;
	}
}
