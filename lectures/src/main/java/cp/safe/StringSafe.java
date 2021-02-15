package cp.safe;

public class StringSafe {
	private String password;
	private String content;
	
	public StringSafe( String password, String content ) {
		this.password = password;
		this.content = content;
	}

	/**
	 * Opens the safe, reads the content inside and returns it. The safe is considered locked
	 * afterwards.
	 * 
	 * @throws InvalidPassword if the password is incorrect.
	 * @return the content of the safe
	 */
	public String readContent( String password )
		throws InvalidPassword {
		if( this.password.equals( password ) ) {
			return content;
		} else {
			throw new InvalidPassword();
		}
	}

	public void setContent( String password, String newContent )
		throws InvalidPassword {
		if( this.password.equals( password ) ) {
			content = newContent;
		} else {
			throw new InvalidPassword();
		}
	}
}
