package com.studentapp.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {
	
	 @Autowired
	 private MockMvc mockMvc;


	@Test
	void testHandleHttpRequestMethodNotSupportedHttpRequestMethodNotSupportedExceptionHttpHeadersHttpStatusWebRequest() throws Exception {
		
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
	                "/student-api/students/get-one/1").accept(
	                MediaType.APPLICATION_JSON);
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		 JSONParser parser = new JSONParser();
		 JSONObject json = (JSONObject) parser.parse(result.getResponse().getContentAsString());
		 
	     String expectedStatus = (String) json.get("status");
	     
	     assertEquals("METHOD_NOT_ALLOWED", expectedStatus);
		 
	}

	@Test
	void testHandleHttpMediaTypeNotSupportedHttpMediaTypeNotSupportedExceptionHttpHeadersHttpStatusWebRequest() throws Exception {
		
		RequestBuilder requestBuilder =MockMvcRequestBuilders.post(
                "/student-api/students").accept(
                MediaType.APPLICATION_JSON).content("stuId:1,name:sss,dept:cse,age:19")
				.contentType(MediaType.TEXT_PLAIN_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONParser parser = new JSONParser();
		 JSONObject json = (JSONObject) parser.parse(result.getResponse().getContentAsString());
		 String expectedStatus = (String) json.get("status");
		 
		 assertEquals("UNSUPPORTED_MEDIA_TYPE",expectedStatus);
		
	}

	@Test
	void testHandleTypeMismatchTypeMismatchExceptionHttpHeadersHttpStatusWebRequest() throws Exception {
		
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
	                "/student-api/students/get-one/sss").accept(
	                MediaType.APPLICATION_JSON);
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		 System.out.println("Type: "+result.getResponse().getContentAsString());
		 
		 JSONParser parser = new JSONParser();
		 JSONObject json = (JSONObject) parser.parse(result.getResponse().getContentAsString());
		 String expectedStatus = (String) json.get("status");
		 
		 assertEquals("BAD_REQUEST",expectedStatus);
		
	}

	@Test
	void testHandleStudentException() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/student-api/students/get-one/1").accept(
                MediaType.APPLICATION_JSON);
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		 System.out.println("Student: "+result.getResponse().getContentAsString());
		 
		 JSONParser parser = new JSONParser();
		 JSONObject json = (JSONObject) parser.parse(result.getResponse().getContentAsString());
		 String expectedStatus = (String) json.get("status");
		 
		 assertEquals("BAD_REQUEST",expectedStatus);
		
	}

//	@Test
//	void testHandleOtherException() throws Exception {
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/student-api/student").accept(
//                MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		
//		System.out.println("Other: "+result.getResponse().getContentAsString());
//		
//		
//	}

}
