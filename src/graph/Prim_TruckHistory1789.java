package graph;

import java.util.*;
public class Prim_TruckHistory1789 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int T;
		String[] typecodes=new String[2000];
		int weights[][]=new int[2000][2000];
		while((T=in.nextInt())!=0)
		{
			
			for(int t=0;t<T;t++)
			{
				typecodes[t]=in.next();
			}
			for(int i=0;i<T;i++)
			{
				for(int j=i;j<T;j++)
				{
					if(i==j)
						weights[i][i]=0;
					else
					{
						weights[i][j]=distance(typecodes[i],typecodes[j]);
						weights[j][i]=weights[i][j];
					}
				}
			}
			int parent[]=minSpanningTree(weights,T);
			int quality=calQuality(parent,weights);
			System.out.println("The highest possible quality is 1/"+quality+".");
			/*Runtime rt = Runtime.getRuntime();
			long freeMemory = rt.freeMemory();
			System.out.println("当前 Java 虚拟机中的空闲内存量：" + (rt.totalMemory()-freeMemory)/1000000 + "M 字节");*/
		}
		
		
	}
	
	private static int calQuality(int[] parent,int weights[][]) {

		int sum=0;
		int n=parent.length;
		for(int i=0;i<n;i++)
		{
			if(parent[i]>=0&&weights[i][parent[i]]<Integer.MAX_VALUE)
			{
				sum+=weights[i][parent[i]];
			}
		}
		return sum;
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
				if(v!=u&&queue.contains(v))
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

	public static int distance(String a,String b)
	{
		int counter=0;
		for(int i=0;i<a.length();i++)
		{
			if(a.charAt(i)!=b.charAt(i))
				counter++;
		}
		return counter;
	}

}

/*class Node implements Comparable<Node>
{
	int id;
	int key;
	public Node(int id,int w)
	{
		this.id=id;
		this.key=w;
	}
	@Override
	public int compareTo(Node o) {

		if(this.key>o.key)
			return 1;
		else if(this.key<o.key)
			return -1;
		return 0;
	}
}*/

