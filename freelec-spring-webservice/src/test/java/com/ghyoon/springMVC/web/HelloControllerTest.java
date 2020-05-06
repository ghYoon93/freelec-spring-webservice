package com.ghyoon.springMVC.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class HelloControllerTest {
	@InjectMocks
	private HelloController helloController;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(helloController).build();
	}
	
	@Test
	public void helloTest() throws Exception {
		String hello = "hello";
		mvc.perform(get("/hello"))
		.andExpect(status().isOk())
		.andExpect(content().string(hello));
	}
	
	@Test
	public void helloDtoTest() throws Exception {
		String name = "hello";
		int amount = 1000;
		mvc.perform(
				get("/hello/dto")
					.param("name", name)
					.param("amount", String.valueOf(amount)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(name)))  // JSON 응답값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
	}
}
