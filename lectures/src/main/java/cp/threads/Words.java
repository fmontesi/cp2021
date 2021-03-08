package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Words {
	public static void main() {
		// https://docs.oracle.com/javase/tutorial/i18n/text/word.html
		try {
			Files.lines( Paths.get( "text1.txt" ) )
				.flatMap( Words::extractWords )
				.forEach( System.out::println );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	private static void hardcodedText() {
		String text = "Hello from the Death Star! Everything is fine over here. We are taking distancing very seriously.";
		
		BreakIterator it = BreakIterator.getWordInstance();
		it.setText( text );
		
		int start = it.first();
		int end = it.next();
		while( end != BreakIterator.DONE ) {
			String word = text.substring( start, end );
			if ( Character.isLetterOrDigit( word.charAt( 0 ) ) ) {
				System.out.println( word );
			}
			start = end;
			end = it.next();
		}
	}
	
	public static Stream< String > extractWords( String s ) {
		List< String > words = new ArrayList<>();
		
		BreakIterator it = BreakIterator.getWordInstance();
		it.setText( s );
		
		int start = it.first();
		int end = it.next();
		while( end != BreakIterator.DONE ) {
			String word = s.substring( start, end );
			if ( Character.isLetterOrDigit( word.charAt( 0 ) ) ) {
				words.add( word );
			}
			start = end;
			end = it.next();
		}
		
		return words.stream();
	}
}
