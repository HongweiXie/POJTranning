package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 用 f[i][j][k] 表示第 i 支队，解决前 j 道题目中的 k 道的概率

用 g[i][j] 表示第 i 支队解决的题目数量至少是 1，最多是 j 的概率

于是我们可以得到下面的地推方程：

 

         f[i][j][k] =  f[i][j-1][k]*(1-p[i][j]) // 第 j 道题没有被 i 做出来

                         +f[i][j-1][k-1]*p[i][j]; // 第 j 道题被 i 做出来

         g[i][j] = f[i][M][1]*f[i][M][2]*.....*f[i][M][j];

 

因为要求解决题目最多的队伍数量不少于 N，于是我们可以这样做：

         ans1 = g[1][M]*g[2][M]*.....*g[T][M];

         ans2 = g[1][N-1]*g[2][N-1]*......*g[T][N-1];

最后我们的结果就应该是 ans = ans1-ans2;
 * @author xianping Tao
 *
 */
public class DP_CheckTheDifficultyofProblems2151 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {


		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str=null;

		double p[][]=new double[1001][32];
		double f[][][]=new double[1001][32][32];
		double g[][]=new double[1001][32];
		while(!(str=br.readLine()).equals("0 0 0"))
		{
			String terms[]=str.split(" ");
			int M=Integer.parseInt(terms[0]);
			int T=Integer.parseInt(terms[1]);
			int N=Integer.parseInt(terms[2]);
			
			for(int t=0;t<T;t++)
			{
				str=br.readLine();
				terms=str.split(" ");
				for(int i=0;i<M;i++)
				{
					p[t][i]=Double.parseDouble(terms[i]);
				}
			}
			
			for(int i=0;i<T;i++)
			{
				Arrays.fill(g[i], 0);
				for(int j=0;j<M+1;j++)
					Arrays.fill(f[i][j], 0);
			}
			for(int i=0;i<T;i++)
			{
				f[i][1][0]=1-p[i][0];
				f[i][1][1]=p[i][0];
			}
			for(int i=0;i<T;i++)
			{
				for(int j=2;j<M+1;j++)
				{
					for(int k=0;k<=j;k++)
					{
						f[i][j][k]=f[i][j-1][k]*(1-p[i][j-1]);
						if(k>0)
							f[i][j][k]+=f[i][j-1][k-1]*p[i][j-1];
					}
				}
			}
			
			for(int i=0;i<T;i++)
			{
				for(int j=1;j<M+1;j++)
				{
					for(int k=1;k<=j;k++)
						g[i][j]+=f[i][M][k];
				}
			}
			
			double ans1=1,ans2=1;
			for(int i=0;i<T;i++)
			{
				ans1*=g[i][M];
				ans2*=g[i][N-1];
			}
			
			System.out.println( String.format("%.3f", ans1-ans2));
			
		}
	}

}
