package zorgapp;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * 
 * In deze klasse wordt het meeste gedaan. Hier wordt de menu gemaakt waar alle keuzes voor het programma staan.
 */
public class Menu
{
	private Scanner			scanner		= new Scanner(System.in);
	private Profile			profile		= new Profile();
	private Medicine		medicine	= new Medicine();
	private DatabaseConnect	db			= new DatabaseConnect();
	private Language		lan			= new Language();
	private TimedExit		timedExit	= new TimedExit();

	public Menu()
	{

	}

	// Maakt het hoofdmenu
	public void createMenu()
	{
		try
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println("MAKE A CHOICE");
				System.out.println("1: Health care provider  2: Patient   3: Close program   4:Switch Language");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("MAAK EEN KEUZE");
				System.out.println("1: Zorgverlener  2: Patient   3: Sluit programma  4:Verander taal");
			}

			int choice = scanner.nextInt();
			if (choice == 1)
			{
				staffMenu();
			} else if (choice == 2)
			{
				patientMenu();
			} else if (choice == 3)
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("Goodbye!");
					timedExit.timedExit();
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Doei!");
					timedExit.timedExit();
				}

			} else if (choice == 4)
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("CHOOSE LANGUAGE");
					System.out.println("1:English   2:Dutch");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("KIES EEN TAAL");
					System.out.println("1:Engels   2:Nederlands");
				}

				int languageChoice = scanner.nextInt();
				if (languageChoice == 2)
				{
					lan.setLanguage(2);
					db.whatLanguage(2);
					createMenu();
				} else
				{
					lan.setLanguage(1);
					db.whatLanguage(1);
					createMenu();
				}
			} else
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("NOTHING HAS BEEN SELECTED");
					createMenu();
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("ER IS NIKS GESELECTEERD");
					createMenu();
				}

			}
		} catch (InputMismatchException e)
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println(
						"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than what was asked. \n Please change this.");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println(
						"DIT IS WAT ER WAARSCHIJNLIJK VERKEERD GING: \n WAARSCHIJNLIJK IS ER IETS ANDERS INGEVULD DAN WERD GEVRAAGD \n Probeer opnieuw");
			}

			scanner.nextLine();
			createMenu();
		}
	}

	// Maakt het menu voor patienten. In case 1 wordt de patient gevraagd om in te
	// loggen.
	private void patientMenu()
	{
		if (lan.getLanguage() == "en")
		{
			System.out.println("WHAT DO YOU WANT TO DO?");
			System.out.println("1:Log in   2:Go back ");
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("WAT WILT U DOEN?");
			System.out.println("1:Log in   2:Terug ");
		}

		int patChoice = scanner.nextInt();
		scanner.nextLine();
		switch (patChoice)
		{
		case 1:
			if (lan.getLanguage() == "en")
			{
				System.out.println("Enter id to get your data. ");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("Voer het id in om de data op te halen. ");
			}

			int patId = scanner.nextInt();
			db.verify(patId);
			if (lan.getLanguage() == "en")
			{
				System.out.println("WHAT DO YOU WANT TO DO?");
				System.out.println("1:Edit   2:Go back ");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("WAT WILT U DOEN?");
				System.out.println("1:Gegevens veranderen   2:Terug ");
			}

			int patientChoice = scanner.nextInt();
			scanner.nextLine();
			switch (patientChoice)
			{
			case 1:
				if (lan.getLanguage() == "en")
				{
					System.out.println("YOU HAVE CHOSEN TO EDIT YOUR DATA.");
					System.out.println("Enter first name here: ");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("U HEEFT GEKOZEN OM UW GEGEVENS TE VERANDEREN.");
					System.out.println("Voer uw voornaam in: ");
				}

				String firstname = scanner.nextLine();
				profile.setFirstName(firstname);
				if (lan.getLanguage() == "en")
				{
					System.out.println("First name has been changed to " + firstname);
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Uw voornaam is veranderd naar " + firstname);
				}

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
			if (lan.getLanguage() == "en")
			{
				System.out.println("NOTHING HAS BEEN SELECTED");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("ER IS NIKS GESELECTEERD");
			}

			patientMenu();
			break;
		}
	}

	// Maakt het menu voor zorgverleners. Zorgverleners hoefen niet in te loggen.
	private void staffMenu()
	{
		try
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println("WHAT DO YOU WANT TO DO?");
				System.out.println("1:View data  2:Add a new patient  3:Go back ");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("WAT WILT U DOEN.");
				System.out.println("1:Gegevens bekijken  2:Nieuwe patient toevoegen  3:Terug ");
			}

			int zorgChoice = scanner.nextInt();
			scanner.nextLine();
			switch (zorgChoice)
			{
			case 1:
				if (lan.getLanguage() == "en")
				{
					System.out.println("You have chosen to view patients data");
				}

				else if (lan.getLanguage() == "nl")
				{
					System.out.println("U heeft gekozen om gegevens in te kijken.");
				}

				db.getAllProfiles();
				choosePatient();

				staffMenu();
				break;

			case 2:
				try
				{
					if (lan.getLanguage() == "en")
					{
						System.out.println("YOU HAVE CHOSEN TO ADD A NEW PATIENT");
					} else if (lan.getLanguage() == "nl")
					{
						System.out.println("U HEEFT GEKOZEN OM EEN NIEUWE PATIENT TOE TE VOEGEN");
					}

					addPatient();

					break;
				} catch (InputMismatchException e)
				{
					if (lan.getLanguage() == "en")
					{
						System.out.println(
								"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than what was asked. \n Please change this.");
					} else if (lan.getLanguage() == "nl")
					{
						System.out.println(
								"DIT IS WAT ER WAARSCHIJNLIJK VERKEERD GING: \n WAARSCHIJNLIJK IS ER IETS ANDERS INGEVULD DAN WERD GEVRAAGD \n Probeer opnieuw");
					}
					scanner.nextLine();
				}
			case 3:
				createMenu();
				break;
			default:
				if (lan.getLanguage() == "en")
				{
					System.out.println("Nothing has been selected");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Er is niks geselecteerd");
				}
				createMenu();
				break;
			}
		} catch (

		InputMismatchException e)
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println(
						"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than what was asked. \n Please change this.");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println(
						"DIT IS WAT ER WAARSCHIJNLIJK VERKEERD GING: \n WAARSCHIJNLIJK IS ER IETS ANDERS INGEVULD DAN WERD GEVRAAGD \n Probeer opnieuw");
			}
			scanner.nextLine();
			staffMenu();
		}
	}

	// Deze methode laat het menu zien om een specifiek profiel op te halen.
	// Daarnaast laat het alle opties zien die gedaan kunnen worden voor dit profiel
	private void choosePatient()
	{
		try
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println("PLEASE CHOOSE WHICH PATIENT YOU WANT TO SEE");
				System.out.println("Type the number in front of the patients info");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("KIES WELKE PATIENT U DE GEGEVENS VAN WILT INZIEN");
				System.out.println("Type het nummer in wat voor de patient staat.");
			}
			int patientChoice = scanner.nextInt();
			db.getProfile(patientChoice);
			if (lan.getLanguage() == "en")
			{
				System.out.println("PLEASE CHOOSE WHAT YOU WANT TO DO");

				System.out.println("1: Add weight point 2: View bmi  3:Edit  4: Go back");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("WAT WILT U DOEN");

				System.out.println("1: Gewichtpunt toevoegen 2: Bmi bekijken  3:Gegevens veranderen  4: Terug");
			}

			int addChoice = scanner.nextInt();
			if (addChoice == 1)
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("Enter weigth here: ");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Voer het gewicht in: ");
				}

				double weight = scanner.nextDouble();
				profile.setWeight(weight);
				if (lan.getLanguage() == "en")
				{
					System.out.println("Weight has been changed to " + weight);
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Het gewicht is aangepast naar " + weight);
				}

				db.addWeight(patientChoice, weight);
			} else if (addChoice == 2)
			{
				db.calculateBmi(patientChoice);
			}

			else if (addChoice == 3)
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("WHAT DO YOU WANT TO EDIT");
					System.out.println("1:Firstname   2:Lastname  3:Age   4:Length");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("WELKE GEGEVENS WILT U AANPASSEN");
					System.out.println("1:Voornaam   2:Achternaam  3:Leeftijd   4:Lengte");
				}

				int editChoice = scanner.nextInt();
				if (editChoice == 1)
				{
					if (lan.getLanguage() == "en")
					{
						System.out.println("ENTER NEW FIRST NAME");
					} else if (lan.getLanguage() == "nl")
					{
						System.out.println("VOER DE VOORNAAM IN");
					}

					scanner.nextLine();
					String newName = scanner.nextLine();
					profile.setFirstName(newName);
					db.update(patientChoice, newName);
				} else if (editChoice == 2)
				{
					if (lan.getLanguage() == "en")
					{
						System.out.println("ENTER NEW LAST NAME");
					} else if (lan.getLanguage() == "nl")
					{
						System.out.println("VOER DE ACHERNAAM IN");
					}
					scanner.nextLine();
					String newName = scanner.nextLine();
					profile.setLastName(newName);
					db.updateLastName(patientChoice, newName);
				} else if (editChoice == 3)
				{
					if (lan.getLanguage() == "en")
					{
						System.out.println("ENTER NEW AGE");
					} else if (lan.getLanguage() == "nl")
					{
						System.out.println("VOER DE LEEFTIJD IN");
					}
					scanner.nextLine();
					int newAge = scanner.nextInt();
					profile.setAge(newAge);
					db.updateAge(patientChoice, newAge);
				} else if (editChoice == 4)
				{
					if (lan.getLanguage() == "en")
					{
						System.out.println("ENTER NEW LENGTH");
					} else if (lan.getLanguage() == "nl")
					{
						System.out.println("VOER DE LENGTE IN");
					}
					scanner.nextLine();
					double newLength = scanner.nextDouble();
					profile.setLength(newLength);
					db.updateLength(patientChoice, newLength);
				} else
				{
					staffMenu();
				}
			} else if (addChoice == 4)
			{
				staffMenu();
			}

			else
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("Nothing has been selected");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Er is niks geselecteerd");
				}
				staffMenu();
			}
		} catch (InputMismatchException e)
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println(
						"HERE IS WHAT MIGHT HAVE GONE WRONG: \n You may have entered something other than what was asked. \n Please change this.");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println(
						"DIT IS WAT ER WAARSCHIJNLIJK VERKEERD GING: \n WAARSCHIJNLIJK IS ER IETS ANDERS INGEVULD DAN WERD GEVRAAGD \n Probeer opnieuw");
			}
			scanner.nextLine();
			createMenu();
		}

	}

	// Deze methode laat alle informatie zien om een patient goed aan te maken.
	private void addPatient()
	{
		if (lan.getLanguage() == "en")
		{
			System.out.println("Enter first name here: ");
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Voer hier de voornaam in: ");
		}
		String firstname = scanner.nextLine();
		profile.setFirstName(firstname);
		if (lan.getLanguage() == "en")
		{
			System.out.println("First name has been changed to " + firstname);
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Voornaam is verandert naar " + firstname);
		}

		if (lan.getLanguage() == "en")
		{
			System.out.println("Enter last name here: ");
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Voer hier de achternaam in: ");
		}

		String lastname = scanner.nextLine();
		profile.setLastName(lastname);
		if (lan.getLanguage() == "en")
		{
			System.out.println("Last name has been changed to " + lastname);
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Achternaam is verandert naar " + lastname);
		}

		if (lan.getLanguage() == "en")
		{
			System.out.println("Enter age here: ");
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Voer hier de leeftijd in: ");
		}

		int age = scanner.nextInt();
		profile.setAge(age);
		if (lan.getLanguage() == "en")
		{
			System.out.println("Age has been changed to " + age);
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Leeftijd is verandert naar " + age);
		}

		if (lan.getLanguage() == "en")
		{
			System.out.println("Enter length here: ");
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Voer hier de lengte in: ");
		}

		double length = scanner.nextDouble();
		profile.setLength(length);
		if (lan.getLanguage() == "en")
		{
			System.out.println("Length has been changed to " + length);
			System.out.println("Does patient take medication? \nType 'true' if they do, type 'false' if they don't");
		} else if (lan.getLanguage() == "nl")
		{
			System.out.println("Lengte is verandert naar " + length);
			System.out.println(
					"Gebruikt deze patiënt medicatie? \nType 'true' als de patiënt medicatie neemt, type 'false' als dit niet het geval is.");
		}

		boolean takesMed = scanner.nextBoolean();
		if (takesMed == false)
		{
			db.addPatient(profile);
			int medType = 1002;
			String dosage = "0";
			medicine.setDosage(dosage);
			db.addMedToPatient(dosage, medType);
			createMenu();
		} else if (takesMed == true)
		{
			db.addPatient(profile);
			db.getAllMedicine();
			if (lan.getLanguage() == "en")
			{
				System.out.println("Type the id in front of the medicine name to add this medicine to patient");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println(
						"Type het id wat voor de medicijn naam staat om dit medicijn toe te voegen aan de patiënt");
			}

			int medType = scanner.nextInt();
			if (lan.getLanguage() == "en")
			{
				System.out.println(
						"Do you want to change the dosage? \nType 'true' if you want to change the dosage, type 'false' if you want to keep this dosage");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println(
						"Wilt u de dosering aanpassen? \nType 'true' als u de dosering wil aanpassen, type 'false' als u de huidige dosering wilt houden.");
			}

			boolean dosageType = scanner.nextBoolean();
			if (dosageType == true)
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("Enter dosage: ");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Voer de dosering in ");
				}

				scanner.nextLine();
				String dosage = scanner.nextLine();
				medicine.setDosage(dosage);
				db.addMedToPatient(dosage, medType);
				staffMenu();
			} else if (dosageType == false)
			{
				String dosage = "0";
				medicine.setDosage(dosage);
				db.addMedToPatient(dosage, medType);
				createMenu();
			} else
			{
				if (lan.getLanguage() == "en")
				{
					System.out.println("Nothing has been selected");
				} else if (lan.getLanguage() == "nl")
				{
					System.out.println("Er is niks geselecteerd");
				}

				staffMenu();
			}

		} else
		{
			if (lan.getLanguage() == "en")
			{
				System.out.println("Nothing has been selected");
			} else if (lan.getLanguage() == "nl")
			{
				System.out.println("Er is niks geselecteerd");
			}
			createMenu();
		}

	}
}
