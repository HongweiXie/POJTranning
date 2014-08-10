package graph;

import java.util.*;
public class Dijkstra_Frogger2253 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int index=0;
		while(n!=0)
		{
			int pos[][]=new int[n][2];
			double arc[][]=new double[n][n];
			for(int i=0;i<n;i++)
			{
				pos[i][0]=in.nextInt();
				pos[i][1]=in.nextInt();
			}
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{
					arc[i][j]=distance(pos[i],pos[j]);
				}
			}
			double dis[]=new double[n];
			Arrays.fill(dis, Integer.MAX_VALUE);
			dis[0]=0;
			double min=dijkstra(dis,arc,n);
			System.out.format("Scenario #"+(++index)+"\nFrog Distance = %.3f\n\n", min);
			n=in.nextInt();
		}
	}

	private static double dijkstra(double[] dis, double[][] arc, int n) {
		Set<Integer> set=new HashSet<Integer>();
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<n;i++)
			list.add(i);
		while(list.size()>0)
		{
			int u=extractMin(list,dis);
			
			set.add(u);
			for(int i=0;i<list.size();i++)
			{
				int v=list.get(i);
				//relax(dis,arc,u,list.get(i));
				if(dis[v]>Math.max(dis[u],arc[u][v]))
					dis[v]=Math.max(dis[u],arc[u][v]);
			}
		}
		return dis[1];
	}

	private static void relax(double[] dis, double arc[][],int u, int v) {

		if(dis[v]>dis[u]+arc[u][v])
		{
			dis[v]=dis[u]+arc[u][v];
			System.out.println(u+" "+v+" "+dis[v]);
		}
	}

	private static int extractMin(List<Integer> list, double[] dis) {

		int min=list.get(0);
		int index=0;
		for(int i=0;i<list.size();i++)
		{
			int v=list.get(i);
					
			if(dis[v]<dis[min])
			{
				min=v;
				index=i;
			}
		}
		return list.remove(index);
	}

	private static double distance(int[] p1, int[] p2) {

		
		return Math.sqrt((p1[0]-p2[0])*(p1[0]-p2[0])+(p1[1]-p2[1])*(p1[1]-p2[1]));
	}

}


