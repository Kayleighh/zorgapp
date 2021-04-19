package zorgapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;

public class DatabaseConnect
{
	private String		url			= "jdbc:sqlserver://kayleigh\\testserver;databaseName=ZorgApp";
	private String		username	= "ZorgAppUser";
	private String		password	= "Z0rgApp!";
	private Connection	connection;
	private String		lan			= "en";

	public DatabaseConnect()
	{

	}

	public void whatLanguage(int lan)
	{
		if (lan == 1)
		{
			this.lan = "en";
		} else if (lan == 2)
		{
			this.lan = "nl";
		}
	}

	// Methode om de database connectie te leggen. De methode is private omdat deze alleen in de huidige klasse wordt gebruikt.
	private void connect()
	{
		try
		{
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	// Deze methode voegt een patient toe aan de database
	public void addPatient(Profile profile)
	{
		try
		{
			connect();
			String sql = "INSERT INTO Patient VALUES(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			String firstName = profile.getFirstName();
			String lastName = profile.getLastName();
			int age = profile.getAge();
			double length = profile.getLength();
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setInt(3, age);
			statement.setDouble(4, length);
			int row = statement.executeUpdate();
			if (row > 0)
			{
				if (lan == "en")
				{
					System.out.println("Patient added");
				} else if (lan == "nl")
				{
					System.out.println("Patiënt toegevoegd");
				}

			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	// Deze methode haalt alle patient profilen uit de database. Alleen het id, de
	// voornaam en de achternaam worden getoond.
	public void getAllProfiles()
	{
		try
		{
			connect();
			String sql = "SELECT id,firstname,lastname FROM patient";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next())
			{
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				System.out.println(id + " " + firstname + " " + lastname);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Deze methode haalt alle medicijnen uit de database. Alle informatie wordt
	// laten zien.
	public void getAllMedicine()
	{
		try
		{
			String sql = "SELECT * FROM Medicine";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String type = rs.getString("soort");
				String dosage = rs.getString("dosage");
				System.out.println(id + " " + name + " " + description + " " + type + " " + dosage);
			}
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	// Deze methode haalt een specifiek profiel op aan de hand van het id. De
	// tabellen Patient en Medicine worden gejoind, zodat alle informatie kan worden
	// getoond.
	public void getProfile(int index)
	{
		try
		{
			connect();
			String sql = "SELECT * FROM Patient";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next())
			{
				if (index == rs.getInt("id"))
				{
					String sql1 = "SELECT * FROM patientmedicine JOIN patient ON patient.id = patientId JOIN Medicine ON medicine.id = medicineId WHERE patient.id = ?";
					PreparedStatement statement1 = connection.prepareStatement(sql1);
					int id = rs.getInt("id");
					statement1.setInt(1, id);
					ResultSet rs1 = statement1.executeQuery();
					while (rs1.next())
					{
						String firstname = rs.getString("firstname");
						String lastname = rs.getString("lastname");
						int age = rs.getInt("age");
						double length = rs.getDouble("length");
						if (lan == "en")
						{
							System.out.println("First name: " + firstname + "\nLastname: " + lastname + "\nAge: " + age
									+ "\nLength: " + length);
						} else if (lan == "nl")
						{
							System.out.println("Voornaam: " + firstname + "\nAchternaam: " + lastname + "\nLeeftijd: "
									+ age + "\nLengte: " + length);
						}

						String medName = rs1.getString("name");
						String medDesc = rs1.getString("description");
						String type = rs1.getString("soort");
						String dosage = rs1.getString("dosage");
						if (lan == "en")
						{
							System.out.println(
									"The following medication is in our system:\n" + "Medicine name: " + medName
											+ "\nDescription: " + medDesc + "\nType: " + type + "\nDosage: " + dosage);
						} else if (lan == "nl")
						{
							System.out.println("De volgende medijcijnen staan in het systeem: \n" + "Medicine naam: "
									+ medName + "\nBeschrijving: " + medDesc + "\nSoort: " + type + "\nDosering: "
									+ dosage);
						}

						getWeight(index);

					}
				}
			}
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	// Deze methode verifieerd de patient aan de hand van het meegestuurde id.
	public void verify(int index)
	{
		try
		{
			connect();
			String sql = "SELECT * FROM patient WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
			{
				getProfile(index);
			} else
			{

				System.out.println("nope");
			}
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	// Deze methode update de voornaam van een specifiek profiel. De id en voornaam
	// worden vanuit het Menu meegestuurd.
	public void update(int index, String name)
	{
		try
		{
			connect();
			String sql = "SELECT id FROM Patient WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = index;
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				String sql1 = "UPDATE Patient SET firstname = ? WHERE id = ?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				String updatedName = name;
				statement1.setInt(2, id);
				statement1.setString(1, updatedName);
				int row = statement1.executeUpdate();
				if (row > 0)
				{
					if (lan == "en")
					{
						System.out.println("Firstname has been updated");
					} else if (lan == "nl")
					{
						System.out.println("Voornaam is veranderd");
					}

				}
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	// Deze methode update de achernaam
	public void updateLastName(int index, String lastname)
	{

		try
		{
			connect();
			String sql = "SELECT id FROM Patient WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = index;
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				String sql1 = "UPDATE Patient SET lastname = ? WHERE id = ?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				String updatedName = lastname;
				statement1.setInt(2, id);
				statement1.setString(1, updatedName);
				int row = statement1.executeUpdate();
				if (row > 0)
				{
					if (lan == "en")
					{
						System.out.println("Lastname has been updated");
					} else if (lan == "nl")
					{
						System.out.println("Achternaam is veranderd");
					}

				}
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void updateAge(int index, int age)
	{

		try
		{
			connect();
			String sql = "SELECT id FROM Patient WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = index;
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				String sql1 = "UPDATE Patient SET age = ? WHERE id = ?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setInt(2, id);
				statement1.setInt(1, age);
				int row = statement1.executeUpdate();
				if (row > 0)
				{
					if (lan == "en")
					{
						System.out.println("Age has been updated");
					} else if (lan == "nl")
					{
						System.out.println("Leeftijd is veranderd");
					}

				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateLength(int index, double length)
	{

		try
		{
			connect();
			String sql = "SELECT id FROM Patient WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				String sql1 = "UPDATE Patient SET length = ? WHERE id = ?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setInt(2, index);
				statement1.setDouble(1, length);
				int row = statement1.executeUpdate();
				if (row > 0)
				{
					if (lan == "en")
					{
						System.out.println("Length has been updated");
					} else if (lan == "nl")
					{
						System.out.println("Lengte is veranderd");
					}

				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Deze methode koppelt een gegevens medicijn aan een patient. De informatie
	// wordt opgeslagen in de tussen-tabel patientmedicine.
	public void addMedToPatient(String dosage, int medId)
	{
		try
		{
			connect();
			String sql = "SELECT TOP 1 * FROM Patient ORDER BY id DESC";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next())
			{
				String sql2 = "SELECT * FROM Medicine WHERE id = ?";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				int id = medId;
				statement2.setInt(1, id);
				ResultSet rs1 = statement2.executeQuery();
				while (rs1.next())
				{
					String addDosage;
					if (dosage == "0")
					{
						addDosage = dosage;
					} else
					{
						addDosage = dosage;
					}
					int patId = rs.getInt("id");
					int medicine = rs1.getInt("id");
					String sql1 = "INSERT INTO patientmedicine VALUES(?,?,?)";
					PreparedStatement statement1 = connection.prepareStatement(sql1);
					int medicineId = medicine;
					int patientId = patId;
					statement1.setInt(1, patientId);
					statement1.setInt(2, medicineId);
					statement1.setString(3, addDosage);
					int row = statement1.executeUpdate();
					if (row > 0)
					{
						if (lan == "en")
						{
							System.out.println("Medicine added to patient");
						} else if (lan == "nl")
						{
							System.out.println("Medicijn is aan de patiënt toegevoegd");
						}

					}
				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Via deze methode kan een gewichtspunt worden teogevoegd. In de database wordt
	// in de tabel patienWeight de patient id, gewicht en datum opgeslagen.
	public void addWeight(int index, double weight)
	{
		try
		{
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			connect();
			String sql1 = "SELECT id FROM Patient WHERE id = ?";
			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setInt(1, index);
			ResultSet rs1 = statement1.executeQuery();
			while (rs1.next())
			{
				String sql = "INSERT INTO PatientWeight VALUES(?,?,?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setDouble(1, weight);
				statement.setDate(2, sqlDate);
				statement.setInt(3, index);
				statement.executeUpdate();
				if (lan == "en")
				{
					System.out.println("Weight has been added");
				} else if (lan == "nl")
				{
					System.out.println("Gewicht is toegevoegd");
				}

			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Deze methode haalt de gewichten van een patient op. De gewichten worden via
	// de methode showGraphic getoond in een grafiek.
	public void getWeight(int index)
	{
		try
		{
			connect();
			String sql = "SELECT * FROM PatientWeight WHERE patId = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			if (lan == "en")
			{
				System.out.println("The following weight has been added to the system: ");
				System.out.println("Every * represents 10kg. Every - represents 1kg.");
			} else if (lan == "nl")
			{
				System.out.println("De volgende gewicht zijn bekend in ons systeem: ");
				System.out.println("Elke * correspondeert met 10kg. Elke - correspondeert met 1kg.");
			}

			while (rs.next())
			{
				Date date = rs.getDate("date");
				double weight = rs.getDouble("weight");
				String weightPoint = showGraphic(weight);
				System.out.println(date + " " + weightPoint);

			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Deze methode berekend de bmi door het gewicht en lengte op te halen. De
	// lengte wordt opgehaald door de tabel Patient te joinen.
	public void calculateBmi(int index)
	{
		try
		{
			connect();
			String sql = "SELECT patId, weight, length FROM PatientWeight AS pw INNER JOIN Patient AS p ON p.id = pw.patId WHERE patid = ? ORDER BY date";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				double weight = rs.getDouble("weight");
				double length = rs.getDouble("length");
				double bmi = weight / (length * length);
				DecimalFormat df = new DecimalFormat("#.#");
				if (lan == "en")
				{
					System.out.println("Your bmi is: " + df.format(bmi));
				} else if (lan == "nl")
				{
					System.out.println("De bmi is: " + df.format(bmi));
				}

			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Deze methode laat het gewicht zien doormiddel van sterretjes en streepjes.
	// Elk sterretje is 10kg en elk streepje is een losse kilo.
	private String showGraphic(Double weight)
	{

		String s = "";
		double n = Math.round(weight);
		double test = n / 10;
		String[] arr = String.valueOf(test).split("\\.");
		int[] intArr = new int[2];
		intArr[0] = Integer.parseInt(arr[0]);
		intArr[1] = Integer.parseInt(arr[1]);
		for (int i = 1; i <= intArr[0]; i++)
		{

			if (i % 4 == 0)
			{
				s = s + "*";
			} else
			{
				s = s + "*";
			}
		}
		for (int i = 1; i <= intArr[1]; i++)
		{

			if (i % 4 == 0)
			{
				s = s + "-";
			} else
			{
				s = s + "-";
			}
		}
		return s;
	}
}
