package backend.gatsby;

import backend.gatsby.AttendeeUser;
import backend.gatsby.AttendeeDatabase;
import backend.gatsby.Controller;
import backend.gatsby.ControllerEvent;
import backend.gatsby.ControllerHost;
import backend.gatsby.Event;
import backend.gatsby.EventDatabase;
import backend.gatsby.HostDatabase;
import backend.gatsby.HostUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
//import junit/spring tests
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;

//import mockito related
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@WebMvcTest(ControllerEvent.class)
public class GatsbyApplicationTests {

	@Autowired
	private MockMvc controller;
	
	@MockBean
	private EventDatabase eventDB;
	
	@MockBean
	private HostDatabase hostDB;
	
	@Test
	public void testEventControllerGetAll() throws Exception {
		List<Event> l = new ArrayList<Event>();
		when(eventDB.findAll()).thenReturn(l);
		
		Event e = new Event();
		e.setAddress("Ames");
		e.setCapacity(50);
		e.setDate(null);
		e.setFee((float)20.95);
		e.setHost(null);
		e.setIsPublic(true);
		e.setName("Joe's Birthday");
		l.add(e);	
		
		controller.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(e.getName())))
		.andExpect(jsonPath("$[0].capacity", is(e.getCapacity())));
	}

	
	@Test
	public void testEventControllerGetOneInList() throws Exception {
		List<Event> l = new ArrayList<Event>();
		when(eventDB.findAll()).thenReturn(l);
		
		Event e = new Event();
		e.setAddress("Ames");
		e.setCapacity(50);
		e.setDate(null);
		e.setFee((float)20.95);
		e.setHost(null);
		e.setIsPublic(true);
		e.setName("Joe's Birthday");
		l.add(e);
		
		Event e2 = new Event();
		e2.setAddress("Iowa");
		e2.setCapacity(75);
		e2.setDate(null);
		e2.setFee((float)30);
		e2.setHost(null);
		e2.setIsPublic(false);
		e2.setName("Pool Party");
		l.add(e2);
		
		controller.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].name", is(e2.getName())))
		.andExpect(jsonPath("$[1].capacity", is(e2.getCapacity())));;
		
		controller.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(e.getName())))
		.andExpect(jsonPath("$[0].capacity", is(e.getCapacity())));;
	}
	
	@Test
	public void testEventControllerUpdate() throws Exception {
		List<Event> l = new ArrayList<Event>();
		when(eventDB.findAll()).thenReturn(l);
		
		Event e = new Event();
		e.setAddress("Ames");
		e.setCapacity(50);
		e.setDate(null);
		e.setFee((float)20.95);
		e.setHost(null);
		e.setIsPublic(true);
		e.setName("Joe's Birthday");
		l.add(e);
		
		controller.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(e.getName())))
		.andExpect(jsonPath("$[0].capacity", is(e.getCapacity())))
		.andExpect(jsonPath("$[0].isPublic", is(e.getIsPublic())));
		
		e.setAddress("Iowa");
		e.setCapacity(75);
		e.setDate(null);
		e.setFee((float)30);
		e.setHost(null);
		e.setIsPublic(false);
		e.setName("Pool Party");
		l.add(e);
		
		
		controller.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(e.getName())))
		.andExpect(jsonPath("$[0].capacity", is(e.getCapacity())))
		.andExpect(jsonPath("$[0].isPublic", is(e.getIsPublic())));
	}
	
	/*@Test
	public void testHostConnectionToEvent() throws Exception {
		List<HostUser> hostList = new ArrayList<HostUser>();
		List<Event> eventList = new ArrayList<Event>();
		when(eventDB.findAll()).thenReturn(eventList);
		
		HostUser h = new HostUser();
		h.setAddress("Ames");
		h.setAge(22);
		h.setEmail("joe@gmail.com");
		h.setName("Joe");
		h.setRating((float)4.8);
		hostList.add(h);
		
		Event e = new Event();
		e.setAddress("Ames");
		e.setCapacity(50);
		e.setDate(null);
		e.setFee((float)20.95);
		e.setHost(h);
		e.setIsPublic(true);
		e.setName("Joe's Birthday");
		eventList.add(e);
		
		
		controller.perform(get("/events").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].host.name", is(h.getName())))
		.andExpect(jsonPath("$[0].host.age", is(h.getAge())));
	}*/
}
