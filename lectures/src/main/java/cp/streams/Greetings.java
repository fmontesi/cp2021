package cp.streams;

import java.util.Arrays;
import java.util.List;

public class Greetings {
	public static void main() {
		List< String > greetings =
			Arrays.asList( "Hello", "Hi", "Ciao", "Hej", "Bok", "Bonjour" );

		// Print out every greeting
		// for( String greeting : greetings ) {
		// System.out.println( greeting );
		// }

		// Does the same as the previous loop, but using streams
		// greetings.stream() // Stream< String >
		// .forEach( greeting -> System.out.println( greeting ) );

		// Print out the length of every greeting
		// for( String greeting : greetings ) {
		// System.out.println( greeting.length() );
		// }

		// Does the same as the previous loop
		// greetings.stream().map( greeting -> {
		// 	return greeting.length();
		// } )
		// 	.forEach( n -> System.out.println( n ) );

		// Shorter form of the above
		// greetings.stream()
		// 	.map( greeting -> greeting.length() )
		// 	.forEach( n -> System.out.println( n ) );

		// Even shorter form of the above
		greetings.stream()
			.map( String::length )
			.forEach( System.out::println );
	}
}
