package graph;

import java.util.*;
public class Dijkstra_ExpensiveDowry1062 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int M=in.nextInt();
		int N=in.nextInt();
		Item[] items=new Item[N];
		int arcs[][]=new int[N][N];
		List<String> relation=new ArrayList<String>();
		int price[]=new int[N];
		for(int n=0;n<N;n++)
		{
			int P=in.nextInt();
			int L=in.nextInt();
			items[n]=new Item(n+1,P,L);
			price[n]=P;
			
			int X=in.nextInt();
			in.nextLine();
			for(int i=0;i<X;i++)
			{
				relation.add((n+1)+" "+in.nextLine());
			}
		}
		int min=Integer.MAX_VALUE;
		for(int i=0;i<=M;i++)
		{
			for(int j=0;j<N;j++)
			{
				Arrays.fill(arcs[j], Integer.MAX_VALUE);
				items[j].price=price[j];
			}
			buildEdges(items,relation,arcs,items[0].level-M+i,items[0].level+i);
			dijkstra(items,N,arcs);
			if(items[0].price<min)
				min=items[0].price;
		}
		
		System.out.println(min);
	}

	private static void dijkstra(Item[] items,int n,int arcs[][]) {
		
		Set<Item> set=new HashSet<Item>();
		List<Item> list=new LinkedList<Item>();
		for(int i=0;i<n;i++)
			list.add(items[i]);
		//set.add(items[0]);
		
		
		while(list.size()>0)
		{
			Item u=extractMin(list);
			
			set.add(u);
			for(Item v:u.sub)
			{
				//System.out.println(u.id+"-"+v.id);
				relax(u,v,arcs);
			}
		}
		
		
		
	}

	private static void relax(Item u, Item v,int arcs[][]) {

		//System.out.println(u.id+" "+v.id+" "+arcs[u.id-1][v.id-1]);
		if(arcs[u.id-1][v.id-1]<Integer.MAX_VALUE&&v.price>u.price+arcs[u.id-1][v.id-1])
		{
			v.price=u.price+arcs[u.id-1][v.id-1];
			//System.out.println(v.id+" "+v.price);
		}
	}

	private static Item extractMin(List<Item> list) {

		int min=0;
		for(int i=1;i<list.size();i++)
		{
			if(list.get(i).compareTo(list.get(min))<0)
			{
				min=i;
			}
		}
		return list.remove(min);
	}

	private static void buildEdges(Item[] items, List<String> relation,int arcs[][],int le,int lh) {

		for(String str:relation)
		{
			//System.out.println(str);
			String vstrs[]=str.split(" ");
			int f=Integer.parseInt(vstrs[0])-1;
			int t=Integer.parseInt(vstrs[1])-1;
			int v=Integer.parseInt(vstrs[2]);
			if(items[f].level>=le&&items[t].level<=lh&&items[t].level>=le&&items[t].level<=lh)
			{
				arcs[t][f]=Math.min(arcs[t][f], v);
				items[t].sub.add(items[f]);
			}
			
		}
	}

}

class Item implements Comparable<Item>
{
	int id;
	int price;
	int  level;
	List<Item> sub=new ArrayList<Item>();
	public Item(int id,int price,int level)
	{
		this.id=id;
		this.price=price;
		this.level=level;
	}
	@Override
	public int compareTo(Item o) {

		if(this.price>o.price)
			return 1;
		else if(this.price<o.price)
			return -1;
		return 0;
	}
}
