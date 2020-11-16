package backend.gatsby.repositories;

import backend.gatsby.entities.AttendeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeDatabase extends JpaRepository<AttendeeUser, Integer> {
	AttendeeUser findByUsername(String email);
}
