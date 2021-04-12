package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SharedMap2TBusyWaitCollaborative
{
	private static class Counter {
		private int c = 0;
	}
	
	private static class Guard {
		private volatile boolean turnT1 = true;
		private volatile boolean t1Done = false;
		private volatile boolean t2Done = false;
	}
	
	public static void main()
	{
		Map< String, Integer > results = new HashMap<>();
		Counter counter = new Counter();
		Guard guard = new Guard();
		
		Thread t1 = new Thread( () -> {
			try {
				Files.lines( Paths.get( "text1.txt" ) )
					.flatMap( s -> Stream.of( s.split( " " ) ) )
					.forEach( word -> {
						while( !guard.turnT1 && !guard.t2Done ) {}
						results.merge( word, 1, Integer::sum );
						counter.c++;
						guard.turnT1 = false;
					} );
				guard.t1Done = true;
			} catch( IOException e ) {
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread( () -> {
			try {
				Files.lines( Paths.get( "text2.txt" ) )
					.flatMap( s -> Stream.of( s.split( " " ) ) )
					.forEach( word -> {
						while( guard.turnT1 && !guard.t1Done ) {}
						results.merge( word, 1, Integer::sum );
						counter.c++;
						guard.turnT1 = true;
					} );
				guard.t2Done = true;
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
