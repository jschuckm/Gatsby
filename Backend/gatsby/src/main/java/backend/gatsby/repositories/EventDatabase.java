package backend.gatsby.repositories;

import backend.gatsby.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDatabase extends JpaRepository<Event, Integer> {
}