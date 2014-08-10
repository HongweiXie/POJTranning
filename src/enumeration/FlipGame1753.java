package enumeration;

import java.util.*;
public class FlipGame1753 {

	static int min=17;
	static boolean[][] board;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		board=new boolean[4][4];
		for(int i=0;i<4;i++)
		{
			String line=in.next();
			char[] c=line.toCharArray();
			for(int j=0;j<4;j++)
			{
				if(c[j]=='b')
					board[i][j]=false;
				else
					board[i][j]=true;
				//System.out.print(board[i][j] +" ");
			}
			//System.out.println();
		}
		
		find(0,0);
		if(min>16)
			System.out.println("Impossible");
		else
			System.out.println(min);
	}
	
	public static void find(int n,int index)
	{
		if(n==16)
		{
			if(isSuccess(board))
			{
				if(n<min)
					min=n;
			}
			return;
		}
		if(isSuccess(board))
		{
			if(n<min)
			{
				min=n;
			}
			
			//System.out.println(min);
			return;
		}
		
		
		for(int i=index;i<16;i++)
		{
			flip(board,i);
			find(n+1,i+1);
			flip(board,i);
			
		}
		
		
	}


	private static void flip(boolean[][] board, int index) {

		int row=index/4;
		int col=index%4;
		
		board[row][col]=!board[row][col];
		if(row-1>=0)
			board[row-1][col]=!board[row-1][col];
		if(col-1>=0)
			board[row][col-1]=!board[row][col-1];
		if(row+1<4)
			board[row+1][col]=!board[row+1][col];
		if(col+1<4)
			board[row][col+1]=!board[row][col+1];
	}

	private static boolean isSuccess(boolean[][] board) {

		boolean b=board[0][0];
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(board[i][j]!=b)
					return false;
			}
		}
		return true;
	}

}
