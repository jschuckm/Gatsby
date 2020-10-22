package backend.gatsby;

import java.util.List;
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
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@Column
	private String address;

	@Column(nullable = false)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
