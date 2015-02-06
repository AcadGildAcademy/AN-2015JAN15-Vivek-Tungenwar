import java.util.*;

public class challenge2 {

	public static void main(String[] args) {
		Set<Integer> set=new HashSet<Integer>();
		set.add(5);
		set.add(8);
		set.add(4);
		set.add(3);
		set.add(9);
		Iterator<Integer> i=set.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		// TODO Auto-generated method stub
	}

}
