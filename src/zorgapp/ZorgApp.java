package zorgapp;

import java.util.*;
public class ZorgApp {
	
	private ProfileList profileList = new ProfileList();
	private Scanner scanner = new Scanner(System.in);
	private Profile prof = new Profile();
	
	ZorgApp() 
	{
		
	}

	public void menu()
	{
	    int choice = 1;
			System.out.println("MAKE A CHOICE");
			System.out.println("1: Zorgverlener  2: Patient");
			
			choice = scanner.nextInt();
		
			switch(choice)
			{
				case 0:
					System.out.println("Stop");
					break;
					
				case 1:
					System.out.println("WHAT DO YOU WANT TO DO?");
					System.out.println("1:View data  2:Edit data ");
					
					int zorgChoice = 1;
					
						zorgChoice = scanner.nextInt();
						switch(zorgChoice)
						{
						case 1:
							profileList.get(0);
							menu();
							break;
						case 2:
							prof.setFirstName();
							prof.setLastName();
							prof.setAge();
							prof.setLength();
							prof.setWeight();
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
					switch(patChoice)
					{
					case 1:
						profileList.get(0);
						menu();
						break;
					case 2:
						prof.setFirstName();
						prof.setLastName();
						prof.setAge();
						prof.setLength();
						prof.setWeight();
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

