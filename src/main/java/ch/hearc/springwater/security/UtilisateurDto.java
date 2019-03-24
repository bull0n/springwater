package ch.hearc.springwater.security;

public class UtilisateurDto
{
	private String nomUtilisateur;
	private String motDePasse;
	private String motDePasseConfirmation;

	public String getNomUtilisateur()
	{
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur)
	{
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse()
	{
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse)
	{
		this.motDePasse = motDePasse;
	}

	public String getMotDePasseConfirmation()
	{
		return motDePasseConfirmation;
	}

	public void setMotDePasseConfirmation(String motDePasseConfirmation)
	{
		this.motDePasseConfirmation = motDePasseConfirmation;
	}

}
