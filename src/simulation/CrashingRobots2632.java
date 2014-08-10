package simulation;

import java.util.*;
public class CrashingRobots2632 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in=new Scanner(System.in);
		int K=in.nextInt();
		for(int k=0;k<K;k++)
		{
			int A=in.nextInt();
			int B=in.nextInt();
			int N=in.nextInt();
			int M=in.nextInt();
			Robot[] robots=new Robot[N];
			for(int i=0;i<N;i++)
			{
				robots[i]=new Robot((i+1),in.nextInt(),in.nextInt(),in.next());
			}
			boolean flag=true;
			for(int i=0;i<M;i++)
			{
				int id=in.nextInt();
				String command=in.next();
				int repeat=in.nextInt();
				if(flag==false)
					continue;
				flag=robots[id-1].excute(robots,N,A,B,command, repeat);
				
			}
			if(flag)
				System.out.println("OK");
		}
	}

	

}

class Robot
{
	int id;
	double x,y,direction;
	public Robot(int id,int x,int y,String dir)
	{
		this.id=id;
		this.x=x;
		this.y=y;
		if(dir.equals("E"))
			this.direction=0;
		else if(dir.equals("N"))
			this.direction=Math.PI/2;
		else if(dir.equals("W"))
			this.direction=Math.PI;
		else
			this.direction=-Math.PI/2;
	}
	
	public boolean excute( Robot[] robots, int n, int a,
			int b,String command,int repeat)
	{
		if(command.equals("F"))
		{
			for(int i=0;i<repeat;i++)
			{
				this.x+=Math.cos(direction);
				this.y+=Math.sin(direction);
				if(isCrash(id,robots,n,a,b))
				{
					return false;
				}
			}
			
		}
		else if(command.equals("L"))
		{
			this.direction+=(repeat*Math.PI/2);
		}
		else if(command.equals("R"))
		{
			this.direction-=(repeat*Math.PI/2);
		}
		return true;
	}
	private boolean isCrash(int id, Robot[] robots, int n, int a,
			int b) {

		Robot robot=robots[id-1];
		
		for(int i=0;i<n;i++)
		{
			if(i!=id-1)
			{
				Robot other=robots[i];
				if(Math.abs(robot.x-other.x)<0.5&&Math.abs(robot.y-other.y)<0.5)
				{
					System.out.println("Robot "+id+" crashes into robot "+(i+1));
					return true;
				}
			}
		}
		if(robot.x<=0.5||robot.y<=0.5||robot.x>a+0.5||robot.y>b+0.5)
		{
			System.out.println("Robot "+id+" crashes into the wall");
			return true;
		}
		return false;
	}
}
