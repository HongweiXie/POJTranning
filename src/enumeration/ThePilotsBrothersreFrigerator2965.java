package enumeration;

import java.util.Arrays;
import java.util.Scanner;

public class ThePilotsBrothersreFrigerator2965 {

	static int min=16;
	static boolean[][] board;
	static int[] minOpt=new int[min];
	static int[] opts=new int[min];
	static boolean flag=false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in=new Scanner(System.in);
		board=new boolean[4][4];
		for(int i=0;i<4;i++)
		{
			String line=in.next();
			char[] c=line.toCharArray();
			for(int j=0;j<4;j++)
			{
				if(c[j]=='-')
					board[i][j]=true;
				else
					board[i][j]=false;
				//System.out.print(board[i][j] +" ");
			}
			//System.out.println();
		}
		Arrays.fill(minOpt, -1);
		Arrays.fill(opts, -1);
		for(int i=0;i<=16;i++)
		{
			min=i;
			find(0,0);
			if(flag)
				break;
		}
		
		System.out.println(min);
		for(int i=0;i<min;i++)
		{
			int row=opts[i]/4+1;
			int col=opts[i]%4+1;
			System.out.println(row+" "+col);
		}
	}

	public static void find(int n,int index)
	{
		if(flag)
		{
			return;
		}
		if(n==min)
		{
			flag=isSuccess(board);
			//if(flag)
			//	minOpt=Arrays.copyOf(opts, min);
			return;
		}
		
		for(int i=index;i<16;i++)
		{
			opt(i);
			opts[n]=i;
			find(n+1,i+1);
			opt(i);
			//opts[n]=-1;
			if(flag)
				break;
			
		}
		
	}
	
	private static void opt(int index) {

		int row=index/4;
		int col=index%4;
		board[row][col]=!board[row][col];
		for(int i=0;i<4;i++)
		{
			board[row][i]=!board[row][i];
			board[i][col]=!board[i][col];
		}
	}

	private static boolean isSuccess(boolean[][] board) {

		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(board[i][j]==false)
					return false;
			}
		}
		return true;
	}
	
}
