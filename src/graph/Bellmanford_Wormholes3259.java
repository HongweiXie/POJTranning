package graph;

import java.util.*;

public class Bellmanford_Wormholes3259 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int F=in.nextInt();
		for(int f=0;f<F;f++)
		{
			int N=in.nextInt();
			int M=in.nextInt();
			int W=in.nextInt();
			Edge3259 edges[]=new Edge3259[M*2+W];
			int arc[][]=new int[N][N];
			for(int i=0;i<arc.length;i++)
			{
				Arrays.fill(arc[i], Integer.MAX_VALUE);
			}
			for(int i=0;i<M;i++)
			{
				int a=in.nextInt()-1;
				int b=in.nextInt()-1;
				int t=in.nextInt();
				arc[a][b]=Math.min(arc[a][b], t);
				arc[b][a]=arc[a][b];
				//edges[i]=new Edge(a,b,t);
			}
			for(int i=M;i<M+W;i++)
			{
				int a=in.nextInt()-1;
				int b=in.nextInt()-1;
				int t=-in.nextInt();
				//edges[i]=new Edge(a,b,t);
				arc[a][b]=Math.min(arc[a][b], t);
			}
			int index=0;
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(arc[i][j]<Integer.MAX_VALUE)
					{
						edges[index]=new Edge3259(i,j,arc[i][j]);
						index++;
					}
					
				}
			}
			if(bellmanFord(N,edges,index))
			{
				System.out.println("YES");
			}
			else
			{
				System.out.println("NO");
			}
		}
	}

	private static boolean bellmanFord(int n, Edge3259[] edges,int edgeSize) {

		int values[]=new int[n];
		Arrays.fill(values, 0);
		for(int i=0;i<n;i++)
		{
			boolean flag=true;
			for(int j=0;j<edgeSize;j++)
			{
				if(values[edges[j].b]>values[edges[j].a]+edges[j].t)
				{
					flag=false;
					values[edges[j].b]=values[edges[j].a]+edges[j].t;
				}
			}
			if(flag)
				break;
		}
		for(int j=0;j<edgeSize;j++)
		{
			if(values[edges[j].b]>values[edges[j].a]+edges[j].t)
			{
				return true;
			}
		}
		return false;
	}

}

class Edge3259
{
	int a,b;
	int t;
	public Edge3259(int a, int b, int t) {
		super();
		this.a = a;
		this.b = b;
		this.t = t;
	}
	
}