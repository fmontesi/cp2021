package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class WalkParallelStream3
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		
		try {
			Files
				.walk( Paths.get( "data" ) )
				.filter( Files::isRegularFile )
				.collect( Collectors.toList() )
				.parallelStream()
				.forEach( filepath -> computeOccurrences( filepath, occurrences ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
//		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}
	
	private static void computeOccurrences( Path textFile, Map< String, Integer > occurrences )
	{
		try {
			Files.lines( textFile )
				.parallel()
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
