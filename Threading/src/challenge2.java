
public class challenge2 {
	public static void main(String args[])
	{
		Thread threadclass =new threadclass();
		threadclass.start();
		while(Thread.currentThread().isAlive()&&threadclass.isAlive())
		{
			System.out.println("Main thread will be alive till the child thread is live");
		}
	}

}
