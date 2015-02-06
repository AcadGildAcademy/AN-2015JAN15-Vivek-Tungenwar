package priority;

public class threadC extends Thread {
	public void run()
	{
		for(int i=0;i<=2;i++)
		{
			System.out.println("Thread C");
		}
	}

}
