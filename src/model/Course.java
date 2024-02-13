package model;

/**
 * The {@link Course} class is the abstract
 * representation of a course.
 */
public class Course {
	private int id;
	private String title;
	private String classroom;
	private String period;
	
	
	public Course(int id, String title, String classroom, String period) {
		this.id = id;
		this.title = title;
		this.classroom = classroom;
		this.period = period;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return title + ", " + classroom + ", " + period;
	}
	
	
	
	
}
