package model;

/**
 * The {@link Teacher} class is the abstract
 * representation of a teacher.
 */
public class Teacher {
	private int id;
	private String firstname;
	private String lastname;
	
	
	public Teacher(int id, String firstname, String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}


	@Override
	public String toString() {
		return id + " : " + firstname + " " + lastname;
	}
}
