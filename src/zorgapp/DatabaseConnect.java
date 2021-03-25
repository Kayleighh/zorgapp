package zorgapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabaseConnect
{
	String		url			= "jdbc:sqlserver://kayleigh\\testserver;databaseName=ZorgApp";
	String		username	= "ZorgAppUser";
	String		password	= "Z0rgApp!";
	Connection	connection;

	DatabaseConnect()
	{

	}

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
				System.out.println("added");
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	/*
	 * public void addMedicine(Medicine medicine) { try { connect(); String sql =
	 * "INSERT INTO Medicine(name,description,soort,dosage) VALUES(?,?,?,?)";
	 * PreparedStatement statement = connection.prepareStatement(sql); String name =
	 * medicine.getMedicineName(); String description = medicine.getDescription();
	 * String soort = medicine.getSoort(); String dosage = medicine.getDosage();
	 * statement.setString(1, name); statement.setString(2, description);
	 * statement.setString(3, soort); statement.setString(4, dosage); int row =
	 * statement.executeUpdate(); if (row > 0) {
	 * System.out.println("Medicine added"); }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

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

						System.out.println("First name: " + firstname + "\nLastname: " + lastname + "\nAge: " + age
								+ "\nLength: " + length);
						String medName = rs1.getString("name");
						String medDesc = rs1.getString("description");
						String type = rs1.getString("soort");
						String dosage = rs1.getString("dosage");
						System.out.println("The following medication is in our system:\n" + "Medicine name: " + medName
								+ "\nDescription: " + medDesc + "\nType: " + type + "\nDosage: " + dosage);

						getWeight(index);

					}
				}
			}
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
	}

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
					System.out.println("Firstname has been updated");
				}
			}

		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
	}

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
					System.out.println("Lastname has been updated");
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
					System.out.println("Name has been updated");
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
					System.out.println("Length has been updated");
				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
						System.out.println("Medicine added to patient");
					}
				}
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
				System.out.println("Weight has been added");
			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getWeight(int index)
	{
		try
		{
			connect();
			String sql = "SELECT * FROM PatientWeight WHERE patId = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			System.out.println("The following weight has been added to the system: ");
			while (rs.next())
			{
				Date date = rs.getDate("date");
				double weight = rs.getDouble("weight");
				System.out.println(date + " " + weight);
				showGraphic(80.5);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
				System.out.println("Your bmi is: " + bmi);

			}

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String showGraphic(Double weight)
	{
	    String s = "";
	    int n = (int) Math.round(weight/10);
	    for (int i =1; i <= n; i++){
	            if(i%4==0){
	                s = s + "*"+"\n";
	            }
	            else
	                {
	                    s = s + "*";
	                }
	        }
	    return s;
	}
}
