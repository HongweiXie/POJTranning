package datastructure;

import java.util.*;
import java.io.*;

public class TrieTree_ColoredSticks2513 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		int N=250000*2+10;
		DisjointSet ds=new DisjointSet(N);
		TrieTree trie=new TrieTree();
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int cnt=0;
		while((s=in.readLine())!=null&&s.length()>2)
		{
			String strs[]=s.split(" ");
			int x=trie.insert(strs[0]);
			int y=trie.insert(strs[1]);
			ds.balanceUnionSet(ds.tree[x], ds.tree[y]);
		}
		//System.out.println(trie.idCnt);
		if(trie.oddDegreeNum==0||trie.oddDegreeNum==2)
		{
			Node k=ds.findSet(ds.tree[1]);
			for(int i=2;i<=trie.idCnt;i++)
			{
				if(ds.findSet(ds.tree[i])!=k)
				{
					System.out.println("Impossible");
					return;
				}
			}
			System.out.println("Possible");
		}
		else
		{
			System.out.println("Impossible");
		}
		
	}
	
	//Disjoint Set
	static class Node
	{
		Node parent=this;
		int rank=0;
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
			
			
			return x.parent;
		}
		
		public Node unionSet(Node nx,Node ny)
		{
			Node rx=findSet(nx);
			Node ry=findSet(ny);
			rx.parent=ry;
			return ry;
		}
		
		public Node balanceUnionSet(Node x,Node y)
		{
			Node rx=findSet(x);
			Node ry=findSet(y);
			Node result=null;
			if(rx.rank>ry.rank)
			{
				ry.parent=rx;
				result=rx;
			}
			else
			{
				rx.parent=ry;
				result=ry;
			}
			if(rx.rank==ry.rank)
				ry.rank++;
			return result;
		}
		
	}
	
	//Trie Tree
	static final int N=26;
	static final int HEIGHT=10;
	static class TrieNode
	{
		int id=-1;
		int wordNum=0;
		int prefixNum=0;
		TrieNode[] children=new TrieNode[N];
	}
	
	static class TrieTree
	{
		private TrieNode root;
		private int idCnt=0;
		private int oddDegreeNum=0;
		public TrieTree()
		{
			root=new TrieNode();
		}
		
		public int insert(String word)
		{
			
			if(word.length()>HEIGHT)
				return -1;
			char c[]=word.toCharArray();
			TrieNode tn=root;
			tn.wordNum++;
			tn.prefixNum++;
			for(int i=0;i<c.length;i++)
			{
				int p=c[i]-'a';
				if(tn.children[p]==null)
				{
					tn.children[p]=new TrieNode();
				}
				tn=tn.children[p];
				tn.prefixNum++;
				if(i==c.length-1)
				{
					if(tn.wordNum==0)
					{
						idCnt++;
						tn.id=idCnt;
					}
					tn.wordNum++;
					if(tn.wordNum%2!=0)
						oddDegreeNum++;
					else
						oddDegreeNum--;
				}
				
			}
			return tn.id;
		}
		
		/*public boolean delete(String word)
		{
			if(this.searchWord(word)==0)
				return false;
			char c[]=word.toCharArray();
			TrieNode tn=root;
			tn.wordNum--;
			tn.prefixNum--;
			for(int i=0;i<c.length-1;i++)
			{
				int p=c[i]-'a';
				tn=tn.children[p];
				tn.prefixNum--;
				if(i==c.length-1)
				{
					tn.wordNum--;
					
				}
			}
			return true;
		}*/
		
		public int searchWord(String word)
		{
		
			char c[]=word.toCharArray();
			
			TrieNode tn=root;
			for(int i=0;i<c.length-1;i++)
			{
				int p=c[i]-'a';
				tn=tn.children[p];
				if(tn==null)
					return 0;
			}
			return tn.wordNum;
		}
	}
	

}
