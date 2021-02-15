package cp.measure;

import cp.Utils;

public class MeasureDedicatedClasses {
	public static int summation( int n ) {
		if( n < 1 ) {
			return 0;
		}
		return n + summation( n - 1 );
	}

	private static class Sum5Runnable implements Runnable {
		public void run() {
			System.out.println( "Result: " + summation( 10000 ) );
		}
	}

	public static void main() {
		Utils.doAndMeasure( new Sum5Runnable() );
	}
}
