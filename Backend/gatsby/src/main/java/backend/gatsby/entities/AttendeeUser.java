package backend.gatsby.entities;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Table(name = "attendee")
@Entity
public class AttendeeUser {

	/**
	 * Unique identifier between Attendees Users
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Integer attendee_id;

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
	private double rating;
	
	/**
	 * Email address of User
	 */
	@Column(nullable = false, unique = true)
	private String email;

	/**
	 * Password of User
	 */
	@Column(nullable = false)
	private String password;

	@ManyToMany
	@JoinTable(
			name = "events_attending",
			joinColumns = @JoinColumn(name = "attendee_id"),
			inverseJoinColumns = @JoinColumn(name = "id"))
	public Set<Event> eventsAttending;


	/**
	 * Address of User
	 */
	@Column
	private String address;

	/**
	 * username of User
	 */
	@Column(nullable = false)
	private String username;

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username to a new String value
	 * @param username new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the user password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the passowrd to a new String value
	 * @param password new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return name of the user
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return email of the user
	 */
	public String getEmail() {
		return email;
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
	public double getRating() {
		return rating;
	}
	/**
	 * @return address of the user
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Sets name of user
	 * @param newName new name
	 */
	public void setName(String newName) {
		name = newName;
	}
	/**
	 * Sets age of user
	 * @param newAge new age
	 */
	public void setAge(int newAge) {
		age = newAge;
	}
	/**
	 * Sets email of user
	 * @param newEmail new email
	 */
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	/**
	 * Sets address of user
	 * @param newAddress new address
	 */
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	/**
	 * Sets rating of user
	 * @param newRating new rating
	 */
	public void setRating(Double newRating) {
		rating = newRating;
	}
	/**
	 * @return user's id
	 */
	public Integer getId() {
		return attendee_id;
	}
	/**
	 * Sets id of user
	 * @param id new id
	 */
	public void setId(Integer id) {
		this.attendee_id = id;
	}

	public Set<Event> getEventsAttending() {
		return eventsAttending;
	}

	public void setEventsAttending(Set<Event> eventsAttending) {
		this.eventsAttending = eventsAttending;
	}
}
