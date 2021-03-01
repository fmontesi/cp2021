package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SequentialMap2F {
	public static void main() {
		Map< Character, Long > occurrences = new HashMap<>();

		countLetters( Paths.get( "text1.txt" ), occurrences );
		countLetters( Paths.get( "text2.txt" ), occurrences );
		
		System.out.println( "e -> " + occurrences.get( 'e' ) );
	}

	private static void countLetters( Path textPath, Map< Character, Long > occurrences ) {
		try( Stream< String > lines = Files.lines( textPath ) ) {
			lines.forEach( line -> {
				for( int i = 0; i < line.length(); i++ ) {
					final char c = line.charAt( i );
					occurrences.merge( c, 1L, Long::sum );
				}
			} );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
