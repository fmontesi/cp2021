package cp;

public class Utils {
	public static void doAndMeasure( Runnable runnable ) {
		long t1 = System.currentTimeMillis();
		runnable.run();
		long t2 = System.currentTimeMillis();
		System.out.println( "Elapsed time: " + (t2 - t1) + "ms" );
	}
}
