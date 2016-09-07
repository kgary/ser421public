package edu.asupoly.ser422.phone;

public class PhoneEntry {
    private String firstname;
    private String lastname;
    private String phone;

    public PhoneEntry(String name, String lname, String phone)
    {
	this.firstname  = name;
	this.lastname  = lname;
	this.phone = phone;
    }

    public void changeName(String newfname, String newlname) {
    	firstname = newfname;
    	// Nonsensical, to prove a point
    	try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	lastname  = newlname;
    }
    public String toString()
    { return firstname + "\n" + lastname + "\n" + phone; }
}



