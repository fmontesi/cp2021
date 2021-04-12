package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WalkExecutorFuture
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new HashMap<>();
		ExecutorService executor = Executors.newWorkStealingPool();

		try {
			List< Future< Map< String, Integer > > > futures =
				Files.walk( Paths.get( "data" ) )
					.filter( Files::isRegularFile )
					.map( filepath ->
						executor.submit( () -> computeOccurrences( filepath ) )
					)
					.collect( Collectors.toList() );
			for( Future< Map< String, Integer > > future : futures ) {
				Map< String, Integer > fileOccurrences = future.get();
				fileOccurrences.forEach( (word, n) -> occurrences.merge( word, n, Integer::sum ) );
			}
		} catch( InterruptedException | ExecutionException | IOException e ) {
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
	
	private static Map< String, Integer > computeOccurrences( Path textFile )
	{
		Map< String, Integer > occurrences = new HashMap<>();
		try {
			Files.lines( textFile )
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		return occurrences;
	}
}
