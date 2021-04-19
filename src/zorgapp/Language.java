package zorgapp;

public class Language
{
	/*
	 * Deze klasse word gebruikt om de taal op te vragen en te veranderen in de menu
	 * klasse.
	 */
	private String language = "en";

	public Language()
	{

	}

	public void setLanguage(int lan)
	{
		if (lan == 1)
		{
			language = "en";
		} else if (lan == 2)
		{
			language = "nl";
		}
	}

	public String getLanguage()
	{
		return language;
	}
}
