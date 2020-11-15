package backend.gatsby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventDatabase db;

    public Event getEventById(int id) throws Exception {
        Event event = db.findById(id).get();
        if (event == null) {
            throw new Exception("Event not found id: "+ id);
        }
        return event;
    }
    public Event save(Event a){
        return db.save(a);
    }
}