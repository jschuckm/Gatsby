package backend.gatsby.services;

import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.repositories.AttendeeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AttendeeUserService {
    @Autowired
    AttendeeDatabase db;

    public AttendeeUser loadAttendeeUserByUsername(String username) throws UsernameNotFoundException {
        AttendeeUser attendeeUser = db.findByUsername(username);
        if (attendeeUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return attendeeUser;
    }
}
