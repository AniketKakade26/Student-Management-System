package com.Student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Student.Service.MyStudentListService;

@Controller
public class MyStudentListController {
	
		@Autowired
		private MyStudentListService service;
		@RequestMapping("/deleteMyList/{id}")
		public String deleteMyList(@PathVariable("id")int id) {
			service.deleteById(id);
			return "redirect:/my_students";
		}
	}

