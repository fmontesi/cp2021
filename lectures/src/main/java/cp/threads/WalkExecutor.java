package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WalkExecutor
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
//		ExecutorService executor = Executors.newCachedThreadPool();
		ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
		
		try {
			Files.walk( Paths.get( "data" ) )
				.filter( Files::isRegularFile )
				.forEach( filepath -> {
					executor.submit( () -> {
						computeOccurrences( filepath, occurrences );
					} );
				} );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		try {
			executor.shutdown();
			executor.awaitTermination( 1, TimeUnit.DAYS );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
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
