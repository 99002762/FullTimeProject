package com.studentapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentapp.exceptions.StudentNotFoundException;
import com.studentapp.modelDto.StudentDto;
import com.studentapp.service.StudentService;


@RestController
@RequestMapping("/student-api")
public class StudentRequestController {
	
	@Autowired
	StudentService studentService;
	
	 @PostMapping("/students")
	    ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto student) {
	        StudentDto nstudent = studentService.addStudent(student);
	        return ResponseEntity.ok(nstudent);
	    }
	 
	    @DeleteMapping("/students/delete-one/{stuId}")
	    ResponseEntity<Boolean> deleteStudent(@PathVariable("stuId")Integer stuId) {
	        Boolean res = studentService.deleteStudent(stuId);
	        return ResponseEntity.status(HttpStatus.OK).body(res);
	    }
	    
	    @GetMapping("/students/get-one/{stuId}")
	    ResponseEntity<StudentDto> getStudentById(@PathVariable("stuId")Integer stuId) throws StudentNotFoundException{
	        StudentDto nstudent = studentService.getStudentById(stuId);
	        HttpHeaders header = new HttpHeaders();
	        header.add("desc", "Getting student by Id");
	        header.add("title", "One Student");
	        return ResponseEntity.status(HttpStatus.OK)
	                .headers(header).body(nstudent);
	    }
	    
	    @PutMapping("/students/update-one")
	    ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto student) {
	        StudentDto nstudent = studentService.updateStudent(student);
	        return ResponseEntity.ok(nstudent);
	    }
	    
	    
	    @GetMapping("/students")
	    ResponseEntity<List<StudentDto>> getAllStudents(){
	        List<StudentDto> studentList =  studentService.getAllStudents();
	        return ResponseEntity.ok(studentList);
	    }

	    

}
