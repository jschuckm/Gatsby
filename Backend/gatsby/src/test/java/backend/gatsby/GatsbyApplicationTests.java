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
import java.util.Optional;

import org.json.JSONObject;
//import junit/spring tests
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

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
@ComponentScan(basePackages = {"backend.gatsby"})
@SpringBootTest(classes = GatsbyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
//@WebMvcTest(ControllerEvent.class)
//@WebAppConfiguration
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
	public void testEventControllerPost() throws Exception {
		List<Event> l = new ArrayList<Event>();
		when(eventDB.save((Event)any(Event.class)))
		.thenAnswer(x-> {
			Event e = x.getArgument(0);
			l.add(e);
			return e;
		});
		
		Event e = new Event();
		e.setAddress("Ames");
		e.setCapacity(50);
		e.setDate(null);
		e.setFee((float)20.95);
		e.setHost(null);
		e.setIsPublic(true);
		e.setName("Joe's Birthday");
		l.add(e);	
		
		controller.perform(post("/event").contentType(MediaType.APPLICATION_JSON).content(asJsonString(e)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is(e.getName())))
		.andExpect(jsonPath("$.capacity", is(e.getCapacity())));
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
}
