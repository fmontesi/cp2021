package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// This is the example developed in the video 15-SynchronizedMap2TWords
public class SynchronizedMap2TWords {
	public static void main() {
		// word -> number of times that it appears over all files
		Map< String, Integer > occurrences = new HashMap<>();

		Thread t1 = new Thread( () -> computeOccurrences( "text1.txt", occurrences ) );
		Thread t2 = new Thread( () -> computeOccurrences( "text2.txt", occurrences ) );

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}

		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}

	private static void computeOccurrences(String filename, Map<String, Integer> occurrences) {
		try {
			Files.lines( Paths.get( filename ) ).flatMap( Words::extractWords ).map( String::toLowerCase ).forEach( s -> {
				synchronized( occurrences ) {
					occurrences.merge( s, 1, Integer::sum );
				}
			} );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
