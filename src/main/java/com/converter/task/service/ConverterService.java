package com.converter.task.service;

import org.springframework.stereotype.Service;

import com.converter.task.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public interface ConverterService {
	
	public float convert(String base, String target, Float amount) throws JsonMappingException, JsonProcessingException, CustomException ;

}
