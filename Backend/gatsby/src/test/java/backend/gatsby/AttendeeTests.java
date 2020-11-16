package backend.gatsby;

import java.util.ArrayList;
import java.util.List;

//import junit/spring tests
import backend.gatsby.entities.AttendeeUser;
import backend.gatsby.repositories.AttendeeDatabase;
import backend.gatsby.repositories.EventDatabase;
import backend.gatsby.repositories.HostDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;

//import mockito related
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.any;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"backend.gatsby"})
@SpringBootTest(classes = GatsbyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
//@WebMvcTest(ControllerEvent.class)
//@WebAppConfiguration
public class AttendeeTests {
	@Autowired
	private MockMvc controller;
	
	@MockBean
	private EventDatabase eventDB;
	
	@MockBean
	private HostDatabase hostDB;
	
	@MockBean
	private AttendeeDatabase attendeeDB;
	
	@Test
	public void testGetAttendee() throws Exception {
		List<AttendeeUser> l = new ArrayList<AttendeeUser>();
		when(attendeeDB.findAll()).thenReturn(l);
		
		AttendeeUser a = new AttendeeUser();
		a.setName("Joe");
		a.setAddress("Ames");
		a.setAge(35);
		a.setEmail("joe@gmail.com");
		a.setRating(4.7);
		l.add(a);	
		
		controller.perform(get("/attendees").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(a.getName())))
		.andExpect(jsonPath("$[0].age", is(a.getAge())));
	}
	
	@Test
	public void testPostAttendee() throws Exception{
		List<AttendeeUser> l = new ArrayList<AttendeeUser>();
		when(attendeeDB.save((AttendeeUser)any(AttendeeUser.class)))
		.thenAnswer(x-> {
			AttendeeUser a = x.getArgument(0);
			l.add(a);
			return a;
		});
		AttendeeUser a = new AttendeeUser();
		a.setName("Joe");
		a.setAddress("Ames");
		a.setAge(35);
		a.setEmail("joe@gmail.com");
		a.setRating(4.7);
		
		controller.perform(post("/attendee").contentType(MediaType.APPLICATION_JSON).content(asJsonString(a)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is(a.getName())))
		.andExpect(jsonPath("$.age", is(a.getAge())));
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
