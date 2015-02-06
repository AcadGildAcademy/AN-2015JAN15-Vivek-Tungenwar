package priority;

public class challenge1 {
	public static void main(String args[]) {
		threadA obj=new threadA();
		obj.setPriority(1);
		threadB obj1=new threadB();
		obj1.setPriority(5);
		threadC obj3=new threadC();
		obj3.setPriority(10);
		obj.start();
		obj1.start();
		obj3.start();
	}

}
