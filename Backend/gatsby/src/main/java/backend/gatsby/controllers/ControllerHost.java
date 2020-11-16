package backend.gatsby.controllers;

import java.util.List;
import java.util.Set;

import backend.gatsby.entities.Event;
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

@RestController
public class ControllerHost {
	@Autowired
    HostDatabase hostDB;
	
	@Autowired
    EventDatabase eventDB;
	
	@RequestMapping("/host/{id}")
    HostUser getHost(@PathVariable Integer id) {
		HostUser result = hostDB.findById(id).get();
		return result;
	}
	
	@RequestMapping("/hosts")
	List<HostUser> getAll()
	{
		return (List<HostUser>) hostDB.findAll();
	}
	
	@PostMapping("/host")
	HostUser createHost(@RequestBody HostUser h) {
		hostDB.save(h);
		return h;
	}
	/*@PostMapping("/host/{id}/events/{idE}")
	HostUser addEvent(@PathVariable Integer id, @PathVariable Integer idE) {
		Event e = eventDB.findById(idE).get();
		HostUser h = hostDB.findById(id).get();
		e.setHost(h);
		h.addEvent(e);
		eventDB.save(e);
		hostDB.save(h);
		return h;
	}*/
	/*
	@RequestMapping("/host/{id}/events")
	Set<Event> getHostEvents(@PathVariable Integer id) {
		Set<Event> e = hostDB.findById(id).get().getEventsHostedHistory();
		return e;
	}*/
	
	@PutMapping("/host/{id}")
	HostUser updateUser(@RequestBody HostUser h, @PathVariable Integer id) {
		HostUser oldH = hostDB.findById(id).get();
		oldH.setName(h.getName());
		oldH.setAge(h.getAge());
		oldH.setAddress(h.getAddress());
		oldH.setEmail(h.getEmail());
		oldH.setRating(h.getRating());
		hostDB.save(oldH);
		return oldH;
	}
	
	@DeleteMapping("/host/{id}")
	String deleteUser(@PathVariable Integer id) {
		hostDB.delete(hostDB.findById(id).get());
		return "Deleted " + id;
	}
}
