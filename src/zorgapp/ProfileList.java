package zorgapp;

import java.util.ArrayList;

public class ProfileList {

	ArrayList<Profile> prof = new ArrayList<>();
	public Medicine medicine = new Medicine();
	public Files file = new Files();
	ProfileList() {

	}

	public Profile add(Profile profile) {
		Profile test2 = new Profile(profile.getFirstName(), profile.getLastName(), profile.getAge(),
				profile.getLength(), profile.getWeight());
		test2.toString();
		prof.add(test2);
		file.write(test2);
		return profile;

	}

	// Functie aanpassen.
	public Profile get(int index) {
		Profile test = prof.get(index);
		System.out.println(test.getFirstName() + " " + test.getLastName() + " " + test.getAge() + " " + test.getLength()
				+ " " + test.getWeight() + " " + test.getBmi());
		return test;

	}

	// Functie aanpassen.
	public void getAllProfiles() {
		file.read();
		/*for (Profile test : prof) {
			int index = prof.indexOf(test);
			System.out.println(index + " " + test.getFirstName() + " " + test.getLastName());
		}*/

	}

	public Profile remove(int index) {
		Profile rem = prof.remove(index);
		return rem;
	}

	public int sizeOf() {
		int size = prof.size();
		return size;
	}

	public void edit(int index, String test) {
		prof.get(index).setFirstName(test);

	}
}
