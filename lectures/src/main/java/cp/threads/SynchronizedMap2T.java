package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// This is the example developed in the video 13-SynchronizedMap2T
public class SynchronizedMap2T {
	public static void main() {
		Map< Character, Long > occurrences = new HashMap<>();

		Thread t1 = new Thread( () ->
			countLetters( Paths.get( "text1.txt" ), occurrences ) );
		Thread t2 = new Thread( () ->
			countLetters( Paths.get( "text2.txt" ), occurrences ) );

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
		System.out.println( "e -> " + occurrences.get( 'e' ) );
	}

	private static void countLetters( Path textPath, Map< Character, Long > occurrences ) {
		try( Stream< String > lines = Files.lines( textPath ) ) {
			lines.forEach( line -> {
				for( int i = 0; i < line.length(); i++ ) {
					final char c = line.charAt( i );
					synchronized( occurrences ) {
						occurrences.merge( c, 1L, Long::sum );
					}
				}
			} );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
