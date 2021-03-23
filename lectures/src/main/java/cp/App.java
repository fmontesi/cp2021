package cp;

import cp.threads.SynchronizedMap;

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
		// Utils.doAndMeasure( SynchronizedMap2T::main );
		// SynchronizedMap2TWords.main();
		SynchronizedMap.main();
	}
}
