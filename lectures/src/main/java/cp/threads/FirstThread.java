package cp.threads;

public class FirstThread {
	public static void main() {
		Thread t1 = new Thread( () -> System.out.println( "Hello from t1" ) );
		Thread t2 = new Thread( () -> System.out.println( "Hello from t2" ) );
		Thread t3 = new Thread( () -> System.out.println( "Hello from t3" ) );
		Thread t4 = new Thread( () -> System.out.println( "Hello from t4" ) );
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
		 	t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch( InterruptedException e ) {
		 	e.printStackTrace();
		}
	}
}
