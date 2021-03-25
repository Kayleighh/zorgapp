package zorgapp;

public class Medicine
{
	/**
	 * 
	 */

	private String	medicineName;
	private String	description;
	private String	soort;
	private String	dosage;

	Medicine()
	{

	}

	Medicine(String name, String desc, String soort, String dosage)
	{
		medicineName = name;
		description = desc;
		this.soort = soort;
		this.dosage = dosage;
	}

	public void setMedicineName(String name)
	{
		medicineName = name;
	}

	public String getMedicineName()
	{
		return medicineName;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setSoort(String soort)
	{
		this.soort = soort;
	}

	public String getSoort()
	{
		return soort;
	}

	public void setDosage(String dosage)
	{
		this.dosage = dosage;
	}

	public String getDosage()
	{
		return dosage;
	}

	public String toString()
	{
		return "\nMedication name: " + medicineName + " " + "\nDescription: " + description + "\nSoort: " + soort
				+ "\nDosage: " + dosage;
	}
}
