package backend.gatsby;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AttendeeDatabase attendeeDatabase;

    public UserDetailsServiceImpl(AttendeeDatabase attendeeDatabase) {
        this.attendeeDatabase = attendeeDatabase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AttendeeUser attendeeUser = attendeeDatabase.findByUsername(username);
        if (attendeeUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(attendeeUser.getUsername(), attendeeUser.getPassword(), emptyList());
    }

    public AttendeeUser loadAttendeeUserByUsername(String username) throws UsernameNotFoundException {
        AttendeeUser attendeeUser = attendeeDatabase.findByUsername(username);
        if (attendeeUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return attendeeUser;
    }
}
