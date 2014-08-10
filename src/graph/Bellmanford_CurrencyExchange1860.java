package graph;

import java.util.*;
/**
 * bell-ford ×î¶ÌÂ·¾¶
 * @author xianping Tao
 *
 */
public class Bellmanford_CurrencyExchange1860 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		int M=in.nextInt();
		int S=in.nextInt();
		double V=in.nextDouble();
		double value[]=new double[N];
		
		
		Edge1860 edges[]=new Edge1860[M*2];
		for(int i=0;i<M;i++)
		{
			int A=in.nextInt()-1;
			int B=in.nextInt()-1;
			edges[2*i]=new Edge1860(A,B);
			edges[2*i+1]=new Edge1860(B,A);
			edges[2*i].rate=in.nextDouble();
			edges[2*i].c=in.nextDouble();
			edges[2*i+1].rate=in.nextDouble();
			edges[2*i+1].c=in.nextDouble();
			
		}
		Arrays.fill(value, 0);
		value[S-1]=V;
		int cur=S-1;
		boolean flag=bellmanFord(cur,N,value,edges);
		if(flag)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}
	

	private static boolean bellmanFord(int cur, int n, double[] value, Edge1860[] edges) {

		
		for(int i=1;i<n;i++ )
		{
			boolean flag=false;
			for(int j=0;j<edges.length;j++)
			{
				if(value[edges[j].b]<exchange(value[edges[j].a],edges[j].c,edges[j].rate))
				{
					value[edges[j].b]=exchange(value[edges[j].a],edges[j].c,edges[j].rate);
					flag=true;
				}
			}
			if(flag==false)
				break;
		}
		
		for(int j=0;j<edges.length;j++)
		{
			if(value[edges[j].b]<exchange(value[edges[j].a],edges[j].c,edges[j].rate))
			{
				return true;
			}
		}
		
		return false;
	}


	public static double exchange(double v,double com,double rate)
	{
		
		return (v-com)*rate;
	}

}

class Edge1860
{
	public Edge1860(int a2, int b2) {

		a=a2;
		b=b2;
	}

	int a,b;
	double c,rate;
}