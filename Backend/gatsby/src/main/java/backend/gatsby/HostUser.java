package backend.gatsby;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

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
	
	@Column
	private ArrayList<Event> eventsHostedHistory;
	
}
