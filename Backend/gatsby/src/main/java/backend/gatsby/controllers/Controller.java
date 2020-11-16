package backend.gatsby.controllers;

import java.util.List;

import backend.gatsby.entities.Event;
import backend.gatsby.models.AttendeeUserDTO;
import backend.gatsby.repositories.AttendeeDatabase;
import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static backend.gatsby.mappers.MapperDTO.convertAttendeeUserToAttendeeUserDTO;
import static backend.gatsby.mappers.MapperDTO.convertListAttendeeUserToListAttendeeUserDTO;

@RestController
public class Controller {

	@Autowired
    AttendeeDatabase db;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	EventService eventService;
	
	@RequestMapping("/attendee/{id}")
    AttendeeUserDTO getUser(@PathVariable Integer id) {
		AttendeeUserDTO result = convertAttendeeUserToAttendeeUserDTO(db.findById(id).get());
		return result;
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
		return new ResponseEntity(convertAttendeeUserToAttendeeUserDTO(user),HttpStatus.OK);
	}


	@RequestMapping("/attendees")
	List<AttendeeUserDTO> getAll()
	{
		return convertListAttendeeUserToListAttendeeUserDTO(db.findAll());
	}

	@PostMapping("/attendee/getid")
	AttendeeUserDTO getByUserName(@RequestBody AttendeeUser a){
		return convertAttendeeUserToAttendeeUserDTO(db.findByUsername(a.getUsername()));
	}
	@PostMapping("/attendee")
	AttendeeUserDTO createUser(@RequestBody AttendeeUser a) {
		db.save(a);
		return convertAttendeeUserToAttendeeUserDTO(a);
	}
	@PostMapping("/register")
	String registerUser(@RequestBody AttendeeUser a) {

		a.setPassword(bCryptPasswordEncoder.encode(a.getPassword()));
		db.save(a);
		return a.getEmail();
	}
	
	@PutMapping("/attendee/{id}")
	AttendeeUserDTO updateUser(@RequestBody AttendeeUser a, @PathVariable Integer id) {
		AttendeeUser oldA = db.findById(id).get();
		oldA.setName(a.getName());
		oldA.setAge(a.getAge());
		oldA.setAddress(a.getAddress());
		oldA.setEmail(a.getEmail());
		oldA.setUsername(a.getUsername());
		oldA.setRating(a.getRating());
		db.save(oldA);
		return convertAttendeeUserToAttendeeUserDTO(oldA);
	}
	@PutMapping("/attendee")
	AttendeeUserDTO updateUserByUsername(@RequestBody AttendeeUser a) {
		AttendeeUser oldA = db.findByUsername(a.getUsername());
		oldA.setName(a.getName());
		oldA.setAge(a.getAge());
		oldA.setAddress(a.getAddress());
		oldA.setEmail(a.getEmail());
		oldA.setUsername(a.getUsername());
		oldA.setRating(a.getRating());
		db.save(oldA);
		return convertAttendeeUserToAttendeeUserDTO(oldA);
	}
	
	AttendeeUserDTO findOne(@PathVariable Integer id) {

		return convertAttendeeUserToAttendeeUserDTO(db.findById(id).get());
	}
	
	@DeleteMapping("/attendee/{id}")
	String deleteUser(@PathVariable Integer id) {
		db.delete(db.findById(id).get());
		return "Deleted " + id;
	}
}

