package com.mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StudentController.class)
public class ControllerMockTest {

	@Autowired
	private MockMvc mockMvc;

//	@Before
//	public void setUp() {
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//	}

	@Test
	public void requestHello() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk());
	}
}
