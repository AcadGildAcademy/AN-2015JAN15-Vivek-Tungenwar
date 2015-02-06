import java.util.*;

public class challenge1 {

	public static void main(String[] args) {
		List<Integer> obj=new ArrayList<Integer>();
		obj.add(7);
		obj.add(2);
		obj.add(3);
		obj.add(4);
		obj.add(5);
		obj.add(6);
		obj.add(1);
		obj.add(8);
		Iterator<Integer> i=obj.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		// TODO Auto-generated method stub
	}

}
