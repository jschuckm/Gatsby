package backend.gatsby;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface AttendeeDatabase extends JpaRepository<AttendeeUser, Integer> {
	AttendeeUser findOne(@PathVariable Integer id);
	
}
