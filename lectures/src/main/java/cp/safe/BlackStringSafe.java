package cp.safe;

public class BlackStringSafe {
	private String password;
	private String content;

	public BlackStringSafe( String password, String content ) {
		this.password = password;
		this.content = content;
	}

	public void enter( String password, StringSafeActor actor ) {
		if( this.password.equals( password ) ) {
			actor.run( content );
		}
	}
}
