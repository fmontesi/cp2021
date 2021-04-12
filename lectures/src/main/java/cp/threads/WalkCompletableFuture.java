package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class WalkCompletableFuture
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		
		try {
			CompletableFuture< Void >[] futures =
				Files.walk( Paths.get( "data" ) )
					.filter( Files::isRegularFile )
					.map( filepath ->
						CompletableFuture.supplyAsync( () -> computeOccurrences( filepath ) )
							.thenAccept( fileOccurrences ->
								fileOccurrences.forEach( (word, n) -> occurrences.merge( word, n, Integer::sum ) )
							)
					).collect( Collectors.toList() ).toArray( new CompletableFuture[0] );
			CompletableFuture
				.allOf( futures )
				.join();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
//		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}
	
	private static Map< String, Integer > computeOccurrences( Path textFile )
	{
		Map< String, Integer > occurrences = new HashMap<>();
		try {
			Files.lines( textFile )
				.parallel()
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		return occurrences;
	}
}
