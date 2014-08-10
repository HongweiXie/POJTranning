package graph;

import java.util.*;
public class SubwayTreeSstems1635 {


	static final int Hn=11000;
	static int h[]=new int[Hn];
	static Random rand=new Random(System.currentTimeMillis());
	static int m=1000000007;
	static int index=0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		run();
	}

	private static void init() {

		for(int i=0;i<Hn;i++)
			h[i]=(rand.nextInt()%m);
	}
	
	public static void run()
	{
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		init();
		for(int t=0;t<T;t++)
		{
			String s1=in.next();
			Node1635 tree1=createTree(s1);
			String s2=in.next();
			Node1635 tree2=createTree(s2);
			/*System.out.println(tree1.children.size()+" "+tree2.children.size());
			displayTree(tree1);
			System.out.println();
			displayTree(tree2);*/
			
			int a=hash(tree1,1);
			int b=hash(tree2,1);
			//System.out.println(a+" "+b);
			if(a==b)
			{
				System.out.println("same");
			}
			else
			{
				System.out.println("different");
			}
		}
	}
	
	public static int hash(Node1635 tree,int j)
	{
		int sum=h[j+5000];//j是树的高度
		for(Node1635 n:tree.children)
			sum=(sum+h[j]*hash(n,j+1))%m;//把子树的哈希值加到父节点上去
		return (sum*sum)%m;
		
	}

	private static Node1635 createTree(String s) {

		char[] seq=s.toCharArray();
		Node1635 root=new Node1635(0);
		Node1635 p=root;
		int index=1;
		for(int i=0;i<seq.length;i++)
		{
			if(seq[i]=='0')
			{
				Node1635 node =new Node1635(index++);
				connect(p,node);
				p=node;
			}
			else if(seq[i]=='1')
			{
				p=p.parent;
			}
		}
		//if(p==root)
		//	System.out.println("create success!");
		return root;
	}

	private static void connect(Node1635 p, Node1635 node) {

		node.parent=p;
		p.children.add(node);
	}
	
	public static void displayTree(Node1635 tree)
	{
		System.out.println(tree);
		for(Node1635 ch:tree.children)
			displayTree(ch);
	}

}

class Node1635
{
	int id;
	Node1635 parent=null;
	List<Node1635> children=new ArrayList<Node1635>();
	public Node1635(int n)
	{
		id=n;
	}
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append(id).append(": ");
		for(Node1635 n:children)
			sb.append(n.id).append(" ");
		return sb.toString();
	}
}
