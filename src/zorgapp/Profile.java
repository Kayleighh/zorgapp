package zorgapp;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Profile
{

	/**
	 * 
	 */

	private String	FirstName;
	private String	LastName;
	private int		age;
	private double	length;
	private double	weight;

	Profile(String firstname, String lastname, int age, double length, double weight)
	{
		this.FirstName = firstname;
		this.LastName = lastname;
		this.age = age;
		this.length = length;
		this.weight = weight;
	}

	Profile()
	{
	}

	public void setFirstName(String firstname)
	{

		this.FirstName = firstname;
	}

	public String getFirstName()
	{
		return FirstName;
	}

	public void setLastName(String lastName)
	{
		this.LastName = lastName;
	}

	public String getLastName()
	{
		return LastName;
	}

	public void setAge(int age)
	{
		this.age = age;

	}

	public int getAge()
	{
		return age;
	}

	public void setLength(Double length)
	{
		this.length = length;

	}

	public double getLength()
	{
		return length;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;

	}

	public double getWeight()
	{
		return weight;
	}

	public String getBmi()
	{
		double bmi = weight / (length * length);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		return "Your BMI is " + df.format(bmi);
	}

	public String toString()
	{

		return "Name:" + FirstName + " " + LastName + " " + "\nAge: " + age + "\nlength: " + length + "\nWeight: "
				+ weight + "\nBmi:" + getBmi();
	}

	

}
