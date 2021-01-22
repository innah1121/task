package com.converter.task.service.impl;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.converter.task.exception.CustomException;
import com.converter.task.exception.ErrorMessage;
import com.converter.task.service.ConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
@PropertySource("classpath:fixerio.properties")
public class ConverterServiceImpl implements ConverterService {
	
	@Value("${access_key}")
    private String accessKey;
	
	@Value("${base_url}")
    private String baseUrl;
	

	@Override
	public float convert(String base, String target, Float amount) throws JsonMappingException, JsonProcessingException, CustomException {
		
		float convertedAmount;
		
		String url = baseUrl + "/latest?access_key={access_key}&base={base}&symbols={target}";
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("access_key", accessKey);
		urlParams.put("base", base);
		urlParams.put("target", target);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseFromApi = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, null, String.class);
		System.out.println(responseFromApi.getBody());
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode response = mapper.readTree(responseFromApi.getBody());
		
		JsonNode success = response.get("success");
		if(success.asBoolean() == true) {
			float rate = response.path("rates").path(target.toUpperCase()).floatValue();
			System.out.println(rate);
			convertedAmount = rate * amount;
			return convertedAmount;
		}else {
			JsonNode error = response.at("/error");
			if (error.has("info")) {
				
				throw new CustomException(error.path("code").asText() , error.path("info").asText());
			}  
			else {
				
			    throw new CustomException(error.path("code").asText() ,error.path("type").asText());
		}
		}	
		
	}


}
