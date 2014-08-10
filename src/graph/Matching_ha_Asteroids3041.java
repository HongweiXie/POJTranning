package graph;

import java.util.*;
public class Matching_ha_Asteroids3041 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		int K=in.nextInt();
		int graph[][]=new int[N][N];
		for(int i=0;i<N;i++)
			Arrays.fill(graph[i], 0);
		for(int i=0;i<K;i++)
		{
			int r=in.nextInt()-1;
			int c=in.nextInt()-1;
			graph[r][c]=1;
		}
		HungarianAlgorithm ha=new HungarianAlgorithm(graph);
		System.out.println(ha.maxMatching());
		
	}

	

}
/**
 * 匈牙利算法，递归（深度优先）搜索增光路径实现
 * @author xianping Tao
 *
 */
class HungarianAlgorithm
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
