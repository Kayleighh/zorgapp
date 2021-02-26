package zorgapp;

import java.util.ArrayList;

public class ProfileList {

	ArrayList<Profile> prof = new ArrayList<>();

	ProfileList() {

	}

	public Profile add(Profile profile) {
		Profile test2 = new Profile(profile.getFirstName(), profile.getLastName(), profile.getAge(),
				profile.getLength(), profile.getWeight());
		prof.add(test2);
		return profile;

	}

	// Functie aanpassen.
	public Profile get(int index) {
		int i = index;
		Profile test = prof.get(i);
		System.out.println(test.getFirstName() + " " + test.getLastName() + " " + test.getAge() + " " + test.getLength()
				+ " " + test.getWeight() + " " + test.getBmi());
		return test;

	}

	// Functie aanpassen.
	public void getAllProfiles() {
		for (Profile test : prof) {
			System.out.println(test.getFirstName() + " " + test.getLastName());
		}
	}

	public Profile remove(int index) {
		Profile rem = prof.remove(index);
		return rem;
	}

	public int sizeOf() {
		int size = prof.size();
		return size;
	}
}
