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
public class ControllerHost {
	@Autowired
	HostDatabase db;
	
	@RequestMapping("/host/{id}")
	HostUser getHost(@PathVariable Integer id) {
		HostUser result = db.findById(id).get();
		return result;
	}
	
	@RequestMapping("/hosts")
	List<HostUser> getAll()
	{
		return db.findAll();
	}
	
	@PostMapping("/host")
	HostUser createHost(@RequestBody HostUser h) {
		db.save(h);
		return h;
	}
	
	@PutMapping("/host/{id}")
	HostUser updateUser(@RequestBody HostUser h, @PathVariable Integer id) {
		HostUser oldH = db.findById(id).get();
		oldH.setName(h.getName());
		oldH.setAge(h.getAge());
		oldH.setAddress(h.getAddress());
		oldH.setEmail(h.getEmail());
		oldH.setRating(h.getRating());
		db.save(oldH);
		return oldH;
	}
	
	@DeleteMapping("/host/{id}")
	String deleteUser(@PathVariable Integer id) {
		db.delete(db.findById(id).get());
		return "Deleted " + id;
	}

}
