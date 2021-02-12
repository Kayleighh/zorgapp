package zorgapp;

import java.util.*;
public class ZorgApp {
	/*In deze klasse worden geen functies geschreven, alleen uitgevoerd.*/
	private ProfileList profileList = new ProfileList();
	
	//constructor
	ZorgApp() 
	{
		//maakt een nieuw object aan met het type Profile (uit Profile.class)
		final Profile prof = new Profile();
		//roept de functie aan uit Profile
		prof.setFirstName();
		prof.setLastName();
		prof.setAge();
		prof.setLength();
		prof.setWeight();
		/* om op een andere manier de data op te vragen:
		 *  prof.getFirstName();
		 * prof.getLastName();
		 * prof.getAge();
		 * prof.getLength();
		 * prof.getWeigth();
		 * prof.getBmi();
		 */
		
		//voegt toe aan arraylist (niet van belang)
		profileList.add(prof);
		int test = profileList.sizeOf();
		System.out.println(test);
		profileList.get(0);
		
		
	}
	
}
