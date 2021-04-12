package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class SpinlockMap2T
{
	public static void main()
	{
		// word -> number of times that it appears over all files
		Map< String, Integer > occurrences = new HashMap<>();
		AtomicBoolean locked = new AtomicBoolean( false );
		
		Thread t1 = new Thread( () -> computeOccurrences( "text1.txt", occurrences, locked ) );
		Thread t2 = new Thread( () -> computeOccurrences( "text2.txt", occurrences, locked ) );
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
//		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}
	
	private static void computeOccurrences( String filename, Map< String, Integer > occurrences, AtomicBoolean locked )
	{
		try {
			Files.lines( Paths.get( filename ) )
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.forEach( s -> {
					while( !locked.compareAndSet( false, true ) ) {}
					occurrences.merge( s, 1, Integer::sum );
					locked.set( false );
				} );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
