package backend.gatsby;

import java.util.List;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "event")
@Entity
public class Event {

	/**
	 * Unique identifier between Events
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
    
	/**
	 * Name of Event
	 */
	@Column
	private String name;
	/**
	 * Description of Event
	 */
	@Column
	private String description;
	/**
	 * Date of Event
	 */
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-DD'T'HH:mm:ss.SSS'YYYY'", timezone="CDT")
	private Date date;
	/**
	 * List of applicants to the event. Applicants are all the attendees users that have applied to go to this event
	 */
	@Column
	@ElementCollection
	private List<String> applicants;

	@ManyToMany(mappedBy = "eventsAttending")
	Set<AttendeeUser> attendees;
	/**
	 * Fee of entrance to the event
	 */
	@Column
	private float fee;
	
	/**
	 * Host of Event
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	private HostUser hostProfile;
	
	/**
	 * Address of Event
	 */
	@Column
	private String address;
	
	/**
	 * Variable that determines if event will be open to the public or not
	 */
	@Column
	private boolean isPublic;
	
	/**
	 * Maximum number of people that can attend of Event
	 */
	@Column
	private int capacity;
	
	/**
	 * @return name of te event
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return date of the event
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 
	 * @return fee to enter the event
	 */
	public float getFee() {
		return fee;
	}
	/**
	 * 
	 * @return address of the event
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 
	 * @return whether event is public or not
	 */
	public boolean getIsPublic() {
		return isPublic;
	}
	/**
	 * 
	 * @return maximum number of attendees
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * 
	 * @return user profile of the host
	 */
	@JsonIgnore
	public HostUser getHost() {
		return hostProfile;
	}
	/**
	 * 
	 * @return list of attendees that have applied to go to the event
	 */
	public List<String> getApplicants(){
		return applicants;
	}
	/**
	 * Changes the host to h
	 * @param h new host
	 */
	public void setHost(HostUser h) {
		hostProfile = h;
	}
	/**
	 * Changes the name of the event
	 * @param n new name
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * Changes the date of the event
	 * @param d new date
	 */
	public void setDate(Date d) {
		date = d;
	}
	/**
	 * Changes the entrance fee for the event
	 * @param f the new fee
	 */
	public void setFee(float f) {
		fee = f;
	}
	/**
	 * Changes the address of the event
	 * @param a the new address
	 */
	public void setAddress(String a) {
		address = a;
	}
	/**
	 * Changes public status of event
	 * @param p new boolean variable that determines if the event is public
	 */
	public void setIsPublic(boolean p) {
		isPublic = p;
	}
	/**
	 * Changes the capacity of the event
	 * @param c the new capacity
	 */
	public void setCapacity(int c) {
		capacity = c;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<AttendeeUser> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<AttendeeUser> attendees) {
		this.attendees = attendees;
	}
}
