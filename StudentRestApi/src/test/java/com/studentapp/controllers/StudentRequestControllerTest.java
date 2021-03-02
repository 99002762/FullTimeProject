package com.studentapp.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.studentapp.modelDto.StudentDto;
import com.studentapp.service.StudentService;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentRequestControllerTest {
	
	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private StudentService studentService;
	    
	    StudentDto mockStudentDto = new StudentDto(1,"sss","cse",19);
	    
	    List<StudentDto> list =  Arrays.asList(new StudentDto(1,"sss","cse",19),
				new StudentDto(1,"sss","cse",19),
				new StudentDto(1,"sss","cse",19),
				new StudentDto(1,"sss","cse",19));

	@Test
	public void testGetStudentById() throws Exception {
		
		 Mockito.when(studentService.getStudentById(Mockito.anyInt())).thenReturn(mockStudentDto);
	        
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
	                "/student-api/students/get-one/1").accept(
	                MediaType.APPLICATION_JSON);
	        
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        
	       // System.out.println(result.getResponse().getContentAsString());
	        
	        String expected = "{stuId:1,name:sss,dept:cse,age:19}";
	        
	        JSONAssert.assertEquals(expected, result.getResponse()
	                .getContentAsString(), false);
		
	}
	
	@Test
	public void testGetAllStudents() throws Exception {
		
		 Mockito.when(studentService.getAllStudents()).thenReturn(list);
	        
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
	                "/student-api/students").accept(
	                MediaType.APPLICATION_JSON);
	        
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        
	        
	        System.out.println("result string"+result.getResponse().getContentAsString());
	        
	        String expected = "[{stuId:1,name:sss,dept:cse,age:19},{stuId:1,name:sss,dept:cse,age:19},{stuId:1,name:sss,dept:cse,age:19},{stuId:1,name:sss,dept:cse,age:19}]";
	        
	        System.out.println("getAll:"+expected);
	        
	        JSONAssert.assertEquals(expected, result.getResponse()
	                .getContentAsString(), false);
		
	}
	
	@Test
	public void testDeleteStudent() throws Exception {
		
		 Mockito.when(studentService.deleteStudent(Mockito.anyInt())).thenReturn(true);
	        
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
	                "/student-api/students/delete-one/1").accept(
	                MediaType.APPLICATION_JSON);
	        
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        
	       // System.out.println("result string"+result.getResponse().getContentAsString());
	        
	        String expected = "true";
	        
	        assertEquals(expected, result.getResponse().getContentAsString());
	             
		
	}
	
	@Test
	public void testAddStudent() throws Exception {
		
		String exampleStudentDtoJson = "{\"stuId\":\"1\",\"name\":\"sss\",\"dept\":\"cse\",\"age\":\"19\"}";
		
		 Mockito.when(studentService.addStudent(Mockito.any(StudentDto.class))).thenReturn(mockStudentDto);
	        
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
	                "/student-api/students").accept(
	                MediaType.APPLICATION_JSON).content(exampleStudentDtoJson)
					.contentType(MediaType.APPLICATION_JSON);
	        
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	                
	        
	        MockHttpServletResponse response = result.getResponse();
//	        System.out.println("response:"+response);
//	        System.out.println("response header:"+response.getHeader(HttpHeaders.LOCATION));

			assertEquals(200, response.getStatus());

//			assertEquals("http://localhost:8089/student-api/students",
//					response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testUpdateStudent() throws Exception {
		
		String exampleStudentDtoJson = "{\"stuId\":\"1\",\"name\":\"sss\",\"dept\":\"cse\",\"age\":\"19\"}";
		
		System.out.println("Update :"+exampleStudentDtoJson);
		
		 Mockito.when(studentService.updateStudent(Mockito.any(StudentDto.class))).thenReturn(mockStudentDto);
	        
	        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
	                "/student-api/students/update-one").accept(
	                MediaType.APPLICATION_JSON).content(exampleStudentDtoJson)
					.contentType(MediaType.APPLICATION_JSON);
	        
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();

			assertEquals(200, response.getStatus());

	}
	
	
	
	

}
