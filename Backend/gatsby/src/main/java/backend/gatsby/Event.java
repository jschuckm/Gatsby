package backend.gatsby;

import java.util.List;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "event")
@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date date;
	
	@Column
	@ElementCollection
	private List<String> applicants;
	
	@Column
	private float fee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	//@JsonIgnore
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
	
	@JsonIgnore
	public HostUser getHost() {
		return hostProfile;
	}
	
	public List<String> getApplicants(){
		return applicants;
	}
	
	public void setHost(HostUser h) {
		hostProfile = h;
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
