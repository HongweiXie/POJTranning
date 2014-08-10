package datastructure;

import java.util.*;
public class Hash_Squares2002 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int N;
		while((N=in.nextInt())!=0)
		{
			Node[] nodes=new Node[N];
			HashSet<Node> set=new HashSet<Node>();
			for(int i=0;i<N;i++)
			{
				nodes[i]=new Node(in.nextInt(),in.nextInt());
				set.add(nodes[i]);
			}
			
			int cnt=0;
			for(int i=0;i<N;i++)
			{
				for(int j=i+1;j<N;j++)
				{
					if(maps(set,nodes[i],nodes[j]))
					{
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	
	private static boolean maps(HashSet<Node> set,Node n1, Node n2) {
		
		int t=(n1.x-n2.x)*(n1.y-n2.y);
		if(t>0||n1.y==n2.y)
			return false;
		int deltaX=Math.abs(n1.y-n2.y);
		int deltaY=Math.abs(n1.x-n2.x);
		int x3=n1.x+deltaX;
		int y3=n1.y+deltaY;
		int x4=n2.x+deltaX;
		int y4=n2.y+deltaY;
		Node n3=new Node(x3,y3);
		Node n4=new Node(x4,y4);
		return set.contains(n3)&&set.contains(n4);
	}




	static class Node
	{
		int x,y;
		public Node(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		@Override
		public int hashCode() {
			return ((x<<9)^y)&131071;
		}
		@Override
		public boolean equals(Object obj) {

			if(obj instanceof Node)
			{
				Node n=(Node)obj;
				return n.x==x&&n.y==y;
			}
			return false;
		}
		
	
		public String toString()
		{
			return x+" "+y;
		}
	}
	

}
