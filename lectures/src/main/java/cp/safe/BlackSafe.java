package cp.safe;

public class BlackSafe< T > {
	private String password;
	private T content;

	public BlackSafe( String password, T content ) {
		this.password = password;
		this.content = content;
	}

	public void enter( String password, SafeActor< T > actor ) {
		if( this.password.equals( password ) ) {
			actor.run( content );
		}
	}
}
