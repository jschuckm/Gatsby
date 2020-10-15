package backend.gatsby;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ControllerEvent {
	@Autowired
	EventDatabase db;
	
	@Autowired
	HostDatabase dbH;
	
	@RequestMapping("/event/{id}")
	Event getEvent(@PathVariable Integer id) {
		Event result = db.findById(id).get();
		return result;
	}
	
	@RequestMapping("/events")
	List<Event> getAll()
	{
		return (List<Event>) db.findAll();
	}
	
	@PostMapping("/event")
	Event createEvent(@RequestBody Event e) {
		db.save(e);
		return e;
	}
	
	@PostMapping("/event/{id}/host/{idH}")
	Event setHost(@PathVariable Integer id, @PathVariable Integer idH) {
		Event e = db.findById(id).get();
		HostUser h = dbH.findById(idH).get();
		e.setHost(h);
		h.addEvent(e);
		dbH.save(h);
		db.save(e);
		return e;
	}
	
	@RequestMapping("/event/{id}/host")
	HostUser getHost(@PathVariable Integer id) {
		HostUser h = db.findById(id).get().getHost();
		return h;
	}
	
	@PutMapping("/event/{id}")
	Event updateEvent(@RequestBody Event e, @PathVariable Integer id) {
		Event oldE = db.findById(id).get();
		oldE.setName(e.getName());
		oldE.setAddress(e.getAddress());
		oldE.setCapacity(e.getCapacity());
		oldE.setDate(e.getDate());
		oldE.setFee(e.getFee());
		oldE.setIsPublic(e.getIsPublic());
		oldE.setHost(e.getHost());
		db.save(oldE);
		return oldE;
	}
	
	@DeleteMapping("/event/{id}")
	String deleteEvent(@PathVariable Integer id) {
		db.delete(db.findById(id).get());
		return "Deleted " + id;
	}

}