package graph;

import java.util.*;
public class Kruskal_TruckHistory1789 {

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
			int tree[][]=minSpanningTree(weights,T);
			int quality=calQuality(tree);
			System.out.println("The highest possible quality is 1/"+quality+".");
			Runtime rt = Runtime.getRuntime();
			long freeMemory = rt.freeMemory();
			//System.out.println("当前 Java 虚拟机中的空闲内存量：" + (rt.totalMemory()-freeMemory)/1000000 + "M 字节");
		}
		
		
	}
	
	private static int calQuality(int[][] tree) {

		int sum=0;
		int n=tree.length;
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if(tree[i][j]<Integer.MAX_VALUE)
					sum+=tree[i][j];
			}
		}
		return sum;
	}

	private static int[][] minSpanningTree(int[][] weights,int N) {

		PriorityQueue<Edge1789> queue=new PriorityQueue<Edge1789>();
		for(int i=0;i<N;i++)
		{
			for(int j=i+1;j<N;j++)
			{
				if(weights[i][j]<Integer.MAX_VALUE)
				{
					queue.add(new Edge1789(i,j,weights[i][j]));
				}
			}
		}
		int tree[][]=new int[N][N];
		/**
		 * 初始化tree
		 */
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(i==j)
					tree[i][j]=0;
				else
					tree[i][j]=Integer.MAX_VALUE;
			}
		}
		
		/*Map<Integer,Set<Integer>> map=new HashMap<Integer,Set<Integer>>();
		for(int i=0;i<N;i++)
		{
			Set<Integer> set=new HashSet<Integer>();
			set.add(i);
			map.put(i, set);
		}*/
		int map[]=new int[N];
		for(int i=0;i<N;i++)
		{
			map[i]=i;
		}
		int edgeCount=0;
		while(edgeCount<N&&!queue.isEmpty())
		{
			Edge1789 edge=queue.poll();
			//Set<Integer> setU=map.get(edge.u);
			//Set<Integer> setV=map.get(edge.v);
			int setU=map[edge.u];
			int setV=map[edge.v];
			if(setU!=setV)
			{
				for(int i=0;i<N;i++)
				{
					if(map[i]==setV)
					{
						map[i]=setU;
					}
				}
				//setU.addAll(setV);
				//map.put(edge.v, setU);
				//setV=null;
				tree[edge.u][edge.v]=edge.weight;
				tree[edge.v][edge.u]=edge.weight;
				edgeCount++;
			}
			
		}
		return tree;
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

class Edge1789 implements Comparable<Edge1789>
{
	int u,v;
	int weight;
	public Edge1789(int u,int v,int w)
	{
		this.u=u;
		this.v=v;
		this.weight=w;
	}
	@Override
	public int compareTo(Edge1789 o) {

		if(this.weight>o.weight)
			return 1;
		else if(this.weight<o.weight)
			return -1;
		return 0;
	}
}

