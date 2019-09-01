package com.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mockito.service.IStudentService;

@Controller
public class StudentController {

	@RequestMapping("/home")
	public @ResponseBody String hello() {
		return "Total " + 5;
	}
}
