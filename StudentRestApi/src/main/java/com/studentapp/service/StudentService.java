package com.studentapp.service;

import java.util.List;

import com.studentapp.exceptions.StudentNotFoundException;
import com.studentapp.modelDto.StudentDto;

public interface StudentService {

	StudentDto addStudent(StudentDto student);

	StudentDto getStudentById(int studentId) throws StudentNotFoundException;

	boolean deleteStudent(int studentId) throws StudentNotFoundException;

	List<StudentDto> getAllStudents();

	StudentDto updateStudent(StudentDto student);

}
