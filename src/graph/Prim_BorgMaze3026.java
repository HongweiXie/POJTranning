package graph;

import java.util.*;
public class Prim_BorgMaze3026 {

	static int[][] matrix=new int[110][110];
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		for(int t=0;t<T;t++)
		{
			int col=in.nextInt();
			int row=in.nextInt();
			int map[][]=new int[row][col];
			in.nextLine();
			int n=0;
			for(int i=0;i<row;i++)
			{
				String str=in.nextLine();
				//System.out.println(str);
				char c[]=str.toCharArray();
				for(int j=0;j<col;j++)
				{
					if(c[j]=='#')
						map[i][j]=-1;
					else if(c[j]==' ')
						map[i][j]=0;
					else
						map[i][j]=(++n);
				}
			}
			
			matrix=buildAdjacentMatrix(map,row,col);
			
			/*for(int i=0;i<matrix.length;i++)
			{
				for(int j=0;j<matrix[0].length;j++)
				{
					System.out.print(matrix[i][j]+" ");
				}
				System.out.println();
			}*/
			System.out.println(sum(minSpanningTree(matrix,n),matrix));
		}
	}

	private static int sum(int[] parent,int weights[][]) {

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
	
	private static int[][] buildAdjacentMatrix(int[][] map,int row,int col) {

		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(map[i][j]>0)
				{
					bfs(map,matrix,i,j);
				}
			}
		}
		return matrix;
	}

	static int move[][]={{1,0},{-1,0},{0,1},{0,-1}};
	private static void bfs(int[][] map, int[][] matrix, int x, int y) {

		int start=map[x][y]-1;
		Point p=new Point(x,y);
		p.step=0;
		Queue<Point> queue=new LinkedList<Point>();
		queue.add(p);

		int[][] visit=new int[map.length][map[0].length];
		for(int i=0;i<visit.length;i++)
			Arrays.fill(visit[i], 0);
		visit[p.x][p.y]=1;
		
		while(!queue.isEmpty())
		{
			Point cur=queue.poll();
			int id=map[cur.x][cur.y]-1;
			if(id>=0)
				matrix[start][id]=cur.step;
			Point next;
			for(int i=0;i<4;i++)
			{
				int nx=cur.x+move[i][0];
				int ny=cur.y+move[i][1];
				if(inMap(nx,ny,map.length,map[0].length)&&visit[nx][ny]==0&&map[nx][ny]>=0)
				{
					next=new Point(nx,ny);
					next.step=cur.step+1;
					queue.add(next);
					visit[nx][ny]=1;
				}
			}
		}
		
	}

	private static boolean inMap(int nx, int ny, int x, int y) {

		if(nx>=0&&nx<x&&ny>=0&&ny<y)
			return true;
		return false;
	}

}

class Point
{
	int x,y;
	int step=0;
	public Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}
