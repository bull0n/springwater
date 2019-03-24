package ch.hearc.springwater.security.exceptions;

public class UtilisateurExistsException extends Exception
{
	private static final long serialVersionUID = 2609522059603989760L;

	public UtilisateurExistsException(String errorMessage, Throwable err)
	{
		super(errorMessage, err);
	}
}
