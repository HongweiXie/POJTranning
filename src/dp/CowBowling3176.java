package dp;

import java.io.BufferedInputStream;
import java.util.*;
public class CowBowling3176 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(new BufferedInputStream(System.in));
		int N=in.nextInt();
		int c[][]=new int[N][N];
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<=i;j++)
			{
				c[i][j]=in.nextInt();
			}
			
		}
		int f[][]=new int[N][N];
		for(int i=0;i<N;i++)
		{
			f[N-1][i]=c[N-1][i];
		}
		for(int i=N-2;i>=0;i--)
		{
			for(int j=0;j<=i;j++)
			{
				f[i][j]=Math.max(f[i+1][j], f[i+1][j+1])+c[i][j];
			}
		}
		System.out.println(f[0][0]);
	}

}
