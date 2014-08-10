package datastructure;

import java.io.*;
import java.util.*;
public class DisjointSet_FoodChain1182 {

	
	static class Node
	{
		Node parent=this;
		int rank=0;
		int value=0;
	}
	
	static class DisjointSet
	{
		private int N;
		private Node tree[];
		public DisjointSet(int n)
		{
			this.N=n;
			tree=new Node[N];
			for(int i=0;i<N;i++)
				tree[i]=new Node();
		}
		
		public Node findSet(Node x)
		{
			if(x==x.parent)
				return x;
			Node temp=x.parent;
			x.parent=findSet(x.parent);
			
			x.value=(temp.value+x.value)%3;
			
			return x.parent;
		}
		
		public Node unionSet(Node nx,Node ny,int value)
		{
			Node rx=findSet(nx);
			Node ry=findSet(ny);
			rx.parent=ry;
			rx.value=(ny.value-nx.value+value+3)%3;
			return ry;
		}
		
		public Node balanceUnionSet(Node x,Node y,int value)
		{
			Node rx=findSet(x);
			Node ry=findSet(y);
			Node result=null;
			if(rx.rank>ry.rank)
			{
				ry.parent=rx;
				ry.value=(x.value+6-value-y.value)%3;
				result=rx;
			}
			else
			{
				rx.parent=ry;
				rx.value=(y.value-x.value+value+3)%3;
				result=ry;
			}
			if(rx.rank==ry.rank)
				ry.rank++;
			return result;
		}
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//Scanner in=new Scanner(new BufferedInputStream(System.in));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String strs[]=br.readLine().split(" ");
		int N=Integer.parseInt(strs[0]);
		int K=Integer.parseInt(strs[1]);
		
		DisjointSet ds=new DisjointSet(N+5);
		int cnt=0;
		for(int i=0;i<K;i++)
		{
			String terms[]=br.readLine().split(" ");
			int d=Integer.parseInt(terms[0])-1;
			int x=Integer.parseInt(terms[1]);
			int y=Integer.parseInt(terms[2]);
			if(x>N||y>N||(d==1&&x==y))
			{
				cnt++;
				continue;
			}
			Node nx=ds.tree[x-1];
			Node ny=ds.tree[y-1];
			Node rx=ds.findSet(nx);
			Node ry=ds.findSet(ny);
			if(rx==ry)
			{
				if((nx.value+3-ny.value)%3!=d)
				{
						cnt++;
				}
				
			}
			else
				ds.balanceUnionSet(nx,ny,d);
		}
		System.out.println(cnt);
		br.close();
	}

}
