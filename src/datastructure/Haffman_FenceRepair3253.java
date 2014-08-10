package datastructure;

import java.io.BufferedInputStream;
import java.util.*;
public class Haffman_FenceRepair3253 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(new BufferedInputStream(System.in));
		int N=in.nextInt();
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
		for(int i=0;i<N;i++)
		{
			pq.add(in.nextInt());
		}
		
		long sum=0;
		for(int i=0;i<N-1;i++)
		{
			int left=pq.poll();
			int right=pq.poll();
			int parent=left+right;
			sum+=parent;
			pq.add(parent);
		}
		System.out.println(sum);
	}

}
