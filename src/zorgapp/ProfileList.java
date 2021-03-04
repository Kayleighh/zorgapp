package zorgapp;

import java.util.ArrayList;

public class ProfileList
{

	/**
	 * 
	 */

	private ArrayList<Object>	prof	= new ArrayList<>();
	private Files				file	= new Files();
	public boolean chechedUser;
	ProfileList()
	{
		Profile addProfile = new Profile("Kayleigh", "Reeringh", 22, 1.54, 60);
		Medicine addMed = new Medicine("Escilatopram", "Helps with depression and anxiety disorder", "Pills", "10mg");
		Object newProfileWithMeds = addProfile + "" + addMed;
		prof.add(newProfileWithMeds);
	}

	public void add(Profile profile, Medicine medicine)
	{
		Profile addProfile = new Profile(profile.getFirstName(), profile.getLastName(), profile.getAge(),
				profile.getLength(), profile.getWeight());

		Medicine addMed = new Medicine(medicine.getMedicineName(), medicine.getDescription(), medicine.getSoort(),
				medicine.getDosage());

		Object newProfileWithMeds = addProfile + "" + addMed;
		prof.add(newProfileWithMeds);

		// write(newProfileWithMeds);

	}

	// Writes object to file
	public void write(Object newProfileWithMeds)
	{
		file.write(newProfileWithMeds);
	}

	// Gets specific profile+meds from arraylist
	public Object get(int index)
	{
		for (int i = 0; i < sizeOf(); i++)
		{
			if (i == index)
			{
				System.out.println(prof.get(i));
				return prof.get(i);
			}
		}
		return 1;
	}

	// Gets all profiles from arraylist/file
	// Needs work
	public void getAllProfile()
	{
		// file.read();
		for (Object profile : prof)
		{
			int index = prof.indexOf(profile);
			System.out.println("Patient number: " + index + "\n" + profile);
		}
	}

	public void edit(int index, Profile profile, Medicine med)
	{
		prof.get(index);
		String editedProfile = "" + profile + "" + med;
		prof.set(index, editedProfile);
	}

	public int sizeOf()
	{
		int size = prof.size();
		return size;
	}

	// Needs work
	

	public void remove(int index)
	{
		prof.remove(index);

	}
}
