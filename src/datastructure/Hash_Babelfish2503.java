package datastructure;

import java.util.*;
import java.io.*;
public class Hash_Babelfish2503 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line=br.readLine();
		HashMap<String,String> map=new HashMap<String,String>();
		while(line!=null&&line.length()>1)
		{
			String strs[]=line.split(" ");
			map.put(strs[1], strs[0]);
			line=br.readLine();
		}
		line=br.readLine();
		while(line!=null&&line.length()>0)
		{
			String value=map.get(line);
			if(value!=null)
				System.out.println(value);
			else
				System.out.println("eh");
			line=br.readLine();
		}
	}

}
