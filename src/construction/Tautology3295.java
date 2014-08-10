package construction;

import java.util.*;

public class Tautology3295 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in =new Scanner(System.in);
		String str="";
		Map<Character,Character> map;
		while(!(str=in.next()).equals("0"))
		{
			char i=0;
			for(;i<32;i++)
			{
				char p=(char) (0x1 & i);
				char q=(char) ((0x2 & i)>>1);
				char r=(char) ((0x4 & i)>>2);
				char s=(char) ((0x8 & i)>>3);
				char t=(char) ((0x10 & i)>>4);
				map=new HashMap<Character,Character>();
				map.put('p', p);
				map.put('q', q);
				map.put('r', r);
				map.put('s', s);
				map.put('t', t);
				if(judge(str,map)==false)
				{
					System.out.println("not");
					break;
				}
			}
			if(i==32)
				System.out.println("tautology");
							
		}
	}

	private static boolean judge(String str, Map<Character, Character> map) {

		Stack<Character> stack=new Stack<Character>();//操作栈
		char[] cstr=str.toCharArray();
		for(int i=0;i<cstr.length;i++)
		{
			Character v=map.get(cstr[i]);
			if(cstr[i]=='0'||cstr[i]=='1')
			{
				stack.push((char)(cstr[i]-'0'));
			}
			else if(v==null)
				stack.push(cstr[i]);
			else
			{
				stack.push(v);
			}
		}
		/*for(char c:stack)
		{
			System.out.print((int)c+" ");
			
		}
		System.out.println();*/
		Stack<Character> vStack=new Stack<Character>();//值栈
		while(!stack.isEmpty())
		{
			char c1=stack.pop();
			if(c1==0||c1==1)
				vStack.push(c1);
			else
			{
				if(c1=='N')
				{
					char c2=vStack.pop();
					if(c2==0)
						c2=1;
					else 
						c2=0;
					vStack.push(c2);
				}
				else
				{
					vStack.push(compute(vStack.pop(),vStack.pop(),c1));
				}
			}
		}
		return vStack.pop()==1;
	}

	private static char compute(char c1, char c2, char opt) {

		//System.out.println((int)c2);
		if(opt=='K')//and
			return (char) ((c1 & c2) &0x01);
		if(opt=='A')//or
			return (char) ((c1 | c2) & 0x01);
		if(opt=='C')//implies->=~A|B
			return (char)(((~c1) | c2) &0x01);
		if(opt=='E')//equal 也就是同或，异或取反
			return (char)(~(c1^c2) &0x01);
		return 0;
	}

}
