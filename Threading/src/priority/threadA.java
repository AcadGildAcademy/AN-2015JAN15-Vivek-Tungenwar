package priority;

public class threadA extends Thread {
	{
		for(int i=0;i<=2;i++)
		{
			System.out.println("Thread A");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
