package backend.gatsby;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

@Table(name = "host")
@Entity
public class HostUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	@Column
	private float rating;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	//one to many relation: one host can have multiple events, but an event only has one hosts
	//@Column
	//private ArrayList<Event> eventsHostedHistory;
	
	//define set and get methods for the controller to access the columns
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public float getRating() {
		return rating;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setAge(int a) {
		age = a;
	}
	
	public void setRating(float r) {
		rating = r;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public void setAddress(String a) {
		address = a;
	}
	
}
