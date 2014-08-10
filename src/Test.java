import java.util.*;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);
	}

	public static void testPriorityQueue()
	{
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
		for(int i=2;i<10;i++)
			pq.add(i);
		System.out.println(pq.peek());
	}
}
