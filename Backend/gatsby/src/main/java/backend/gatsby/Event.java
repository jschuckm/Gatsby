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
	
}
