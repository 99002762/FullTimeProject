package com.studentapp.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentapp.dao.StudentRepository;
import com.studentapp.exceptions.StudentNotFoundException;
import com.studentapp.model.Student;
import com.studentapp.modelDto.StudentDto;
import com.studentapp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public StudentDto addStudent(StudentDto student) {

		Student newstudent = modelMapper.map(student, Student.class);

		studentRepository.save(newstudent);

		return student;

	}

	@Override
	public StudentDto getStudentById(int studentId) throws StudentNotFoundException {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new StudentNotFoundException("Id not available"));
		StudentDto newstudentDto = modelMapper.map(student, StudentDto.class);
		return newstudentDto;
	}

	@Override
	public boolean deleteStudent(int studentId) throws StudentNotFoundException {

		studentRepository.deleteById(studentId);

		return true;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();

		List<StudentDto> dtos = students.stream().map(user -> modelMapper.map(user, StudentDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public StudentDto updateStudent(StudentDto student) {
		// TODO Auto-generated method stub

		Student newstudent = modelMapper.map(student, Student.class);
		Student saved = studentRepository.save(newstudent);
		StudentDto newstudentDto = modelMapper.map(saved, StudentDto.class);
		return newstudentDto;
	}

}
