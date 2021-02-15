package cp.safe;

public class SafeClient {
	private static class StringPrintActor implements StringSafeActor {
		public void run( String content ) {
			System.out.println( content );
		}
	}

	private static void mainBlackStringSafe() {
		final String password = "hello";
		final BlackStringSafe safe = new BlackStringSafe( password, "Very secure content" );

		// Three methods that do exactly the same thing

		// With a named class (StringPrintActor here)
		safe.enter( password, new StringPrintActor() );

		// With an anonymous inner class
		safe.enter( password, new StringSafeActor() {
			@Override
			public void run( String content ) {
				System.out.println( content );
			}
		} );

		// With a lambda expression
		safe.enter( password, ( content ) -> {
			System.out.println( content );
		} );

		// With a lambda expression that has only one parameter
		safe.enter( password, content -> {
			System.out.println( content );
		} );

		// With a lambda expression that has only one parameter and one line of code
		safe.enter( password, content -> System.out.println( content ) );

		// When the method you wanna run exists already
		safe.enter( password, System.out::println );
	}

	private static void mainStringSafe() {
		final String password = "hello";
		final StringSafe safe = new StringSafe( password, "Very secure content" );

		try {
			System.out.println( safe.readContent( password ) );
			safe.setContent( password, "New Very Secure Content" );
			System.out.println( safe.readContent( password ) );
		} catch( InvalidPassword e ) {
			e.printStackTrace();
		}
	}

	private static void mainBlackSafe() {
		final String password = "Hey";
		final BlackSafe< Integer > safe = new BlackSafe<>( password, 3 );

		safe.enter( password, content -> System.out.println( content * 2 ) );

		final BlackSafe< String > safeString = new BlackSafe<>( password, "Hello" );
		safeString.enter( password, System.out::println );
	}

	public static void main() {
		// mainStringSafe();
		// mainBlackStringSafe();
		// mainBlackSafe();
	}
}
