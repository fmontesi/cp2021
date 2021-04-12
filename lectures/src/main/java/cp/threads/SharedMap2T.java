package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SharedMap2T
{
	private static class Counter {
		private int c = 0;
	}
	
	public static void main()
	{
		Map< String, Integer > results = new HashMap<>();
		Counter counter = new Counter();		
		
		Thread t1 = new Thread( () -> {
			try {
				Files.lines( Paths.get( "text1.txt" ) )
					.flatMap( s -> Stream.of( s.split( " " ) ) )
					.forEach( word -> {
						synchronized( results ) {
							results.merge( word, 1, Integer::sum );
							counter.c++;
						}
					} );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread( () -> {
			try {
				Files.lines( Paths.get( "text2.txt" ) )
					.flatMap( s -> Stream.of( s.split( " " ) ) )
					.forEach( word -> {
						synchronized( results ) {
							results.merge( word, 1, Integer::sum );
							counter.c++;
						}
					} );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
//		results.forEach( (key, value) -> System.out.println( key + ": " + value ) );
//		System.out.println( "Total: " + counter.c );
	}
}
