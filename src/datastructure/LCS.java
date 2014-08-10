package datastructure;

public class LCS
{
	String x,y;
	int c[][],b[][];
	public LCS(String x,String y)
	{
		this.x=x;
		this.y=y;
		this.c=new int[x.length()][y.length()];
		this.b=new int[x.length()][y.length()];
	}
	
	public String lcs()
	{
		char cx[]=x.toCharArray();
		char cy[]=y.toCharArray();
		for(int i=0;i<cx.length;i++)
			c[i][0]=0;
		for(int j=0;j<cy.length;j++)
			c[0][j]=0;
		for(int i=1;i<cx.length;i++)
		{
			for(int j=1;j<cy.length;j++)
			{
				if(cx[i]==cy[j])
				{
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]=0;
					//System.out.print(cx[i]);
				}
				else if(c[i-1][j]>=c[i][j-1])
				{
					c[i][j]=c[i-1][j];
					b[i][j]=1;
				}
				else
				{
					c[i][j]=c[i][j-1];
					b[i][j]=-1;
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		int i=cx.length-1,j=cy.length-1;
		while(i>0&&j>0)
		{
			if(b[i][j]==0)
			{
				sb.append(cx[i]);
				i--;
				j--;
			}
			else if(b[i][j]==1)
			{
				i--;
			}
			else if(b[i][j]==-1)
			{
				j--;
			}
				 
		}
		if(cx[i]==cy[j])
			sb.append(cx[i]);
		return sb.reverse().toString();
	}
	
}
