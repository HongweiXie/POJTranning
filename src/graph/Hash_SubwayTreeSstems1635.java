package graph;

import java.util.*;
public class Hash_SubwayTreeSstems1635 {


	static final int Hn=11000;
	static int h[]=new int[Hn];
	static Random rand=new Random(47);
	static int m=19001;
	//static int N=19901;
	static int index=0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		run();
	}

	public static void run()
	{
		Scanner in=new Scanner(System.in);
		int T=in.nextInt();
		init();
		for(int t=0;t<T;t++)
		{
			String s1=in.next();
			index=0;
			int h1=hash(s1.toCharArray(),1);
			String s2=in.next();
			index=0;
			int h2=hash(s2.toCharArray(),1);
			/*System.out.println(tree1.children.size()+" "+tree2.children.size());
			displayTree(tree1);
			System.out.println();
			displayTree(tree2);*/
			System.out.println(h1+" "+h2);
			if(h1==h2)
			{
				System.out.println("same");
			}
			else
			{
				System.out.println("different");
			}
		}
	}

	private static void init() {

		for(int i=0;i<Hn;i++)
			h[i]=(rand.nextInt()%m);
	}

	private static int hash(char[] charArray,int j) {

		int sum=h[j+5000];//j 是节点的高度
		while(index<charArray.length && charArray[index++]=='0')
		{
				sum=(sum+h[j]*hash(charArray,j+1))%m;//把子节点的哈希值加到父节点上
		}
		return (sum*sum)%m;
	}
	

}

