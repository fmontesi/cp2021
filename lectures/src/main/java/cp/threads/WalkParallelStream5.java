package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WalkParallelStream5
{
	public static void main()
	{
		try {
			Map< String, Integer > occurrences =
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
					.collect( Collectors.toMap(
						word -> word,
						word -> 1,
						Integer::sum
					) );
//			occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
