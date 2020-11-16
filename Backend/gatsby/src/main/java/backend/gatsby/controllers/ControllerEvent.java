package backend.gatsby.controllers;

import java.util.List;

import backend.gatsby.entities.Event;
import backend.gatsby.models.EventDTO;
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

import static backend.gatsby.mappers.MapperDTO.convertEventToEventDTO;
import static backend.gatsby.mappers.MapperDTO.convertListEventsToListEventDTO;

@RestController
public class ControllerEvent {
	@Autowired
    EventDatabase eventDB;
	
	@Autowired
    HostDatabase hostDB;
	
	@RequestMapping("/event/{id}")
    EventDTO getEvent(@PathVariable Integer id) {
		EventDTO result = convertEventToEventDTO(eventDB.findById(id).get());
		return result;
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
	
	@PostMapping("/event/{id}/host/{idH}")
	EventDTO setHost(@PathVariable Integer id, @PathVariable Integer idH) {
		Event e = eventDB.findById(id).get();
		HostUser h = hostDB.findById(idH).get();
		e.setHost(h);
		h.addEvent(e);
		hostDB.save(h);
		eventDB.save(e);
		return convertEventToEventDTO(e);
	}
	
	@RequestMapping("/event/{id}/host")
	HostUser getEventHost(@PathVariable Integer id) {
		HostUser h = eventDB.findById(id).get().getHost();
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
		oldE.setHost(e.getHost());
		eventDB.save(oldE);
		return convertEventToEventDTO(oldE);
	}
	
	@DeleteMapping("/event/{id}")
	String deleteEvent(@PathVariable Integer id) {
		eventDB.delete(eventDB.findById(id).get());
		return "Deleted " + id;
	}

}