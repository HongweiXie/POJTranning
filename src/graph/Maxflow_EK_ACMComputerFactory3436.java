package graph;

import java.util.*;
public class Maxflow_EK_ACMComputerFactory3436 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int T=0;
		while(in.hasNext())
		{
			//in.nextLine();
			int P=in.nextInt();
			int N=in.nextInt();
			int performance[]=new int[N];
			int input[][]=new int[N][P];
			int output[][]=new int[N][P];
			int M=2*N+2;
			int map[][]=new int[M][M];
			for(int i=0;i<map.length;i++)
			{
				Arrays.fill(map[i], 0);
			}
			
			for(int n=0;n<N;n++)
			{
				performance[n]=in.nextInt();
				for(int i=0;i<P;i++)
				{
					input[n][i]=in.nextInt();
				}
				
				for(int i=0;i<P;i++)
				{
					output[n][i]=in.nextInt();
				}
			}
			/**
			 * 拆点,i-> i'=2*i和i''=2*i+1
			 */
			for(int i=0;i<N;i++)
			{
				map[2*i][2*i+1]=performance[i];
				map[2*i+1][2*i]=performance[i];
			}
			
			/**
			 * 建图,i->j 连接成 i''->j'
			 */
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(i!=j)
					{
						int c=0;
						for(int k=0;k<P;k++)
						{
							if(output[i][k]==input[j][k]||input[j][k]==2)
							{
								c++;
							}
						}
						if(c==P)
						{
							map[2*i+1][2*j]=Integer.MAX_VALUE;
						}
					}
				}
			}
			/**
			 * 连接源点
			 */
			for(int i=0;i<N;i++)
			{
				int c=0;
				for(int k=0;k<P;k++)
				{
					if(input[i][k]!=1)
						c++;
				}
				if(c==P)
				{
					map[M-2][2*i]=Integer.MAX_VALUE;
				}
			}
			/**
			 * 连接汇点
			 */
			for(int i=0;i<N;i++)
			{
				int c=0;
				for(int k=0;k<P;k++)
				{
					if(output[i][k]==1)
						c++;
				}
				if(c==P)
				{
					map[2*i+1][M-1]=Integer.MAX_VALUE;
				}
			}
			
			FordFulkerson ford=new FordFulkerson(M);
			int maxflow=ford.edmondsKarpMaxFlow(map, M-2, M-1);
			int flowNetwork[][]=ford.getFlowNetwork();
			int connections=0;
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<M-2;i++)
			{
				for(int j=0;j<M-2;j++)
				{
					if(flowNetwork[i][j]>0)
					{
						if(i/2!=j/2)
						{	
							sb.append(i/2+1).append(" ").append(j/2+1).append(" ").append(flowNetwork[i][j]).append("\n");
							connections++;
						}
					}
				}
			}
			//System.out.println("Sample output "+(++T));
			System.out.println(maxflow +" "+connections);
			System.out.println(sb.toString());
			//in.nextLine();
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
