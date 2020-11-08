package backend.gatsby;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name = "host")
@Entity
public class HostUser {

	/**
	 * Unique identifier between Host Users
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	/**
	 * Name of User
	 */
	@Column
	private String name;
	
	/**
	 * Age of User
	 */
	@Column
	private int age;
	
	/**
	 * This represents how other users have rated this one in the past
	 */
	@Column
	private float rating;
	
	/**
	 * Email address of User
	 */
	@Column
	private String email;
	
	/**
	 * Address of User
	 */
	@Column
	private String address;
	
	/**
	 * List containing all events hosted by this user
	 */
	//one to many relation: one host can have multiple events, but an event only has one hosts
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "host")
	private Set<Event> eventsHostedHistory;
	
	/**
	 * @return name of the user
	 */
	//define set and get methods for the controller to access the columns
	public String getName() {
		return name;
	}
	
	/**
	 * @return age of the user
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * @return rating of the user
	 */
	public float getRating() {
		return rating;
	}
	
	/**
	 * @return email of the user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return address of the user
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * This method has the @JsonIgone annotation to prevent infinite recursion when viewing the response of a request
	 * @return list of events hosted
	 */
	@JsonIgnore
	public Set<Event> getEventsHostedHistory(){
		return eventsHostedHistory;
	}
	
	/**
	 * Adds an event to the list of events of this host
	 * @param e event to be added
	 */
	public void addEvent(Event e) {
		eventsHostedHistory.add(e);
	}
	
	/**
	 * Sets name of host
	 * @param n new name
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Sets age of host
	 * @param a new age
	 */
	public void setAge(int a) {
		age = a;
	}
	
	/**
	 * Sets rating of host
	 * @param r new rating
	 */
	public void setRating(float r) {
		rating = r;
	}
	
	/**
	 * Sets email of host
	 * @param e new email
	 */
	public void setEmail(String e) {
		email = e;
	}
	
	/**
	 * Sets address of host
	 * @param a new address
	 */
	public void setAddress(String a) {
		address = a;
	}
	
}
