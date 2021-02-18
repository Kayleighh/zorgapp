package zorgapp;

import java.util.ArrayList;

public class ProfileList {

	ArrayList<Profile> prof = new ArrayList<>();

	ProfileList() {

	}

	public Profile add(Profile profile) {
		prof.add(profile);
		return profile;

	}

	public Profile get(int index) 
	{
		int i = index; 
		Profile test = prof.get(i);
		for(i = 0; i <= 0; i++) {
			String first = test.getFirstName();
			String last = test.getLastName();
			int age = test.getAge();
			double length = test.getLength();
			double weigth = test.getWeight();
			String bmi = test.getBmi();
			System.out.println("Your info: "+ first + " " + last + " "+ age + " "+ length + " "+weigth+" "+bmi);
		}
		return test;
		
		
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
