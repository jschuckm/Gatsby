package backend.gatsby;

import java.util.ArrayList;
import javax.persistence.*;

@Table(name = "attendee")
@Entity
public class AttendeeUser {
	//first, define all of the fields that belong to this user
	//The ID is defined similarly to the example provided to us by prof. Mitra
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	@Column
	private double rating;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getAge() {
		return age;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setAge(int newAge) {
		age = newAge;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public void setAddress(String newAddress) {
		address = newAddress;
	}

	public void setRating(Double newRating) {
		rating = newRating;
	}
}
