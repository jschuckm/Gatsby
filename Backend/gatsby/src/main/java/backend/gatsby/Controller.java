package backend.gatsby;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class Controller {

	@Autowired
	AttendeeDatabase db;
	@Autowired
	EventService eventService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/attendee/{id}")
	AttendeeUser getUser(@PathVariable Integer id) {
		AttendeeUser result = db.findById(id).get();
		return result;
	}
	
	@RequestMapping("/attendees")
	List<AttendeeUser> getAll()
	{
		return (List<AttendeeUser>) db.findAll();
	}

	@PostMapping("/attendee/getid")
	AttendeeUser getByUserName(@RequestBody AttendeeUser a){
		return db.findByUsername(a.getUsername());
	}
	@PostMapping("/attendee/registerevent/{eventId}")
	ResponseEntity registerForEvent(@PathVariable int eventId, @RequestBody AttendeeUser a){
		AttendeeUser user = db.findByUsername(a.getUsername());
		Event event;
		try {
			event = eventService.getEventById(eventId);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		if(!user.eventsAttending.contains(event)) {
			user.eventsAttending.add(event);
			event.attendees.add(user);
		}else{
			return new ResponseEntity((HttpStatus.BAD_REQUEST));
		}
		db.save(user);
		eventService.save(event);
		return new ResponseEntity(user,HttpStatus.OK);
	}
	@PostMapping("/attendee")
	AttendeeUser createUser(@RequestBody AttendeeUser a) {
		db.save(a);
		return a;
	}
	@PostMapping("/register")
	String registerUser(@RequestBody AttendeeUser a) {

		a.setPassword(bCryptPasswordEncoder.encode(a.getPassword()));
		db.save(a);
		return a.getEmail();
	}
	
	@PutMapping("/attendee/{id}")
	AttendeeUser updateUser(@RequestBody AttendeeUser a, @PathVariable Integer id) {
		AttendeeUser oldA = db.findById(id).get();
		oldA.setName(a.getName());
		oldA.setAge(a.getAge());
		oldA.setAddress(a.getAddress());
		oldA.setEmail(a.getEmail());
		oldA.setUsername(a.getUsername());
		oldA.setRating(a.getRating());
		db.save(oldA);
		return oldA;
	}
	@PutMapping("/attendee")
	AttendeeUser updateUserByUsername(@RequestBody AttendeeUser a) {
		AttendeeUser oldA = db.findByUsername(a.getUsername());
		oldA.setName(a.getName());
		oldA.setAge(a.getAge());
		oldA.setAddress(a.getAddress());
		oldA.setEmail(a.getEmail());
		oldA.setUsername(a.getUsername());
		oldA.setRating(a.getRating());
		db.save(oldA);
		return oldA;
	}
	
	AttendeeUser findOne(@PathVariable Integer id) {
		return db.findById(id).get();
	}
	
	@DeleteMapping("/attendee/{id}")
	String deleteUser(@PathVariable Integer id) {
		db.delete(db.findById(id).get());
		return "Deleted " + id;
	}
}

