package zorgapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private ProfileList profileList = new ProfileList();
	private Scanner scanner = new Scanner(System.in);
	private Profile profile = new Profile();

	Menu() {

	}

	public void createMenu() {
		int choice = 1;
		try {
			System.out.println("MAKE A CHOICE");
			System.out.println("1: Zorgverlener  2: Patient   3: Close program");

			choice = scanner.nextInt();
			if (choice == 1) {
				staffMenu();
			} else if (choice == 2) {
				patientMenu();
			} else if (choice == 3) {
				System.out.println("Goodbye!");
			} else {
				System.out.println("NOTHING HAS BEEN SELECTED");
				createMenu();
			}
		} catch (InputMismatchException e) {
			System.out.println(
					"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \n Please change this.");
			scanner.nextLine();
			createMenu();
		}
	}

	private void patientMenu() {
		System.out.println("WHAT DO YOU WANT TO DO?");
		System.out.println("1:View data  2:Edit data   3:Go back ");

		int patChoice = 1;

		patChoice = scanner.nextInt();
		scanner.nextLine();
		switch (patChoice) {
		case 1:
			if (profileList.sizeOf() > 0) {
				profileList.get(0);
				createMenu();
			} else {
				System.out.println("THERE ARE NO PATIENTS IN THE SYSTEM");
				createMenu();
			}
			break;
		case 2:
			System.out.println("YOU HAVE CHOSEN TO EDIT YOUR DATA");

			System.out.println("Enter first name here: ");
			String firstname = scanner.nextLine();
			profile.setFirstName(firstname);
			System.out.println("First name has been changed to " + firstname);

			profileList.add(profile);
			createMenu();
			break;
		case 3:
			createMenu();
			break;

		default:
			System.out.println("NOTHING HAS BEEN SELECTED");
			createMenu();
			break;
		}
	}

	private void staffMenu() {
		try {
			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1:View data  2:Edit data   3:Go back ");

			int zorgChoice = scanner.nextInt();
			scanner.nextLine();
			switch (zorgChoice) {
			case 1:
				System.out.println("You have chosen to view patients data");
				if (profileList.sizeOf() > 0) {
					profileList.getAllProfiles();

					createMenu();
				} else {
					System.out.println("THERE ARE NO PATIENTS IN THE SYSTEM");
					createMenu();
				}
				break;

			case 2:
				try {
					System.out.println("YOU HAVE CHOSEN TO EDIT PATIENTS DATA");

					System.out.println("Enter first name here: ");
					String firstname = scanner.nextLine();
					profile.setFirstName(firstname);
					System.out.println("First name has been changed to " + firstname);

					System.out.println("Enter last name here: ");
					String lastname = scanner.nextLine();
					profile.setLastName(lastname);
					System.out.println("Last name has been changed to " + lastname);

					System.out.println("Enter age here: ");
					int age = scanner.nextInt();
					profile.setAge(age);
					System.out.println("Age has been changed to " + age);

					System.out.println("Enter length here: ");
					double length = scanner.nextDouble();
					profile.setLength(length);
					System.out.println("Length has been changed to " + length);

					System.out.println("Enter weigth here: ");
					double weight = scanner.nextDouble();
					profile.setWeight(weight);
					System.out.println("Weight has been changed to " + weight);

					profileList.add(new Profile(profile.getFirstName(), profile.getLastName(), profile.getAge(),
							profile.getLength(), profile.getWeight()));
					createMenu();
					break;
				} catch (InputMismatchException e) {
					System.out.println(
							"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \n Please change this.");
					scanner.nextLine();
				}
			case 3:
				createMenu();
				break;

			default:
				System.out.println("NOTHING HAS BEEN SELECTED");
				createMenu();
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println(
					"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \\n Please change this.");
			scanner.nextLine();
			createMenu();
		}
	}
}
