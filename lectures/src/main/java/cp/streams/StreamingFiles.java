package cp.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StreamingFiles {
	public static void main() {
		final Path path = Paths.get( "text1.txt" );
		// printLines( path );
		// printLinesWithResource( path );
		// countLines( path );
		// countEt( path );
		// countEtFlatMap( path );
		countWords( path );
	}

	// NOT Recommended way of managing resources that need to be closed
	private static void printLines( Path path ) {
		try {
			Stream< String > lines = Files.lines( path );
			try {
				lines.forEach( System.out::println );
			} finally {
				lines.close();
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	// Recommended way of managing resources that need to be closed
	// See https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
	private static void printLinesWithResource( Path path ) {
		try( Stream< String > lines = Files.lines( path ) ) {
			lines.forEach( System.out::println );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	private static void countLines( Path path ) {
		try( Stream< String > lines = Files.lines( path ) ) {
			System.out.println( lines.count() );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	// NOT recommended version of counting occurrences of the word "et"
	private static void countEt( Path path ) {
		try( Stream< String > lines = Files.lines( path ) ) {
			int result = lines
				// Stream< String > "et blah et", "et quoque", "et al"
				.map( line -> line.split( " " ) )
				// now we have a Stream< String[] >
				// [ "et", "blah", "et" ], [ "et", "quoque" ], [ "et", "al" ]
				.map( words -> {
					int n = 0;
					for( String word : words ) {
						if( word.equals( "et" ) ) {
							n++;
						}
					}
					return n;
				} )
				// Stream< Integer > 2, 1, 1
				.reduce( 0, Integer::sum );
			System.out.println( result );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	// Recommended version of counting occurrences of the word "et"
	private static void countEtFlatMap( Path path ) {
		try( Stream< String > lines = Files.lines( path ) ) {
			long result = lines
				// Stream< String >
				.flatMap( line -> Stream.of( line.split( " " ) ) )
				// "et", "blah", "et", "et", "quoque", "et", "al"
				// now we have a Stream< String >
				.filter( word -> word.equals( "et" ) )
				.count();
			System.out.println( result );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	private static void countWords( Path path ) {
		try( Stream< String > lines = Files.lines( path ) ) {
			lines
				.flatMap( line -> Stream.of( line.split( " " ) ) )
				// Stream< String >
				// "et", "blah", "et", "et", "quoque", "et", "al"
				.map( word -> {
					Map< String, Integer > m = new HashMap<>();
					m.put( word, 1 );
					return m;
				} )
				// Stream< Map< String, Integer > >
				// { "et" -> 1 }, { "blah" -> 1 }, { "et" -> 1 },
				// { "et" -> 1 }, { "quoque" -> 1 }, { "et" -> 1 },
				// { "al" -> 1 }
				.reduce( new HashMap< String, Integer >(), ( m1, m2 ) -> {
					Map< String, Integer > result = new HashMap<>( m1 );
					m2.forEach( ( key, value ) -> result.merge( key, value, Integer::sum ) );
					return result;
				} )
				// {
				//	"et" -> 4,
				//	"al" -> 1,
				//	"quoque" -> 1,
				//  "blah" -> 1
				// }
				.forEach( ( word, value ) -> System.out.println( word + " -> " + value ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
