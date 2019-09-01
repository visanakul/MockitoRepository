package com.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.mockito.resource.MyEmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestWithMockitoDemoApplicationTests {

	@InjectMocks
	private MyEmployeeService employeeService;

	
	private MockMvc mocMvc;

	@Before
	public void init() {
		mocMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
	}

	@Test
	public void testFindByIdSuccess() throws Exception {
		mocMvc.perform(get("/emp/find?id=1")).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("emp1"))).andExpect(jsonPath("$.*", org.hamcrest.Matchers.hasSize(3)));
	}

	@Test(expected = NestedServletException.class)
	public void testFindByIdFail() throws Exception {
		mocMvc.perform(get("/emp/find?id=11")).andExpect(status().isOk());
	}

	@Test
	public void testFindAllEmployeesSuccess() throws Exception {
		mocMvc.perform(get("/emp/findAll")).andExpect(jsonPath("$.*", Matchers.hasSize(3)));
	}

}
