package greedy;

import java.util.*;
public class PowerofCryptography2109 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		while(in.hasNext())
		{
			int n=in.nextInt();
			String pStr=in.next();
			double p=Double.parseDouble(pStr);
			System.out.println(getRoot(n,p,pStr));
		}
	}

	private static long getRoot(int n, double p,String strP) {

		if(n==1)
			return (long) p;
		//System.out.println(strP);
		int len=strP.length();
		int low=(int) Math.pow(10,(len-1)/n);
		int up=low*10;
		
		return find(n,p,low,up);
	}

	private static long find(int n, double p, int low, int up) {

		
		while(low<=up)
		{
			
			int mid=(low+up)/2;
			//System.out.println(low +" "+up +" "+mid);
			double temp=Math.pow(mid, n);
			if(temp==p)
				return mid;
			else if(temp<p)
			{
				low=mid+1;
			}
			else 
			{
				up=mid-1;
			}
						
		}
		return -1;
	}

}
