package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WalkParallelStream4
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
				.flatMap( textFile -> {
					try {
						return Files.lines( textFile );
					} catch( IOException e ) {
						return Stream.empty();
					}
				} )
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
//		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}
}
