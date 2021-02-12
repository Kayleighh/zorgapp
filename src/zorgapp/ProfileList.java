package zorgapp;

import java.util.ArrayList;

public class ProfileList
{
	/*
	 * In deze klasse wordt een ArrayList gemaakt, om de profiles op te slaan
	 */
	private ArrayList<Profile> prof = new ArrayList<>();

	ProfileList()
	{
		
	}
	
	//functie om de profile op te slaan
	//heeft als parameter een object van het type Profile
	public Profile add(Profile profile)
	{
		prof.add(profile);
		return profile;
		
		
	}
	
	//functie om de data uit de arraylist te halen
	//heeft een parameter van het type int, maar stuurt het datatype Profile terug.
	public Profile get(int index) 
	{
		//for loop om de date uit de arraylist te krijgen
		int i = 0;
		Profile test = prof.get(i);
		for(i = 0; i <= 0; i++) {
			String teet = test.getFirstName();
			String beep = test.getLastName();
			int yee = test.getAge();
			double yo = test.getLength();
			double baa = test.getWeight();
			String bm = test.getBmi();
			System.out.println("Your info: "+ teet + " " + beep + " "+ yee + " "+ yo + " "+baa+" "+bm);
		}
		return test;
		
		
	}
	
	//functie om een profile te verwijderen.
	public Profile remove(int index)
	{
	Profile rem = prof.remove(index);
	return rem;
	}
	
	//functie om te kijken hoeveel dingen er in de arraylist zitten.
	public int sizeOf()
	{
		int size = prof.size();
		return size;
	}
}
