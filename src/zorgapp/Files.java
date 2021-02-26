package zorgapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Files {
	//private ProfileList prof = new ProfileList();
	Files() {

	}

	public void write(Profile prof) {
		try {

			FileOutputStream fileOut = new FileOutputStream(new File("profiles.txt"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(prof);
			objectOut.close();
			fileOut.close();
			System.out.println("The Object  was succesfully written to a file");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void read() {
		try {
		 FileInputStream fi = new FileInputStream(new File("profiles.txt"));
         ObjectInputStream oi = new ObjectInputStream(fi);
		Profile prof = (Profile) oi.readObject();
		
		System.out.println(prof.toString());
		
		oi.close();
		fi.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
