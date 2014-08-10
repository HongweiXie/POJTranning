package graph;

import java.util.*;
public class Matching_AntennaPlacement3020 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for(int t=0;t<T;t++)
		{
			int row=in.nextInt();
			int col=in.nextInt();
			in.nextLine();
			int[][] data=new int[row][col];
			int index=1;
			for(int i=0;i<row;i++)
			{
				char carray[]=in.nextLine().toCharArray();
				for(int j=0;j<col;j++)
				{
					if(carray[j]=='o')
					{
						data[i][j]=0;
					}
					else
					{
						data[i][j]=index;
						index++;
					}
				}
			}
			index--;
			if(index==0)
			{
				System.out.println(0);
			}
			else
			{
				int graph[][]=buildGraph(data,index);
				HungarianAlgorithm ha=new HungarianAlgorithm(graph);
				System.out.println(index-ha.maxMatching()/2);
			}
			
		}
	}

	static int move[][]={{1,0},{-1,0},{0,1},{0,-1}};
	private static int[][] buildGraph(int[][] data,int index) {

		int row=data.length;
		int col=data[0].length;
		int[][] graph=new int[index][index];
		for(int i=0;i<index;i++)
			Arrays.fill(graph[i], 0);
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(data[i][j]>0)
				{
					for(int k=0;k<4;k++)
					{
						int nr=i+move[k][0];
						int nc=j+move[k][1];
						if(nr>=0&&nr<row&&nc>=0&&nc<col&&data[nr][nc]>0)
						{
							graph[data[i][j]-1][data[nr][nc]-1]=1;
						}
					}
				}
			}
		}
		return graph;
	}

	/**
	 * 匈牙利算法，递归（深度优先）搜索增光路径实现
	 * @author xianping Tao
	 *
	 */
	static class HungarianAlgorithm
	{
		int[][] graph;
		int match[];
		int state[];
		int n,m;
		int maxMatch=0;
		public HungarianAlgorithm(int[][] graph)
		{
			this.graph=graph;
			this.n=graph.length;
			this.m=graph[0].length;
			match=new int[n];
			Arrays.fill(match, -1);
			state=new int[n];
		}
		
		public int maxMatching() {
			maxMatch=0;
			for(int i=0;i<n;i++)
			{
				Arrays.fill(state, 0);
				maxMatch+=find(i);
			}
			return maxMatch;
		}

		public int find(int x) {

			for(int i=0;i<m;i++)
			{
				if(graph[x][i]==1&&state[i]==0)
				{
					state[i]=1;
					if(match[i]==-1||find(match[i])>0)
					{
						match[i]=x;
						return 1;
					}
				}
			}
			return 0;
		}
	}
	
}
