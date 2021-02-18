package zorgapp;

import java.util.*;
import java.util.Scanner; 
public class ZorgApp {

	private ProfileList profileList = new ProfileList();
	private Scanner scanner = new Scanner(System.in);
	private Profile prof = new Profile();

	ZorgApp() {
		menu();
	}

	public void menu() {
		int choice = 1;
		System.out.println("MAKE A CHOICE");
		System.out.println("1: Zorgverlener  2: Patient");

		choice = scanner.nextInt();
		switch (choice) {
		case 1:
			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1:View data  2:Edit data ");
			
			int zorgChoice = scanner.nextInt();
			scanner.nextLine();
			switch (zorgChoice) {
			case 1:
				System.out.println("You have chosen to view patients data");
				profileList.get(0);
				menu();
				break;
				
			case 2:
				
				System.out.println("YOU HAVE CHOSEN TO EDIT PATIENTS DATA");
			
				System.out.println("Enter first name here: ");
				String firstname = scanner.nextLine();
				prof.setFirstName(firstname);
				System.out.println("First name has been changed to " + firstname);
				
				
				
				System.out.println("Enter last name here: ");
				String lastname = scanner.nextLine();
				prof.setLastName(lastname);
				System.out.println("Last name has been changed to " + lastname);

				System.out.println("Enter age here: ");
				int age = scanner.nextInt();
				prof.setAge(age);
				System.out.println("Age has been changed to " + age);
				
				System.out.println("Enter length here: ");
				double length = scanner.nextDouble();
				prof.setLength(length);
				System.out.println("Length has been changed to " + length);
			
				System.out.println("Enter weigth here: ");
				double weight = scanner.nextDouble();
				prof.setWeight(weight);
				System.out.println("Weight has been changed to " + weight);
				
				profileList.add(prof);
				menu();
				break;
			}
			break;

		case 2:
			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1:View data  2:Edit data ");

			int patChoice = 1;

			patChoice = scanner.nextInt();
			switch (patChoice) {
			case 1:
				profileList.get(0);
				menu();
				break;
			case 2:
				System.out.println("YOU HAVE CHOSEN TO EDIT YOUR DATA");
				
				System.out.println("Enter first name here: ");
				String firstname = scanner.nextLine();
				prof.setFirstName(firstname);
				System.out.println("First name has been changed to " + firstname);
				
				System.out.println("Enter last name here: ");
				String lastname = scanner.nextLine();
				prof.setLastName(lastname);
				System.out.println("Last name has been changed to " + lastname);

				System.out.println("Enter age here: ");
				int age = scanner.nextInt();
				prof.setAge(age);
				System.out.println("Age has been changed to " + age);
				
				System.out.println("Enter length here: ");
				double length = scanner.nextDouble();
				prof.setLength(length);
				System.out.println("Length has been changed to " + length);
			
				System.out.println("Enter weigth here: ");
				double weight = scanner.nextDouble();
				prof.setWeight(weight);
				System.out.println("Weight has been changed to " + weight);
				
				profileList.add(prof);
				menu();
				break;
			}
			break;
		default:
			System.out.println("NOTHING HAS BEEN SELECTED");
			menu();
			break;
		}
	}

}
