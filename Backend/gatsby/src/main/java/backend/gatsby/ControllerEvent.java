package backend.gatsby;

import java.util.List;
import org.springframework.*;
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
	
	@RequestMapping("/event/{id}")
	Event getEvent(@PathVariable Integer id) {
		Event result = db.findById(id).get();
		return result;
	}
	
	@RequestMapping("/events")
	List<Event> getAll()
	{
		return db.findAll();
	}
	
	@PostMapping("/event")
	Event createHost(@RequestBody Event e) {
		db.save(e);
		return e;
	}
	
	@PutMapping("/event/{id}")
	Event updateUser(@RequestBody Event e, @PathVariable Integer id) {
		Event oldE = db.findById(id).get();
		oldE.setName(e.getName());
		oldE.setAddress(e.getAddress());
		oldE.setCapacity(e.getCapacity());
		oldE.setDate(e.getDate());
		oldE.setFee(e.getFee());
		oldE.setIsPublic(e.getIsPublic());
		db.save(oldE);
		return oldE;
	}
	
	@DeleteMapping("/event/{id}")
	String deleteUser(@PathVariable Integer id) {
		db.delete(db.findById(id).get());
		return "Deleted " + id;
	}

}