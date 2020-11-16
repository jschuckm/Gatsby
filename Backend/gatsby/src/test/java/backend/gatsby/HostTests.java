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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.any;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"backend.gatsby"})
@SpringBootTest(classes = GatsbyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class HostTests {
	@Autowired
	private MockMvc controller;
	
	@MockBean
	private EventDatabase eventDB;
	
	@MockBean
	private HostDatabase hostDB;
	
	@Test
	public void testGetHost() throws Exception {
		List<HostUser> l = new ArrayList<HostUser>();
		when(hostDB.findAll()).thenReturn(l);
		
		HostUser h = new HostUser();
		h.setName("Joe");
		h.setAddress("Ames");
		h.setAge(35);
		h.setEmail("joe@gmail.com");
		h.setRating((float) 4.7);
		l.add(h);	
		
		controller.perform(get("/hosts").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(h.getName())))
		.andExpect(jsonPath("$[0].age", is(h.getAge())));
	}
	
	@Test
	public void testPostHost() throws Exception{
		List<HostUser> l = new ArrayList<HostUser>();
		when(hostDB.save((HostUser)any(HostUser.class)))
		.thenAnswer(x-> {
			HostUser h = x.getArgument(0);
			l.add(h);
			return h;
		});
		
		HostUser h = new HostUser();
		h.setName("Joe");
		h.setAddress("Ames");
		h.setAge(35);
		h.setEmail("joe@gmail.com");
		h.setRating((float) 4.7);
		
		controller.perform(post("/host").contentType(MediaType.APPLICATION_JSON).content(asJsonString(h)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is(h.getName())))
		.andExpect(jsonPath("$.age", is(h.getAge())));
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}