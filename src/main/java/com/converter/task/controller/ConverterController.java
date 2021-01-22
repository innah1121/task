package com.converter.task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.converter.task.exception.CustomException;
import com.converter.task.service.ConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@CrossOrigin(origins = "*")
public class ConverterController {
	
	@Autowired
	private ConverterService service;
	
	@GetMapping(value = "/convert")
	public  ResponseEntity<Float> convert(@RequestParam String base,@RequestParam String target,@RequestParam Float amount) throws JsonMappingException, JsonProcessingException, CustomException 
	{
	
	  return ResponseEntity.ok(service.convert(base, target, amount));
	}

}
