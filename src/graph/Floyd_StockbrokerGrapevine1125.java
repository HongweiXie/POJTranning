package graph;

import java.util.*;

public class Floyd_StockbrokerGrapevine1125 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		while(N!=0)
		{
			int arc[][]=new int[N][N];
			for(int i=0;i<N;i++)
			{	
				Arrays.fill(arc[i], Integer.MAX_VALUE);
				arc[i][i]=0;
			
			}
			for(int i=0;i<N;i++)
			{
				int num=in.nextInt();
				for(int j=0;j<num;j++)
				{
					int col=in.nextInt()-1;
					int time=in.nextInt();
					arc[i][col]=time;
				}
			}
			
			int[][] dis=floyd(arc);
			int min=Integer.MAX_VALUE;
			int index=0;
			for(int i=0;i<N;i++)
			{
				int max=maxDis(dis[i]);
				if(min>max)
				{
					index=i+1;
					min=max;
				}
			}
			if(min==Integer.MAX_VALUE)
			{
				System.out.println("disjoint");
			}
			else
			{
				System.out.println(index+" "+min);
			}
			N=in.nextInt();
		}
	}

	private static int maxDis(int[] d) {

		int max=0;
		for(int i=0;i<d.length;i++)
		{
			if(d[i]>max)
			{
				max=d[i];
			}
		}
		return max;
	}

	private static int[][] floyd(int[][] arc) {

		int n = arc.length;
		int[][] dis = new int[n][n];
		for (int i = 0; i < n; i++)
			dis[i] = Arrays.copyOf(arc[i], n);
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (dis[i][k] < Integer.MAX_VALUE
							&& dis[k][j] < Integer.MAX_VALUE) {
						dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
					}

				}
			}
		}
		return dis;
	}
}
