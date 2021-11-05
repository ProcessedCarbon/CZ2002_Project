package CZ2002_Assignment;

import java.util.Scanner;

public class Staff {
	private String name, gender, jobTitle;
	private int emp_id;
	public Staff(String name, String gender, String jobTitle, int emp_id) {
		super();
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.emp_id = emp_id;
	}
	
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	
}
