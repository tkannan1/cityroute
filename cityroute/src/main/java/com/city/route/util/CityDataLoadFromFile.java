package com.city.route.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class CityDataLoadFromFile {
	private final Logger LOG = LoggerFactory.getLogger(CityDataLoadFromFile.class);
	private final String filePath ="classpath:city.txt";
	public static Map<String, CityRoute> cityMap = new HashMap<String, CityRoute>();
	private static String ROUTE ="route";
	@Autowired
    private ResourceLoader resourceLoader;
	
	@Bean
	public void loadCitiesFromFile() throws IOException {	
		LOG.info("inside loadCitiesFromFile()");		
		try {			
			Resource resource = resourceLoader.getResource(filePath);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));	        
	        CityRoute route = new CityRoute();
	        String line;
	        while((line = reader.readLine()) != null){
	            String[] cities = line.split(",");
	            String origin = cities[0].trim().toUpperCase();
	            String desc = cities[1].trim().toUpperCase();
	            route.addEdge(origin, desc);
	            route.addEdge(desc, origin);
	            LOG.info(origin +"<-- connected -->" + desc);
	        }	        
	        cityMap.put(ROUTE , route);	        
		} catch (Exception  e) {
			LOG.error("Exception : " +e);
			throw e;
		} 
		LOG.info("city route mapping  : " +cityMap);		
	}
}
