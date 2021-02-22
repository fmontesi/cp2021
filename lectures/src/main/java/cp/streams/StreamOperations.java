package cp.streams;

import java.util.List;

public class StreamOperations {
	public static void main() {
		List< String > names = List.of( "Kim", "Inge", "Lasse", "Joan", "Christian", "Kasper", "Annette", "Susanne",
			"Caroline", "Holger Danske", "Carlsberg", "Albani", "Faxe", "Coca Cola", "Pepsi", "Odense", "Royal Beer",
			"Skovlyst", "Svenninge" );

		// Print all names starting with K
		// names.stream() // Stream< String >
		// 	.filter( s -> s.startsWith( "K" ) )
		// 	.forEach( System.out::println );

		// Print how many names start with K
		// System.out.println(
		// 	names.stream().filter( s -> s.startsWith( "K" ) ).count()
		// );

		// Print all names shorter than 5, sorted
		names.stream()
			.filter( s -> s.length() < 5 )
			.sorted( ( s1, s2 ) -> s1.compareTo( s2 ) )
			.forEach( System.out::println );
	}
}
