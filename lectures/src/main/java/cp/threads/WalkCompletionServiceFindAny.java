package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WalkCompletionServiceFindAny
{
	public static void main()
	{
		boolean found = false;
		ExecutorService executor = Executors.newWorkStealingPool();
		ExecutorCompletionService< Boolean > completionService =
			new ExecutorCompletionService<>( executor );
		
		try {
			long pendingTasks = 
				Files.walk( Paths.get( "data" ) )
					.filter( Files::isRegularFile )
					.map( filepath ->
						completionService.submit( () -> computeOccurrences( filepath ) )
					).count();
			while( pendingTasks > 0 && !found ) {
				found = completionService.take().get();
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
		
		System.out.println( found );
	}
	
	private static boolean computeOccurrences( Path textFile )
	{
		try {
			return Files.lines( textFile )
				.flatMap( Words::extractWords )
				.map( String::toLowerCase )
				.anyMatch( word -> word.equals( "ipsum" ) );
		} catch( IOException e ) {
			e.printStackTrace();
			return false;
		}
	}
}
