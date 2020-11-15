package backend.gatsby.repositories;

import backend.gatsby.entities.HostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostDatabase extends JpaRepository<HostUser, Integer> {
	
}

