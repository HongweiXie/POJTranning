package greedy;

import java.util.*;
public class Y2KAccountingBug2586 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		while(in.hasNext())
		{
			int s=in.nextInt();
			int d=in.nextInt();
			
			
			System.out.println(cal(s,d));
		}
	}

	private static String cal(int s, int d) {

		int report[]=new int[5];
		int month[]=new int[12];
		Arrays.fill(report, s);
		for(int i=4;i>=0;i--)
		{
			report[i]=-d;
			if(sum(report,0,5)<0)
				break;
		}
		for(int i=0;i<5;i++)
			month[i]=report[i];
		for(int i=5;i<month.length;i++)
		{
			month[i]=month[i-5];
		}
		int result=sum(month,0,12);
		if(result<0)
			return "Deficit";
		return result+"";
	}

	private static int sum(int[] report,int start,int end) {

		int sum=0;
		for(int i=start;i<end;i++)
			sum+=report[i];
		return sum;
	}

}
