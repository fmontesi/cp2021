package cp;

import cp.threads.SequentialMap2F;
import cp.threads.SynchronizedMap2T;

/**
 * Main class (entry point) of the Java Application.
 */
public final class App {
	/**
	 * Entry point method of the Java application.
	 * 
	 * @param args The arguments of the program.
	 */
	public static void main( String[] args ) {
		// FirstThread.main();
		// Counting.main();
		// Utils.doAndMeasure( SequentialMap2F::main );
		Utils.doAndMeasure( SynchronizedMap2T::main );
	}
}
