package Application;

import java.io.Serializable;

public class Person implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -8442461307941012359L;
private String firstname;
private String lastname;
private String phone;
private Address address;

public Person() {
	super();
	this.firstname = "";
	this.lastname = "";
	this.phone = "";
	this.address=new Address();
}
public Person(String firstname, String lastname, String phone) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.phone = phone;
	this.address=new Address();
}
public Person(String firstname, String lastname, String phone, Address address) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.phone = phone;
	this.address = address;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}

}
