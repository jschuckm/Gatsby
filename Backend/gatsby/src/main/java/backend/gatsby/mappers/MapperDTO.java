package backend.gatsby.mappers;

import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.entities.Event;
import backend.gatsby.models.AttendeeUserDTO;
import backend.gatsby.models.AttendeeUserItemDTO;
import backend.gatsby.models.EventDTO;
import backend.gatsby.models.EventItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MapperDTO {
    public static AttendeeUserDTO convertAttendeeUserToAttendeeUserDTO(AttendeeUser user){
        AttendeeUserDTO result = new AttendeeUserDTO();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setAge(user.getAge());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setAddress(user.getAddress());
        result.setRating(user.getRating());
        result.setEventsAttending(convertListEventsToListEventItemDTO(user.getEventsAttending()));//Convert to List<EventItemDTO>
        result.setEventsHosting(convertListEventsToListEventItemDTO(user.eventsHosting));
        return result;
    }
    public static AttendeeUserItemDTO convertAttendeeUserToAttendeeUserItemDTO(AttendeeUser user){
        AttendeeUserItemDTO result = new AttendeeUserItemDTO();
        if(user!=null) {
            result.setUsername(user.getUsername());
            result.setAge(user.getAge());
            result.setName(user.getName());
            result.setEmail(user.getEmail());
            result.setAddress(user.getAddress());
            result.setRating(user.getRating());
            result.setId(user.getId());
        }
        return result;
    }
    public static List<AttendeeUserDTO> convertListAttendeeUserToListAttendeeUserDTO(List<AttendeeUser> users){
        List<AttendeeUserDTO> result = new ArrayList<>();
        for(int i = 0;i< users.size();i++){
            result.add(convertAttendeeUserToAttendeeUserDTO(users.get(i)));
        }
        return result;
    }
    public static List<AttendeeUserItemDTO> convertListAttendeeUserToListAttendeeUserItemDTO(List<AttendeeUser> users){
        List<AttendeeUserItemDTO> result = new ArrayList<>();
        if(users!=null) {
            for (int i = 0; i < users.size(); i++) {
                result.add(convertAttendeeUserToAttendeeUserItemDTO(users.get(i)));
            }
        }
        return result;
    }
    public static List<EventItemDTO> convertListEventsToListEventItemDTO(List<Event> events){
        List<EventItemDTO> result = new ArrayList<>();
        if(events!=null) {
            for (int i = 0; i < events.size(); i++) {
                result.add(convertEventToEventItemDTO(events.get(i)));
            }
        }
        return result;
    }

    public static EventItemDTO convertEventToEventItemDTO(Event event) {
        EventItemDTO result = new EventItemDTO();
        result.setCapacity(event.getCapacity());
        result.setName(event.getName());
        result.setIsPublic(event.getIsPublic());
        result.setAddress(event.getAddress());
        result.setDate(event.getDate());
        result.setFee(event.getFee());
        result.setId(event.getId());
        return result;
    }
    public static EventDTO convertEventToEventDTO(Event event) {
        EventDTO result = new EventDTO();
        result.setCapacity(event.getCapacity());
        result.setName(event.getName());
        result.setIsPublic(event.getIsPublic());
        result.setAddress(event.getAddress());
        result.setDate(event.getDate());
        result.setFee(event.getFee());
        result.setAttendees(convertListAttendeeUserToListAttendeeUserItemDTO(event.getAttendees()));
        result.setId(event.getId());
        result.setHostUser(convertAttendeeUserToAttendeeUserItemDTO(event.getHostUser()));
        return result;
    }
    public static List<EventDTO> convertListEventsToListEventDTO(List<Event> events){
        List<EventDTO> result = new ArrayList<>();
        for (int i = 0;i<events.size();i++) {
            result.add(convertEventToEventDTO(events.get(i)));
        }
        return result;
    }
}
