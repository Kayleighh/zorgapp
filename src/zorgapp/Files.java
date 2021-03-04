package zorgapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Files implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Files()
	{

	}

	public void write(Object prof)
	{
		try
		{
			File f = new File("profiles.txt");
			FileOutputStream fileOut = new FileOutputStream(f, true);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(prof);

			objectOut.close();
			fileOut.close();
			System.out.println("The Object  was succesfully written to a file");

		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public void read()
	{

		try
		{
			FileInputStream fi = new FileInputStream(new File("profiles.txt"));

			ObjectInputStream oi = new ObjectInputStream(fi);

			String profile = (String) oi.readObject();
			System.out.println(profile.toString());

			oi.close();

		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}

	}

}
