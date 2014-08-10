package math;

import java.util.*;
/**
 * [2,12]区间的RoundNumbers（简称RN）个数:Rn[2,12]=Rn[0,12]-Rn[0,1]
	即：Rn[start,finish]=Rn[0,finish]-Rn[0,start-1]
	所以关键是给定一个X，求出Rn[0,X]
	现在假设X=10100100 
	这个X的二进制总共是8位，任何一个小于8位的二进制都小于X
	第一部分，求出长度为[0,7]区间内的二进制是RoundNumber的个数
		对于一个长度为Len的二进制(最高位为1)，如何求出他的RoundNumbers呢（假设为用R(len)来表达），分为奇数和偶数两种情况
		1、奇数情况：在Len=2k+1的情况下，最高位为1，剩下2k位，至少需要k+1为0
			用C(m,n)表示排列组合数：从m个位置选出n个位置的方法
			R(len)=C(2k,k+1)+C(2k,k+2)+...+C(2k,2k).
			由于 A：C(2k,0)+C(2k,1)+...+C(2k,2k)=2^（2k）
				 B:C(2k,0)=C(2k,2k), C(2k,1)=C(2k,2k-1) ,,C(2k,i)=C(2k,2k-i)
			于是  C(2k,0)+C(2k,1)+...+C(2k,2k)
				= C(2k,0)+C(2k,1)+...+C(2k,k)+C(2k,k+1)+C(2k,K+2)+...+C(2k,2k)
				= 2*R(len)+C(2k,k)
				=2^(2k)
				所以R(len)=1/2*{2^(2k)-C(2k,k)};
		2. 偶数情况 len=2*k，类似可以推到 R(len)=1/2*(2^(2k-1));
	第二部分，对于上面这个长度为8的例子:即X=10100100，首先如果本身是RoundNumbers，第二部分的结果总数+1
		第一部分已经将长度小于8的部分求出。现在要求长度=8的RoundNumber数目
		长度为8，所以第一个1不可改变
		现在到第二个1，如果Y是前缀如100*****的二进制，这个前缀下，后面取0和1必然小于X，已经有2个0，一个1，剩下的5个数字中至少需要2个0，
			所以把第二个1改为0：可以有C(5,2)+C(5,3)+C(5,4)+C(5,5)
		现在第三个1，也就是前最为101000**，同样求出，至少需要0个0就可，所以有C(2,0)+C(2,1)+C(2,2)个RoundNumbers
		。。。
		将所有除了第一个1以外的1全部变为0，如上算出有多少个RoundNumbers，结果相加（由于前缀不一样，所以后面不管怎么组合都是唯一的）

	将第一部分和第二部分的结果相加，就是最后的结果了。
 * @author xianping Tao
 *
 */
public class Combinatorics_RoundNumbers3252 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		Init();
		while(in.hasNext())
		{
			int a=in.nextInt();
			int b=in.nextInt();
			//System.out.println(roundNumber(a-1)+" " +roundNumber(b));
			System.out.println(roundNumber(b)-roundNumber(a-1));
		}
		
	}
	
   static int c[][]=new int[35][35];
   public static void Init(){
	    for(int i=0;i<33;i++){
	        c[i][0]=c[i][i]=1;
	        for(int j=1;j<i;j++)
	            c[i][j]=c[i-1][j]+c[i-1][j-1];
	    }
	   /*for(int i=0;i<33;i++)
	   {
		   for(int j=1;j<i;j++)
		   {
			   if(c[i][j]!=choose(i,j))
			   {
				   System.out.println(i+" "+j+" "+c[i][j]);
				   System.exit(1);
			   }
		   }
	   }*/
	}
	
	public static int roundNumber(int value)
	{
		char b[]=toBinary(value);
		int sum=0;
		for(int len=1;len<b.length;len++)
		{
			for(int j=(len+1)/2;j<len;j++)
			sum+=c[len-1][j];
		}
		int zeros=0;
		for(int i=1;i<b.length;i++)
		{
			if(b[i]=='1')
			{
				int k=(b.length+1)/2;
				int m=Math.max(0, k-(zeros+1));
				int n=b.length-i-1;
				for(int j=n;j>=m;j--)
					sum+=c[n][j];
			}
			else
			{
				zeros++;
			}
		}
		if(2*zeros>=b.length)
			sum++;
		return sum;
	}
	
	private static char[] toBinary(int value) {
		return Integer.toBinaryString(value).toCharArray();
	}

	/*public static int roundNumberOfLength(int len)
	{
		int k=len/2;
		if(len%2==0)
		{
			return (1<<(len-2));
		}
		else
		{
			return ((1<<(len-1))-choose(len-1,k))/2;
		}
	}*/

	
	public static int choose(int n, int m) {

		if(n==0)
			return 0;
		if(m==0||m==n)
			return 1;
		if(m>n)
			return 0;
		return choose(n-1,m-1)+choose(n-1,m);
	}
	
	public static int factor(int n)
	{
		if(n==0)
			return 1;
		int sum=1;
		for(int i=1;i<=n;i++)
			sum*=i;
		return sum;
	}

}
