package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Walk
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		List< Thread > threads = new ArrayList<>();
		
		try {
			Files.walk( Paths.get( "data" ) )
				.filter( Files::isRegularFile )
				.map( path -> new Thread( () -> computeOccurrences( path, occurrences ) ) )
				.forEach( thread -> threads.add( thread ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		threads.forEach( Thread::start );
		threads.forEach( t -> {
			try {
				t.join();
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
		} );
		
//		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}
	
	private static void computeOccurrences( Path textFile, Map< String, Integer > occurrences )
	{
		try {
			Files.lines( textFile )
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
