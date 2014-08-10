package math;

import java.util.*;
/**
 * [2,12]�����RoundNumbers�����RN������:Rn[2,12]=Rn[0,12]-Rn[0,1]
	����Rn[start,finish]=Rn[0,finish]-Rn[0,start-1]
	���Թؼ��Ǹ���һ��X�����Rn[0,X]
	���ڼ���X=10100100 
	���X�Ķ������ܹ���8λ���κ�һ��С��8λ�Ķ����ƶ�С��X
	��һ���֣��������Ϊ[0,7]�����ڵĶ�������RoundNumber�ĸ���
		����һ������ΪLen�Ķ�����(���λΪ1)������������RoundNumbers�أ�����Ϊ��R(len)��������Ϊ������ż���������
		1�������������Len=2k+1������£����λΪ1��ʣ��2kλ��������Ҫk+1Ϊ0
			��C(m,n)��ʾ�������������m��λ��ѡ��n��λ�õķ���
			R(len)=C(2k,k+1)+C(2k,k+2)+...+C(2k,2k).
			���� A��C(2k,0)+C(2k,1)+...+C(2k,2k)=2^��2k��
				 B:C(2k,0)=C(2k,2k), C(2k,1)=C(2k,2k-1) ,,C(2k,i)=C(2k,2k-i)
			����  C(2k,0)+C(2k,1)+...+C(2k,2k)
				= C(2k,0)+C(2k,1)+...+C(2k,k)+C(2k,k+1)+C(2k,K+2)+...+C(2k,2k)
				= 2*R(len)+C(2k,k)
				=2^(2k)
				����R(len)=1/2*{2^(2k)-C(2k,k)};
		2. ż����� len=2*k�����ƿ����Ƶ� R(len)=1/2*(2^(2k-1));
	�ڶ����֣����������������Ϊ8������:��X=10100100���������������RoundNumbers���ڶ����ֵĽ������+1
		��һ�����Ѿ�������С��8�Ĳ������������Ҫ�󳤶�=8��RoundNumber��Ŀ
		����Ϊ8�����Ե�һ��1���ɸı�
		���ڵ��ڶ���1�����Y��ǰ׺��100*****�Ķ����ƣ����ǰ׺�£�����ȡ0��1��ȻС��X���Ѿ���2��0��һ��1��ʣ�µ�5��������������Ҫ2��0��
			���԰ѵڶ���1��Ϊ0��������C(5,2)+C(5,3)+C(5,4)+C(5,5)
		���ڵ�����1��Ҳ����ǰ��Ϊ101000**��ͬ�������������Ҫ0��0�Ϳɣ�������C(2,0)+C(2,1)+C(2,2)��RoundNumbers
		������
		�����г��˵�һ��1�����1ȫ����Ϊ0����������ж��ٸ�RoundNumbers�������ӣ�����ǰ׺��һ�������Ժ��治����ô��϶���Ψһ�ģ�

	����һ���ֺ͵ڶ����ֵĽ����ӣ��������Ľ���ˡ�
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
