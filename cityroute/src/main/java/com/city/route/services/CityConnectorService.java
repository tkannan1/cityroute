package com.city.route.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.route.util.CityDataLoadFromFile;
import com.city.route.util.Search;

@Service
public class CityConnectorService {
	private final Logger LOG = LoggerFactory.getLogger(CityConnectorService.class);
	private static String ROUTE ="route";
    @Autowired
    private Search search;

    public String findCitiesRoute(String origin, String destination){
        LOG.info("inside findCitiesRoute origin "+origin+" destination "+destination);
        
        search.setOrigin(origin.trim().toUpperCase());
        search.setDestination(destination.trim().toUpperCase());
        return search.searchCityRoute(CityDataLoadFromFile.cityMap.get(ROUTE));
    }
}
