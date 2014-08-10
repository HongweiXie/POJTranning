package greedy;
/**
 * 总结一下犯得错误：
 * 1、没考虑到54行这里的错误
 * 2、 计算Math.sqrt的时候居然写出Math.abs,而且写错不止一次，以后应该把这些计算都放在函数里，这样方便看出问题所在
 * 3、计算Math.sqrt得到的x的值居然写出了int，简直是SB啊
 * 4、输出的格式没写对，不长眼
 * 5、排序居然写错了，我真实无力吐槽我自己了
 * 6、在一个程序上犯了那么多错误，可见平时的习惯是多么的差，多么的粗心。 发生错误一定要从头到尾仔细看
 */
import java.util.*;
/**
 * 时间 2014-04-10
 * @author xxyx
 *
 */
public class RadarInstallation1328 {
	
	public static void main(String args[])
	{
		Scanner in =new Scanner(System.in);
		
		int n=-1;
		int d=-1;
		int index=0;
		
		n=in.nextInt();
		d=in.nextInt();
		while(n!=0&&d!=0)
		{
			int[][] island=new int[n][2];
			boolean flag=false;
			for(int i=0;i<n;i++)
			{
				island[i][0]=in.nextInt();
				island[i][1]=in.nextInt();
				if(island[i][1]>d||island[i][1]<0)
				{
					flag=true;
				}
			}
			if(flag)
			{
				System.out.println("Case "+(++index)+": "+-1);
				n=in.nextInt();
				d=in.nextInt();
				continue;
			}
			sortByXCor(island);
			int sum=0;
			for(int i=0;i<n;i++)
			{
				sum++;
				double h=island[i][1];
				double dx=Math.sqrt(d*d-h*h);
				double x=island[i][0]+dx;
				for(i=i+1;i<n;i++)
				{
					double ndx=island[i][0]+Math.sqrt(d*d-island[i][1]*island[i][1]);
					if(distance(x,0,island[i][0],island[i][1])>d)
					{
						
						if(ndx<x)
						{
							x=ndx;
						}
						if(ndx>x)
						{
							break;
						}
						
					}
					
				}
				i--;
			}
			
			System.out.println("Case "+(++index)+": "+sum);
			n=in.nextInt();
			d=in.nextInt();
		}
	}

	private static double distance(double x1, double y1, double x2, double y2) {

		
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}

	private static void sortByXCor(int[][] island) {

		for(int i=0;i<island.length-1;i++)
		{
			int min=i;
			for(int j=i+1;j<island.length;j++)
			{
				if(island[j][0]<island[min][0])
				{
					min=j;
				}
				else if(island[j][0]==island[min][0])
				{
					if(island[j][1]>island[min][1])
						min=j;
				}
			}
			int[] temp=island[i];
			island[i]=island[min];
			island[min]=temp;
		}
	}

}
