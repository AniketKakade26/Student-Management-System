package com.Student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Student.Entity.MyStudentList;
import com.Student.Entity.Student;
import com.Student.Service.MyStudentListService;
import com.Student.Service.StudentService;

import ch.qos.logback.core.model.Model;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	private MyStudentListService myStudentService;
	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/student_register")
	public String studentRegister() {
		return "studentRegister";
	}

	@GetMapping("/available_students")
	public ModelAndView getAllStudent() {
		List<Student> list = service.getAllStudent();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("studentList");
//		m.addObject("student",list);
		return new ModelAndView("studentList", "student", list);
	}

	@PostMapping("/save")
	public String addStudent(@ModelAttribute Student s) {
		service.save(s);
		return "redirect:/available_students";
	}
	@GetMapping("/my_students")
	public String getMyStudents(Model model) {
		
		List<MyStudentList>list=myStudentService.getAllMyStudents();
		model.addAttribute("student",list);
		return "myStudents";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id")int id) {
		Student s=service.getStudentById(id);
		MyStudentList mb=new MyStudentList(s.getId(),s.getName(),s.getAddress(),s.getSubject(),s.getRollno());
		myStudentService.saveMyStudent(mb);
		return "redirect:/my_students";
	}
	@RequestMapping("/editStudent/{id}")
	public String editStudent(@PathVariable("id")int id,Model model) {
		Student s=service.getStudentById(id);
		model.addAttribute("student",s);
		return "studentEdit";
	}
	@RequestMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_students";
	}
	
}
