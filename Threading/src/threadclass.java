
public class threadclass extends Thread {
	
	public void run()
	{
		System.out.println("My Thread created "+Thread.currentThread());
		for(int i=1;i<=10;i++)
		{
			System.out.println("Printing the count"+i);
		}
	}
}
