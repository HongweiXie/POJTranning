package datastructure;

import java.util.*;
public class Median_WhosintheMiddle2388 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		int array[]=new int[N];
		for(int n=0;n<N;n++)
		{
			array[n]=in.nextInt();
		}
		System.out.println(median(array,0,N,N/2));
	}
	
	public static int median(int array[],int s,int e,int k)
	{
		if(s==e-1)
			return array[s];
		int i = partition(array, s, e);
		
		if(i==k)
			return array[k];
		else if(i<k)
			return median(array,i+1,e,k);
		else
			return median(array,s,i,k);
		
	}

	public static int partition(int[] array, int s, int e) {
		int x=array[e-1];
		int i=s-1;
		for(int j=s;j<e-1;j++)
		{
			if(array[j]<=x)
				swap(array,++i,j);
		}
		swap(array,++i,e-1);
		return i;
	}

	private static void swap(int[] array, int j, int i) {

		if(i!=j)
		{
			int temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
	}

}
