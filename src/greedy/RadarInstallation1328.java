package greedy;
/**
 * �ܽ�һ�·��ô���
 * 1��û���ǵ�54������Ĵ���
 * 2�� ����Math.sqrt��ʱ���Ȼд��Math.abs,����д��ֹһ�Σ��Ժ�Ӧ�ð���Щ���㶼���ں�����������㿴����������
 * 3������Math.sqrt�õ���x��ֵ��Ȼд����int����ֱ��SB��
 * 4������ĸ�ʽûд�ԣ�������
 * 5�������Ȼд���ˣ�����ʵ�����²����Լ���
 * 6����һ�������Ϸ�����ô����󣬿ɼ�ƽʱ��ϰ���Ƕ�ô�Ĳ��ô�Ĵ��ġ� ��������һ��Ҫ��ͷ��β��ϸ��
 */
import java.util.*;
/**
 * ʱ�� 2014-04-10
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
