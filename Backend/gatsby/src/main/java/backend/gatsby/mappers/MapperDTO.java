package backend.gatsby.mappers;

import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.entities.Event;
import backend.gatsby.models.AttendeeUserDTO;
import backend.gatsby.models.EventItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MapperDTO {
    public static AttendeeUserDTO convertAttendeeUserToAttendeeUserDTO(AttendeeUser user){
        AttendeeUserDTO result = new AttendeeUserDTO();
        result.setUsername(user.getUsername());
        result.setAge(user.getAge());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setAddress(user.getAddress());
        result.setRating(user.getRating());
        result.setEventsAttending(convertListEventsToListEventItemDTO(user.getEventsAttending()));//Convert to List<EventItemDTO>
        return result;
    }
    public static List<AttendeeUserDTO> convertListAttendeeUserToListAttendeeUserDTO(List<AttendeeUser> users){
        List<AttendeeUserDTO> result = new ArrayList<>();
        for(int i = 0;i< users.size();i++){
            result.add(convertAttendeeUserToAttendeeUserDTO(users.get(i)));
        }
        return result;
    }
    public static List<EventItemDTO> convertListEventsToListEventItemDTO(List<Event> events){
        List<EventItemDTO> result = new ArrayList<>();
        for (int i = 0;i<events.size();i++) {
            result.add(convertEventToEventItemDTO(events.get(i)));
        }
        return result;
    }

    public static EventItemDTO convertEventToEventItemDTO(Event event) {
        EventItemDTO result = new EventItemDTO();
        result.setCapacity(event.getCapacity());
        result.setName(event.getName());
        result.setIsPublic(event.getIsPublic());
        result.setHost(event.getHost());
        result.setAddress(event.getAddress());
        result.setDate(event.getDate());
        result.setFee(event.getFee());
        return result;
    }
}
