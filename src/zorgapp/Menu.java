package zorgapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu
{
	private ProfileList		profileList	= new ProfileList();
	private Scanner			scanner		= new Scanner(System.in);
	private Profile			profile		= new Profile();
	private DatabaseConnect	db			= new DatabaseConnect();
	Menu()
	{

	}

	public void createMenu()
	{
		try
		{
			System.out.println("MAKE A CHOICE");
			System.out.println("1: Health care provider  2: Patient   3: Close program   4:Switch Language");

			int choice = scanner.nextInt();
			if (choice == 1)
			{
				staffMenu();
			} else if (choice == 2)
			{
				patientMenu();
			} else if (choice == 3)
			{
				System.out.println("Goodbye!");
			} else if (choice == 4)
			{	
				
				System.out.println("CHOOSE LANGUAGE");
				System.out.println("1:English   2:Dutch");
				int languageChoice = scanner.nextInt();
				if(languageChoice == 2)
				{
					createDutchMenu();
				}
				else {
					createMenu();
				}
			}
			else
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
		System.out.println("1:Log in   2:Go back ");

		int patChoice = scanner.nextInt();
		scanner.nextLine();
		switch (patChoice)
		{
		case 1:
			System.out.println("Enter id to get your data ");
			int patId = scanner.nextInt();
			db.verify(patId);
			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1:Edit   2:Go back ");
			int patientChoice = scanner.nextInt();
			scanner.nextLine();
			switch (patientChoice)
			{
			case 1:
				System.out.println("YOU HAVE CHOSEN TO EDIT YOUR DATA");
				System.out.println("Enter first name here: ");
				String firstname = scanner.nextLine();
				profile.setFirstName(firstname);
				System.out.println("First name has been changed to " + firstname);
				db.update(patId, firstname);
				patientMenu();
				break;
			case 2:
				patientMenu();
			}
			break;
		case 2:
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
					db.getAllProfiles();
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

					System.out.println(
							"Does patient take medication? \nType 'true' if they do, type 'false' if they don't");
					boolean takesMed = scanner.nextBoolean();
					if (takesMed == false)
					{
						db.addPatient(profile);
						int medType = 1002;
						String dosage = "0";
						db.addMedToPatient(dosage, medType);
						createMenu();
					} else if (takesMed == true)
					{
						db.addPatient(profile);
						db.getAllMedicine();
						System.out.println("Type the id in front of the medicine name to add this medicine to patient");
						int medType = scanner.nextInt();
						System.out.println(
								"Do you want to change the dosage? \nType 'true' if you want to change the dosage, type 'false' if you want to keep this dosage");
						boolean dosageType = scanner.nextBoolean();
						if (dosageType == true)
						{
							System.out.println("Enter dosage: ");
							scanner.nextLine();
							String dosage = scanner.nextLine();
							db.addMedToPatient(dosage, medType);
							staffMenu();
						} else if (dosageType == false)
						{
							String dosage = "0";
							db.addMedToPatient(dosage, medType);
							createMenu();
						} else
						{
							System.out.println("Nothing has been selected");
							staffMenu();
						}

					} else
					{
						System.out.println("Nothing has been selected");
						createMenu();
					}

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
			db.getProfile(patientChoice);
			System.out.println("PLEASE CHOOSE WHAT YOU WANT TO DO");
			
			System.out.println("1: Add weight point 2: View bmi  3:Edit  4: Go back");
			int addChoice = scanner.nextInt();
			if (addChoice == 1)
			{
				System.out.println("Enter weigth here: ");
				double weight = scanner.nextDouble();
				profile.setWeight(weight);
				System.out.println("Weight has been changed to " + weight);
				db.addWeight(patientChoice, weight);
			} else if (addChoice == 2)
			{
				db.calculateBmi(patientChoice);
			}

			else if (addChoice == 3)
			{
				System.out.println("WHAT DO YOU WANT TO EDIT");
				System.out.println("1:Firstname   2:Lastname  3:Age   4:Length");
				int editChoice = scanner.nextInt();
				if(editChoice == 1)
				{
					System.out.println("ENTER NEW FIRST NAME");
					scanner.nextLine();
					String newName = scanner.nextLine();
					db.update(patientChoice, newName);
				}
				else if(editChoice == 2)
				{
					System.out.println("ENTER NEW LAST NAME");
					scanner.nextLine();
					String newName = scanner.nextLine();
					db.updateLastName(patientChoice, newName);
				}
				else if (editChoice == 3)
				{
					System.out.println("ENTER NEW AGE");
					scanner.nextLine();
					int newAge = scanner.nextInt();
					db.updateAge(patientChoice, newAge);
				}
				else if(editChoice == 4)
				{
					System.out.println("ENTER NEW LENGTH");
					scanner.nextLine();
					double newLength = scanner.nextDouble();
					db.updateLength(patientChoice, newLength);
				}
				else
				{
					staffMenu();
				}
			}
			else if(addChoice == 4)
			{
				staffMenu();
			}
			
			else
			{
				System.out.println("Nothing has been selected.");
				staffMenu();
			}
		} catch (InputMismatchException e)
		{
			System.out.println(
					"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than a number. \\n Please change this.");
			scanner.nextLine();
			createMenu();
		}

	}
	
	//DUTCH MENU
	public void createDutchMenu()
	{
		try
		{
			System.out.println("MAAK EEN KEUZE");
			System.out.println("1: Zorgverlener  2: Patient   3: Sluit programma   4:Verander taal");

			int choice = scanner.nextInt();
			if (choice == 1)
			{
				staffMenu();
			} else if (choice == 2)
			{
				patientMenu();
			} else if (choice == 3)
			{
				System.out.println("Doei!");
			} else if (choice == 4)
			{
				System.out.println("KIES EEN TAAL");
				System.out.println("1:Engels   2:Nederlands");
				int languageChoice = scanner.nextInt();
				if(languageChoice == 2)
				{
					createDutchMenu();
				}else {
					createMenu();
				}
			}
			else
			{
				System.out.println("ER IS NIKS GEKOZEN");
				createDutchMenu();
			}
		} catch (InputMismatchException e)
		{
			System.out.println(
					"Dit is wat er mis gegaan is: \n U heeft waarschijnlijk iets anders ingevuld dan een cijfer. \n Vul s.v.p een nummer in.");
			scanner.nextLine();
			createMenu();
		}
	}
	
	public void dutchPatientMenu()
	{
		System.out.println("WAT WILT U DOEN");
		System.out.println("1:Inloggen   2:Terug ");

		int patChoice = scanner.nextInt();
		scanner.nextLine();
		switch (patChoice)
		{
		case 1:
			System.out.println("Toets uw id in om uw gegevens op te halen ");
			int patId = scanner.nextInt();
			db.verify(patId);
			System.out.println("WAT WILT U DOEN");
			System.out.println("1:Gegevens veranderen   2:Terug ");
			int patientChoice = scanner.nextInt();
			scanner.nextLine();
			switch (patientChoice)
			{
			case 1:
				System.out.println("U HEEFT GEKOZEN OM UW GEGEVENS TE VERANDEREN");
				System.out.println("Toets uw voornaam in ");
				String firstname = scanner.nextLine();
				profile.setFirstName(firstname);
				System.out.println("Uw voornaam is verandert naar " + firstname);
				db.update(patId, firstname);
				dutchPatientMenu();
				break;
			case 2:
				dutchPatientMenu();
			}
			break;
		case 2:
			createDutchMenu();
			break;
		default:
			System.out.println("ER IS NIKS GESELECTEERD");
			dutchPatientMenu();
			break;
		}
	}
	
	private void dutchStaffMenu()
	{
		try
		{
			System.out.println("WAT WILT U DOEN");
			System.out.println("1:Data bekijken  2:Nieuw patient toevoegen  3:Terug ");

			int zorgChoice = scanner.nextInt();
			scanner.nextLine();
			switch (zorgChoice)
			{
			case 1:
				System.out.println("U heeft gekozen om uw patienten te bekijken.");
				if (profileList.sizeOf() > 0)
				{
					db.getAllProfiles();
					dutchChoosePatient();
				} else
				{
					System.out.println("Er zijn momenteel geen patienten bekend in het systeem.");
				}
				staffMenu();
				break;

			case 2:
				try
				{
					System.out.println("U HEEFT GEKOZEN OM EEN NIEUW PATIENT TOE TE VOEGEN");

					System.out.println("Toets de voornaam in: ");
					String firstname = scanner.nextLine();
					profile.setFirstName(firstname);
					System.out.println("Voornaam is verandert naar " + firstname);

					System.out.println("Toets de achternaam in: ");
					String lastname = scanner.nextLine();
					profile.setLastName(lastname);
					System.out.println("Achternaam is verandert naar " + lastname);

					System.out.println("Toets de leeftijd in: ");
					int age = scanner.nextInt();
					profile.setAge(age);
					System.out.println("De leeftijd in verandert naar " + age);

					System.out.println("Toets de lengte in meters in: ");
					double length = scanner.nextDouble();
					profile.setLength(length);
					System.out.println("De lengte is verandert naar " + length);

					System.out.println(
							"Gebruikt de patient medicatie? \nType 'true' als dat zo is, type 'false' als dit niet het geval is.");
					boolean takesMed = scanner.nextBoolean();
					if (takesMed == false)
					{
						db.addPatient(profile);
						int medType = 1002;
						String dosage = "0";
						db.addMedToPatient(dosage, medType);
						dutchStaffMenu();
					} else if (takesMed == true)
					{
						db.addPatient(profile);
						db.getAllMedicine();
						System.out.println("Type de id voor de medicijn naam in");
						int medType = scanner.nextInt();
						System.out.println(
								"Wilt u de dosering aanpassen? \nType 'true' als u de dosering wilt aanpassen, type 'false' als u de huidige dosering wilt behouden.");
						boolean dosageType = scanner.nextBoolean();
						if (dosageType == true)
						{
							System.out.println("Toets de dosering in: ");
							scanner.nextLine();
							String dosage = scanner.nextLine();
							db.addMedToPatient(dosage, medType);
							dutchStaffMenu();
						} else if (dosageType == false)
						{
							String dosage = "0";
							db.addMedToPatient(dosage, medType);
							dutchStaffMenu();
						} else
						{
							System.out.println("ER IS NIKS GESELECTEERD");
							dutchStaffMenu();
						}

					} else
					{
						System.out.println("ER IS NIKS GESELECTEERD");
						dutchStaffMenu();
					}

					break;
				} catch (InputMismatchException e)
				{
					System.out.println(
							"Dit is wat er mis gegaan is: \n U heeft waarschijnlijk iets anders ingevuld dan een cijfer. \n Vul s.v.p een nummer in.");
					scanner.nextLine();
				}
			case 3:
				createDutchMenu();
				break;
			default:
				System.out.println("ER IS NIKS GESELECTEERD");
				createDutchMenu();
				break;
			}
		} catch (InputMismatchException e)
		{
			System.out.println(
					"Dit is wat er mis gegaan is: \n U heeft waarschijnlijk iets anders ingevuld dan een cijfer. \n Vul s.v.p een nummer in.");
			scanner.nextLine();
			dutchStaffMenu();
		}
	}
	private void dutchChoosePatient()
	{
		try
		{
			System.out.println("KIES WELKE PATIENT U WILT ZIEN");
			System.out.println("Type het id voor de patient naam in a.u.b.");
			int patientChoice = scanner.nextInt();
			db.getProfile(patientChoice);
			System.out.println("WAT WILT U DOEN");
			System.out.println("1: Voeg een gewichtspunt toe 2: Bekijk bmi  3:Wijzig gegevens  4: Terug");
			int addChoice = scanner.nextInt();
			if (addChoice == 1)
			{
				System.out.println("Vul het gewicht in ");
				double weight = scanner.nextDouble();
				profile.setWeight(weight);
				System.out.println("Het gewicht is verandert naar " + weight);
				db.addWeight(patientChoice, weight);
			} else if (addChoice == 2)
			{
				db.calculateBmi(patientChoice);
			}

			else if (addChoice == 3)
			{
				System.out.println("WAT WILT U VERANDEREN");
				System.out.println("1:Voornaam   2:Achternaam  3:Leeftijd   4:Lengte");
				int editChoice = scanner.nextInt();
				if(editChoice == 1)
				{
					System.out.println("VUL DE VOORNAAM IN ");
					scanner.nextLine();
					String newName = scanner.nextLine();
					db.update(patientChoice, newName);
				}
				else if(editChoice == 2)
				{
					System.out.println("VUL DE ACHTERNAAM IN ");
					scanner.nextLine();
					String newName = scanner.nextLine();
					db.updateLastName(patientChoice, newName);
				}
				else if (editChoice == 3)
				{
					System.out.println("VUL DE LEEFTIJD IN");
					scanner.nextLine();
					int newAge = scanner.nextInt();
					db.updateAge(patientChoice, newAge);
				}
				else if(editChoice == 4)
				{
					System.out.println("VUL DE LENGTE IN");
					scanner.nextLine();
					double newLength = scanner.nextDouble();
					db.updateLength(patientChoice, newLength);
				}
				else
				{
					dutchStaffMenu();
				}
			}
			else if(addChoice == 4)
			{
				dutchStaffMenu();
			}
			
			else
			{
				System.out.println("ER IS NIKS GESELECTEERD");
				dutchStaffMenu();
			}
		} catch (InputMismatchException e)
		{
			System.out.println(
					"Dit is wat er mis gegaan is: \\n U heeft waarschijnlijk iets anders ingevuld dan een cijfer. \\n Vul s.v.p een nummer in.");
			scanner.nextLine();
			createDutchMenu();
		}

	}

}
