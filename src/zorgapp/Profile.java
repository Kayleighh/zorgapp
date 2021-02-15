package zorgapp;

import java.util.Scanner;

public class Profile {

	private String FirstName;
	private String LastName;
	private int age;
	private double length;
	private double weight;
	
	private Scanner scanner = new Scanner(System.in);
	

	Profile()
	{

	}
	
	
	//functie om de voornaam te wijzigen. Void betekent dat de functie geen specifieke date terugstuurd. Wel print deze functie "your first name has been changed"
	public void setFirstName() 
	{
		System.out.println("Enter your first name here: ");
		String firstname = getFirstName();
		firstname = scanner.next();
		this.FirstName = firstname;
		System.out.println("Your first has been changed. ");
		
	}
	
	//functie om de naam op te halen. Functie stuurt data met het type String terug.
	public String getFirstName()
	{
		return FirstName;
	}
	
	//functie om de achternaam te wijzigen. Vrijwel hetzelfde als setFirstName()
	public void setLastName()
	{
		System.out.println("Enter your last name here: ");
		String lastname = getLastName();
		lastname = scanner.next();
		this.LastName = lastname;
		System.out.println("Your last name has been changed. ");
	}
	
	public String getLastName()
	{
		return LastName;
	}
	
	//functie om de leeftijd aan te passsen. Hier wordt een andere functie van de scanner aangeroepen, namelijk nextInt()
	//Het gaat hier namelijk om het datatype int en niet om een String.
	public void setAge()
	{
		System.out.println("Enter your age here: ");
		int age = getAge();
		age = scanner.nextInt();
		this.age = age;
		System.out.println("Your age has been changed. ");
		
	}
	
	public int getAge()
	{
		return age;
	}
	
	//functie om de lengte aan te passen.
	//Er wordt een andere functie van scanner aangeroepen, omdat het hier om een double gaat.
	public void setLength()
	{
		System.out.println("Enter your length in meters here: ");
		double length = getLength();
		length = scanner.nextDouble();
		this.length = length;
		System.out.println("Your length has been changed. ");
		
	}
	
	public double getLength()
	{
		return length;
	}
	
	//functie om het gewicht aan te passen.
	//vrijwel hetzelfde als setLength()
	public void setWeight()
	{
		System.out.println("Enter your weight here: ");
		double weight = getWeight();
		weight = scanner.nextDouble();
		this.weight = weight;
		System.out.println("Your weight has been changed. ");
		
	}
	
	public double getWeight() {
		return weight;
	}
	
	
	//functie om de bmi uit te rekenen
	public String getBmi() {
		double bmi = weight/(length*length);
		return "Your BMI is "+ bmi;
	}
	
	
	

}
