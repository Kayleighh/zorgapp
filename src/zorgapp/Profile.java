package zorgapp;



public class Profile {

	private String FirstName;
	private String LastName;
	private int age;
	private double length;
	private double weight;

	Profile() {

	}

	public void setFirstName(String firstname) {
		
		this.FirstName = firstname;
	}


	public String getFirstName() {
		return FirstName;
	}


	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setAge(int age) {
		this.age = age;

	}

	public int getAge() {
		return age;
	}


	public void setLength(Double length) {
		this.length = length;

	}

	public double getLength() {
		return length;
	}


	public void setWeight(double weight) {
		this.weight = weight;

	}

	public double getWeight() {
		return weight;
	}

	// functie om de bmi uit te rekenen
	public String getBmi() {
		double bmi = weight / (length * length);
		return "Your BMI is " + bmi;
	}

}
