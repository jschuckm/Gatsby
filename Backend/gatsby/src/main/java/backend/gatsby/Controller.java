package backend.gatsby;

import java.util.List;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controller {

	@Autowired
	AttendeeDatabase db;
	
	@GetMapping("/attendee/{id}")
	AttendeeUser getUser(@PathVariable Integer id) {
		return db.findOne(id);
	}
	
	@RequestMapping("/attendees")
	List<AttendeeUser> getAll(){
		return db.findAll();
	}
	
	@PostMapping("/attendee")
	AttendeeUser createUser(@RequestBody AttendeeUser a) {
		db.save(a);
		return a;
	}
	
	@PutMapping("/attendee/{id}")
	AttendeeUser updateUserName(@RequestBody AttendeeUser a, @PathVariable Integer id) {
		AttendeeUser oldA = db.findOne(id);
		oldA.setName(a.getName());
		db.save(oldA);
		return oldA;
	}
	
	@PutMapping("/attendee/{id}")
	AttendeeUser updateUserAddress(@RequestBody AttendeeUser a, @PathVariable Integer id) {
		AttendeeUser oldA = db.findOne(id);
		oldA.setName(a.getAddress());
		db.save(oldA);
		return oldA;
	}
	
	@PutMapping("/attendee/{id}")
	AttendeeUser updateUserEmail(@RequestBody AttendeeUser a, @PathVariable Integer id) {
		AttendeeUser oldA = db.findOne(id);
		oldA.setName(a.getEmail());
		db.save(oldA);
		return oldA;
	}
	
	@PutMapping("/attendee/{id}")
	AttendeeUser updateUserAge(@RequestBody AttendeeUser a, @PathVariable Integer id) {
		AttendeeUser oldA = db.findOne(id);
		oldA.setAge(a.getAge());
		db.save(oldA);
		return oldA;
	}
	
}
