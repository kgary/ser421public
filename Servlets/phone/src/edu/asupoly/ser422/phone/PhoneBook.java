package edu.asupoly.ser422.phone;

import java.io.*;
import java.util.*;

public class PhoneBook {
	public static final String DEFAULT_FILENAME = "phonebook.txt";

	private Map<String, PhoneEntry> _pbook = new HashMap<String, PhoneEntry>();

	public PhoneBook() throws IOException {
		this(DEFAULT_FILENAME); 
	}
	public PhoneBook(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}
	public PhoneBook(InputStream is) throws IOException {
		this(new BufferedReader(new InputStreamReader(is)));
	}
	private PhoneBook(BufferedReader br) throws IOException {	
		String name = null;
		String lname = null;
		String phone = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null)
			{
				name  = nextLine;
				lname = br.readLine();
				phone = br.readLine();
				addEntry(name, lname, phone);
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error process phonebook");
			throw new IOException("Could not process phonebook file");
		}
	}

	public void savePhoneBook(String fname)
	{
		try {
			System.out.println("Opening " + fname);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fname));
			System.out.println("...done");
			String[] entries = listEntries();
			for (int i = 0; i < entries.length; i++)
				pw.println(entries[i]);

			pw.close();
		}
		catch (Exception exc)
		{ 
			exc.printStackTrace(); 
			System.out.println("Error saving phone book");
		}
	}

	public void editEntry(String phone, String fname, String lname) {
		PhoneEntry pentry = _pbook.get(phone);
		pentry.changeName(fname, lname);
	}
	
	public void addEntry(String fname, String lname, String phone)
	{ 
		addEntry(phone, new PhoneEntry(fname, lname, phone));
	}

	public void addEntry(String number, PhoneEntry entry)
	{ _pbook.put(number, entry); }

	public String[] listEntries()
	{
		String[] rval = new String[_pbook.size()];
		int i = 0;
		PhoneEntry nextEntry = null;
		for (Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}
}
