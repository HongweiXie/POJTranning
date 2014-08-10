package graph;

import java.util.*;

public class Maxflow_EK_PowerNetwork1459 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in).useDelimiter("[\\W&&[^\\^Z]]+");
		while(in.hasNext())
		{
			int n=in.nextInt();
			int np=in.nextInt();
			int nc=in.nextInt();
			int m=in.nextInt();
			int graph[][]=new int[n+2][n+2];
			for(int i=0;i<graph.length;i++)
			{
				Arrays.fill(graph[i], 0);
			}
			for(int i=0;i<m;i++)
			{
				
				int s=in.nextInt();
				int t=in.nextInt();
				int v=in.nextInt();
				graph[s][t]=v;
			}
			for(int i=0;i<np;i++)
			{
				int u=in.nextInt();
				int v=in.nextInt();
				graph[n][u]=v;
			}
			
			for(int i=0;i<nc;i++)
			{
				int u=in.nextInt();
				int v=in.nextInt();
				graph[u][n+1]=v;
			}
			/*for(int i=0;i<n+2;i++)
			{
				for(int j=0;j<n+2;j++)
				{
					System.out.print(graph[i][j]+" ");
				}
				System.out.println();
			}*/
			int v=new FordFulkerson(graph.length).edmondsKarpMaxFlow(graph, n, n+1);
			System.out.println(v);
		}
	}
	
	public static class FordFulkerson
	{
		private int residualNetwork[][]=null;
		private int flowNetwork[][]=null;
		
		public final int N;
		int parent[];
		public FordFulkerson(int N)
		{
			this.N=N;
			parent=new int[N];
		}
		/**
		 * 实现FordFulkerson方法的一种算法——edmondsKarp算法
		 * @param graph
		 * @param s
		 * @param t
		 * @return
		 */
		public int edmondsKarpMaxFlow(int graph[][],int s,int t)
		{
			int length=graph.length;
			int f[][]=new int[length][length];
			for(int i=0;i<length;i++)
			{
				Arrays.fill(f[i], 0);
			}
			int r[][]=residualNetwork(graph,f);
			int result=augmentPath(r,s,t);
			
			int sum=0;
			
			while(result!=-1)
			{
				int cur=t;
				while(cur!=s)
				{
					f[parent[cur]][cur]+=result;
					f[cur][parent[cur]]=-f[parent[cur]][cur];
					r[parent[cur]][cur]-=result;
					r[cur][parent[cur]]+=result;
					cur=parent[cur];
				}
				
				sum+=result;
				result=augmentPath(r,s,t);
			}
			
			residualNetwork=r;
			flowNetwork=f;
			
			return sum;
		}

		/**
		 * deepCopy
		 * @param c
		 * @param f
		 * @return
		 */
		private int[][] residualNetwork(int c[][],int f[][]) {
			int length=c.length;
			int r[][]=new int[length][length];
			for(int i=0;i<length;i++)
			{
				for(int j=0;j<length;j++)
				{
					r[i][j]=c[i][j]-f[i][j];
				}
			}
			
			return r;
		}

		/**
		 * 广度优先遍历，寻找增光路径，也是最短增广路径
		 * @param graph
		 * @param s
		 * @param t
		 * @return
		 */
		public int augmentPath(int graph[][],int s,int t)
		{
			
			int maxflow=Integer.MAX_VALUE;
			Arrays.fill(parent, -1);
			Queue<Integer> queue=new LinkedList<Integer>();
			queue.add(s);
			parent[s]=s;

			while(!queue.isEmpty())
			{
				int p=queue.poll();
				if(p==t)
					break;
				for(int i=0;i<graph.length;i++)
				{
					if(i!=p&&parent[i]==-1&&graph[p][i]>0)
					{
						if(maxflow>graph[p][i])
							maxflow=graph[p][i];
						//flow[i]=Math.min(flow[p], graph[p][i]);
						parent[i]=p;
						queue.add(i);
					}
				}
			}
			if(parent[t]==-1)
				return -1;
			return  maxflow;
			
		}

		public int[][] getResidualNetwork() {
			return residualNetwork;
		}

		public int[][] getFlowNetwork() {
			return flowNetwork;
		}
	}

}
