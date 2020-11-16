package backend.gatsby.controllers;

import java.util.List;

import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.entities.Event;
import backend.gatsby.models.AttendeeUserDTO;
import backend.gatsby.models.EventDTO;
import backend.gatsby.repositories.AttendeeDatabase;
import backend.gatsby.repositories.EventDatabase;
import backend.gatsby.repositories.HostDatabase;
import backend.gatsby.entities.HostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static backend.gatsby.mappers.MapperDTO.*;

@RestController
public class ControllerEvent {
	@Autowired
    EventDatabase eventDB;
	
	@Autowired
	AttendeeDatabase db;
	
	@RequestMapping("/event/{id}")
    EventDTO getEvent(@PathVariable Integer id) {
		EventDTO result = convertEventToEventDTO(eventDB.findById(id).get());
		return result;
	}
	@RequestMapping("/events/host/{idHost}")
	List<EventDTO> getHostEvents(@PathVariable int idHost)
	{
		AttendeeUser user = db.findById(idHost).get();
		return convertListEventsToListEventDTO(user.getEventsHosting());
	}
	@RequestMapping("/events/attendee/{idAttendee}")
	List<EventDTO> getAttendeeEvents(@PathVariable int idAttendee)
	{
		AttendeeUser user = db.findById(idAttendee).get();
		return convertListEventsToListEventDTO(user.getEventsHosting());
	}
	@RequestMapping("/events")
	List<EventDTO> getAll()
	{
		return convertListEventsToListEventDTO(eventDB.findAll());
	}
	
	@PostMapping("/event")
	EventDTO createEvent(@RequestBody Event e) {
		eventDB.save(e);
		return convertEventToEventDTO(e);
	}
	@PostMapping("/event/{idHost}")
	EventDTO createEvent(@PathVariable Integer idHost,@RequestBody Event e){
		AttendeeUser host = db.findById(idHost).get();
		System.out.println(host);
		e.setHost(host);
		eventDB.save(e);
		return convertEventToEventDTO(e);
	}
	
	@PostMapping("/event/{id}/host/{idH}")
	EventDTO setHost(@PathVariable Integer id, @PathVariable Integer idH) {
		Event e = eventDB.findById(id).get();
		AttendeeUser h = e.getHostUser();
		e.setHost(h);
		h.getEventsHosting().add(e);
		db.save(h);
		eventDB.save(e);
		return convertEventToEventDTO(e);
	}
	
	@RequestMapping("/event/{id}/host")
	AttendeeUser getEventHost(@PathVariable Integer id) {
		AttendeeUser h = eventDB.findById(id).get().getHostUser();
		return h;
	}
	
	@PutMapping("/event/{id}")
	EventDTO updateEvent(@RequestBody Event e, @PathVariable Integer id) {
		Event oldE = eventDB.findById(id).get();
		oldE.setName(e.getName());
		oldE.setAddress(e.getAddress());
		oldE.setCapacity(e.getCapacity());
		oldE.setDate(e.getDate());
		oldE.setFee(e.getFee());
		oldE.setIsPublic(e.getIsPublic());
		oldE.setHost(e.getHostUser());
		eventDB.save(oldE);
		return convertEventToEventDTO(oldE);
	}
	
	@DeleteMapping("/event/{id}")
	String deleteEvent(@PathVariable Integer id) {
		eventDB.delete(eventDB.findById(id).get());
		return "Deleted " + id;
	}

}