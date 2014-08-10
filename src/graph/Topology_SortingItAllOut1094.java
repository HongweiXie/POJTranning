package graph;

import java.util.*;
public class Topology_SortingItAllOut1094 {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		
		while(true)
		{
			int n=in.nextInt();
			int m=in.nextInt();	
			if(n==0&&m==0)
				break;
			in.nextLine();

			int relations[][]=new int[m][2];
			int edges[][]=new int[n][n];
			Node1049 nodes[]=new Node1049[n];
			
			for(int i=0;i<n;i++)
			{
				nodes[i]=new Node1049((char)(i+'A'));
			}
			
			for(int i=0;i<m;i++)
			{
				char c[]=in.nextLine().toCharArray();
				relations[i][0]=c[0]-'A';
				relations[i][1]=c[2]-'A';
				
			}
			
			char result[]=new char[n];
			int flag;
			for(int con=1;con<=m;con++)
			{
				for(int i=0;i<n;i++)
				{
					nodes[i].in=0;
					nodes[i].out=0;
					Arrays.fill(edges[i], 0);
				}
				for(int i=0;i<con;i++)
				{
					nodes[relations[i][0]].out++;
					nodes[relations[i][1]].in++;
					edges[relations[i][0]][relations[i][1]]=1;
				}
				
				flag=topologySort(nodes,edges,result);
				if(flag==0)
				{
					System.out.println("Inconsistency found after "+con+" relations. ");
					break;
				}
				else if(flag==1)
				{
					StringBuilder sb=new StringBuilder();
					for(int i=0;i<n;i++)
						sb.append(result[i]);
					System.out.println("Sorted sequence determined after "+con+" relations: "+sb.toString()+". ");
					break;
				}
				else
				{
					if(con==m)
					{
						System.out.println("Sorted sequence cannot be determined. ");
					}
				}
					
			}
			
		}
	}

	private static int topologySort(Node1049[] nodes, int[][] edges,char result[]) {
		int flag=1;
		int n=nodes.length;
		List<Node1049> list=new LinkedList<Node1049>();
		for(int i=0;i<n;i++)
		{
			if(nodes[i].in>0||nodes[i].out>0)
				list.add(nodes[i]);
		}
		if(list.size()<n)
			flag=-1;
		int index=0;
		while(list.size()>0)
		{
			int num=0;
			int top=-1;
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).in==0)
				{
					num++;
					top=i;
				}
			}
			if(num>1)
				flag=-1;
			if(top==-1)
			{
				flag=0;
				break;
			}
			Node1049 node=list.remove(top);
			result[index++]=node.id;
			for(int i=0;i<n;i++)
			{
				//edges[node.id-'A'][i]=0;
				if(edges[node.id-'A'][i]==1)
				{
					nodes[i].in--;
				}
			}
			
		}
		return flag;
	}

	/*private static boolean isTopologySorted(Node[] nodes) {

		Set<Integer> set=new HashSet<Integer>();
		for(int i=0;i<nodes.length;i++)
		{
			if(set.contains(nodes[i].value))
			{
				return false;
	
			}
			set.add(nodes[i].value);
		}
		return true;
	}*/


}

class Node1049 implements Comparable<Node1049>
{
	public Node1049(char c)
	{
		id=c;
	}
	//int in=0;
	//int out=0;
	char id;
	int in=0;
	int out=0;
	@Override
	public int compareTo(Node1049 o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}


