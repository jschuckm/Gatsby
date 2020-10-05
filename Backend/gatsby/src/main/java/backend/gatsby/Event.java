package backend.gatsby;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

public class Event {

	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date date;
	
	//many to many relation: one event has many attendees and the users can attend many events
	@Column
	private ArrayList<AttendeeUser> attendeesList;
	
	@Column
	private float fee;
	
	@Column
	private HostUser hostProfile;
	
	@Column
	private String address;
	
	//images - ask for help
	
	@Column
	private boolean isPublic;
	
	@Column
	private int capacity;
	
	//define get and set methods
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public float getFee() {
		return fee;
	}
	
	public String getAddress() {
		return address;
	}
	
	public boolean getIsPublic() {
		return isPublic;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setDate(Date d) {
		date = d;
	}
	
	public void setFee(float f) {
		fee = f;
	}
	
	public void setAddress(String a) {
		address = a;
	}
	
	public void setIsPublic(boolean p) {
		isPublic = p;
	}
	
	public void setCapacity(int c) {
		capacity = c;
	}
}
