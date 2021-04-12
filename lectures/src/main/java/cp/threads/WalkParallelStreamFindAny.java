package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WalkParallelStreamFindAny
{
	public static void main()
	{
		try {
			boolean found =
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
					.anyMatch( word -> word.equals( "ipsum" ) );
			System.out.println( found );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
