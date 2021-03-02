package com.studentapp.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.studentapp.dao.StudentRepository;
import com.studentapp.model.Student;
import com.studentapp.modelDto.StudentDto;
import com.studentapp.service.StudentService;


@SpringBootTest
@AutoConfigureMockMvc
class StudentServiceImplTest {
	
//	 @Autowired
//	 private MockMvc mockMvc;
	 
	 @MockBean
	 private StudentRepository studentRepository;
	 
	 @Autowired
	 StudentService studentService;
	 
	 Student mockStudent = new Student(1,"sss","cse",19);
	 
	 List<Student> list =  Arrays.asList(new Student(1,"sss","cse",19),
				new Student(1,"sss","cse",19),
				new Student(1,"sss","cse",19),
				new Student(1,"sss","cse",19));
	 
	 ModelMapper modelMapper = new ModelMapper();

	@Test
	void testAddStudent() {
		
		
		Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mockStudent);
		
		StudentDto newstudentDto = modelMapper.map(mockStudent, StudentDto.class);
		
		System.out.println(newstudentDto.toString());
		
		StudentDto found = studentService.addStudent(newstudentDto);
		
		System.out.println(found.toString());
		
		Student newstudent = modelMapper.map(found, Student.class);
		
		System.out.println(newstudent.toString());
		
		 assertEquals(mockStudent.toString(), newstudent.toString());
		
	}

	@Test
	void testGetStudentById() {
		
		Optional<Student> st = Optional.ofNullable(new Student(1,"sss","cse",19));
		
        Mockito.when(studentRepository.findById(Mockito.anyInt())).thenReturn(st);
		
		StudentDto found = studentService.getStudentById(1);
		
		System.out.println("stdent by id found:"+found.toString());
		
		Student newstudent = modelMapper.map(found, Student.class);
		
		Optional<Student> userOptional = Optional.of(newstudent);
		
		System.out.println("stdent by id :"+newstudent.toString());
		
		 assertEquals(st.toString(), userOptional.toString());
	}

	@Test
	void testDeleteStudent() {
		
		studentService.deleteStudent(1);
		Mockito.verify(studentRepository,Mockito.times(1)).deleteById(1);
		
	}

	@Test
	void testGetAllStudents() {
		
		Mockito.when(studentRepository.findAll()).thenReturn(list);
		
		List<StudentDto> found = studentService.getAllStudents();
		 
		List<StudentDto> dtos = list.stream().map(user -> modelMapper.map(user, StudentDto.class))
				.collect(Collectors.toList());
		
		 assertEquals(found.toString(), dtos.toString());
	}

	@Test
	void testUpdateStudent() {
		
		Student mock = new Student(1,"sss","cse",19);
		
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(mock);
		
		StudentDto newstudentDto = modelMapper.map(mockStudent, StudentDto.class);
		
		StudentDto found = studentService.updateStudent(newstudentDto);
	
		Student newstudent = modelMapper.map(found, Student.class);
		
		 assertEquals(mock.toString(), newstudent.toString());
	}

}
