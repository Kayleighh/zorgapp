package zorgapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu
{
	private ProfileList	profileList	= new ProfileList();
	private Scanner		scanner		= new Scanner(System.in);
	private Profile		profile		= new Profile();
	private Medicine	med			= new Medicine();
	private Boolean		medicine	= true;

	Menu()
	{

	}

	public void createMenu()
	{
		int choice = 1;
		try
		{
			System.out.println("MAKE A CHOICE");
			System.out.println("1: Zorgverlener  2: Patient   3: Close program");

			choice = scanner.nextInt();
			if (choice == 1)
			{
				staffMenu();
			} else if (choice == 2)
			{
				verifyUser();
			} else if (choice == 3)
			{
				System.out.println("Goodbye!");
			} else
			{
				System.out.println("NOTHING HAS BEEN SELECTED");
				createMenu();
			}
		} catch (InputMismatchException e)
		{
			System.out.println(
					"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \n Please change this.");
			scanner.nextLine();
			createMenu();
		}
	}

	public void patientMenu()
	{
		System.out.println("WHAT DO YOU WANT TO DO?");
		System.out.println("1:View data  2:Edit data   3:Go back ");

		int patChoice = 1;

		patChoice = scanner.nextInt();
		scanner.nextLine();
		switch (patChoice)
		{
		case 1:
			profileList.get(0);
			patientMenu();
			break;
		case 2:
			// needs work
			System.out.println("YOU HAVE CHOSEN TO EDIT YOUR DATA");
			profileList.get(0);
			System.out.println("Enter first name here: ");
			String firstname = scanner.nextLine();
			profile.setFirstName(firstname);
			System.out.println("First name has been changed to " + firstname);
			profileList.edit(0, profile, med);
			patientMenu();
			break;
		case 3:
			createMenu();
			break;

		default:
			System.out.println("NOTHING HAS BEEN SELECTED");
			patientMenu();
			break;
		}
	}

	private void staffMenu()
	{
		try
		{
			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1:View data  2:Add a new patient  3:Go back ");

			int zorgChoice = scanner.nextInt();
			scanner.nextLine();
			switch (zorgChoice)
			{
			case 1:
				System.out.println("You have chosen to view patients data");
				if (profileList.sizeOf() > 0)
				{
					profileList.getAllProfile();
					choosePatient();
				} else
				{
					System.out.println("There are currently no patients in the system.");
				}
				staffMenu();
				break;

			case 2:
				try
				{
					System.out.println("YOU HAVE CHOSEN TO ADD A NEW PATIENT");

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
					scanner.nextLine();

					System.out.println("Does this patient use any medication? \n");
					System.out.println("Type 'true' if yes, type 'false' if patient doesn't use medication");
					medicine = scanner.nextBoolean();
					if (medicine == true)
					{
						System.out.println("Enter medicine name: ");
						String medName = scanner.nextLine();
						med.setMedicineName(medName);
						System.out.println("medicine name has been changed to " + medName);
						scanner.nextLine();
						System.out.println("Enter medicine description: ");
						String medDesc = scanner.nextLine();
						med.setDescription(medDesc);
						System.out.println("medicine description has been changed to " + medDesc);

						System.out.println("Enter medicine soort: ");
						String medSoort = scanner.nextLine();
						med.setSoort(medSoort);
						System.out.println("medicine soort has been changed to " + medSoort);

						System.out.println("Enter medicine dosage: ");
						String medDosage = scanner.nextLine();
						med.setDosage(medDosage);
						System.out.println("medicine dosage has been changed to " + medDosage);
					} else
					{
						med.setMedicineName("No medicine");
						med.setDescription("This patient doesn't use any medication");
						med.setSoort("0");
						med.setDosage("0");
					}
					profileList.add(profile, med);
					createMenu();
					break;
				} catch (InputMismatchException e)
				{
					System.out.println(
							"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than what was asked. \n Please try again.");
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
		} catch (InputMismatchException e)
		{
			System.out.println(
					"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \\n Please change this.");
			scanner.nextLine();
			staffMenu();
		}
	}

	private void choosePatient()
	{
		try
		{
			System.out.println("PLEASE CHOOSE WHICH PATIENT YOU WANT TO SEE");
			System.out.println("Type the number in front of the patients info");
			int patientChoice = scanner.nextInt();
			profileList.get(patientChoice);

			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1: Edit    2: Delete    3: Go back");
			int menuChoice = scanner.nextInt();
			scanner.nextLine();
			switch (menuChoice)
			{
			case 1:
				System.out.println("YOU HAVE CHOSEN TO ADD EDIT THIS PATIENT");

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
				scanner.nextLine();

				System.out.println("Does this patient use any medication? \n");
				System.out.println("Type 'true' if yes, type 'false' if patient doesn't use medication");
				medicine = scanner.nextBoolean();
				if (medicine == true)
				{
					System.out.println("Enter medicine name: ");
					String medName = scanner.nextLine();
					med.setMedicineName(medName);
					System.out.println("medicine name has been changed to " + medName);
					scanner.nextLine();
					System.out.println("Enter medicine description: ");
					String medDesc = scanner.nextLine();
					med.setDescription(medDesc);
					System.out.println("medicine description has been changed to " + medDesc);

					System.out.println("Enter medicine soort: ");
					String medSoort = scanner.nextLine();
					med.setSoort(medSoort);
					System.out.println("medicine soort has been changed to " + medSoort);

					System.out.println("Enter medicine dosage: ");
					String medDosage = scanner.nextLine();
					med.setDosage(medDosage);
					System.out.println("medicine dosage has been changed to " + medDosage);
				} else
				{
					med.setMedicineName("No medicine");
					med.setDescription("This patient doesn't use any medication");
					med.setSoort("0");
					med.setDosage("0");
				}
				profileList.edit(patientChoice, profile, med);
				staffMenu();
				break;

			case 2:
				profileList.remove(patientChoice);
				staffMenu();
				break;

			case 3:
				staffMenu();
				break;
			}

		} catch (InputMismatchException e)
		{
			System.out.println(
					"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \\n Please change this.");
			scanner.nextLine();
			createMenu();
		}

	}

	// Needs work
	public void verifyUser()
	{
		System.out.println("TYPE YOUR FIRST NAME");
		String userName = scanner.nextLine();
		String test = userName;
		scanner.nextLine();
		if (userName == test) //but it never is?
		{
			patientMenu();
		} else
		{
			createMenu();
		}

	}
}
