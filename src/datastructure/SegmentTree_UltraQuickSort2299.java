package datastructure;

import java.io.BufferedInputStream;
import java.util.*;
public class SegmentTree_UltraQuickSort2299 {

	static final int N=550000;
	static class Node implements Comparable<Node>
	{
		int val;
		int pos;
		@Override
		public int compareTo(Node o) {
			return val-o.val;
		}
	}
	
	static class Segment
	{
		int left,right,val;
		int mid()
		{
			return (left+right)/2;
		}
	}
	
	static class SegmentTree
	{
		Segment segs[];
		public SegmentTree()
		{
			segs=new Segment[N*2+1];
			for(int i=0;i<N*2+1;i++)
				segs[i]=new Segment();
		}
		
		public void build(int left,int right,int index)
		{
			segs[index].left=left;
			segs[index].right=right;
			segs[index].val=0;
			if(left==right)
				return;
			int mid=segs[index].mid();
			this.build(left, mid, index<<1);
			this.build(mid+1, right, (index<<1)+1);
		}
		
		public void insert(int value,int left,int right,int index)
		{
			if(segs[index].left==value&&segs[index].right==value)
			{
				segs[index].val++;
				return ;
			}
			if(value<=segs[index].mid())
			{
				insert(value,left,segs[index].mid(),index<<1);
			}
			else
			{
				insert(value,segs[index].mid()+1,right,(index<<1)+1);
			}
			segs[index].val=segs[index<<1].val+segs[(index<<1)+1].val;
		}
		
		public int query(int left,int right,int index)
		{
			//System.out.println(index);
			if(segs[index].left==left&&segs[index].right==right)
				return segs[index].val;
			int mid=segs[index].mid();
			if(right<=mid)
				return query(left,right,index<<1);
			else if(left>mid)
				return query(left,right,(index<<1)+1);
			else
				return query(left,mid,index<<1)+query(mid+1,right,(index<<1)+1);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(new BufferedInputStream(System.in));
		
		
		
		int n;
		while((n=in.nextInt())!=0)
		{
		
			Node array[]=new Node[n];
			int seqData[]=new int[n];
			for(int i=0;i<n;i++)
			{
				array[i]=new Node();
				array[i].val=in.nextInt();
				array[i].pos=i;
			}
			Arrays.sort(array,0,n);
			for(int i=0;i<n;i++)
				seqData[array[i].pos]=i+1;
			
			SegmentTree st=new SegmentTree();
			st.build(1, n, 1);
			long sum=0;
			for(int i=0;i<n;i++)
			{
				sum+=st.query(seqData[i], n, 1);
				st.insert(seqData[i], 1, n, 1);
			}
			System.out.println(sum);
		}
	}

}
