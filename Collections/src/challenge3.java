import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class challenge3 {

	public static void main(String[] args) {
		Map<String, Integer> set=new HashMap<String, Integer>();
		set.put("Tutorial", 1);
		set.put("point", 2);
		set.put("is best", 3);
		Set<Entry<String, Integer>> i=set.entrySet();
		Iterator<Entry<String, Integer>> t=i.iterator();
		System.out.print("Set values:[");
		while(t.hasNext())
		{
			Map.Entry<String, Integer> m=(Map.Entry<String, Integer>)t.next();
			System.out.print(m.getValue()+"="+m.getKey());
		}
		System.out.print("]");
		// TODO Auto-generated method stub

	}

}
