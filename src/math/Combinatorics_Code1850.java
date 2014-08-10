package math;

import java.util.*;

public class Combinatorics_Code1850 {

	static int c[][] = new int[30][30];

	public static void Init() {
		for (int i = 0; i < 27; i++) {
			c[i][0] = c[i][i] = 1;
			for (int j = 1; j < i; j++)
				c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		Init();
		/*while(in.hasNext())
		{*/
			String word=in.next();
			int n=word.length();
			int base='a'-1;
			int code=0;
			
			if(n==1)
			{
				System.out.println(word.charAt(0)-base);
				return;
			}
			
			for(int i=1;i<n;i++)
			{
				if(word.charAt(i)<=word.charAt(i-1))
				{
					System.out.println(0);
					return;
				}
			}
			
			for(int i=1;i<n;i++)
			{
				code+=c[26][i];
			}
			int start=0;
			for(int i=0;i<n;i++)
			{
				int low=word.charAt(i)-base-1;
				for(int j=start;j<low;j++)
				{
					code+=c[26-j-1][n-i-1];
				}
				start=low+1;
			}
			//System.out.println(code);
			//code+=(word.charAt(n-1)-word.charAt(n-2));
			System.out.println(code+1);
		}
		
	//}
}
