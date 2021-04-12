package cp.threads;

public class NotifyWait
{
	private static final Object monitor = new Object();
	private static boolean t2Done = false;
	
	public static void main()
	{
		Thread t1 = new Thread( () -> {
			synchronized( monitor ) {
				if ( !t2Done ) {
					try {
						monitor.wait();
					} catch( InterruptedException e ) {
						e.printStackTrace();
					}
				}
			}
			System.out.println( "Hello from t1" );
		} );
		
		Thread t2 = new Thread( () -> {
			System.out.println( "Hello from t2" );
			synchronized( monitor ) {
				monitor.notify();
				t2Done = true;
			}
		} );
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
