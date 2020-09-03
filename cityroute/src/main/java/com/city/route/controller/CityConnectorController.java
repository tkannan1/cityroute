package com.city.route.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.city.route.services.CityConnectorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(value = "CityConnectorController" , tags = {"City Connector Controller which validate whether two cities are connected"})
@SwaggerDefinition(tags = {@Tag(name = "City Connector Controller", description = "Finds whether two cities are connected")})
@RestController
public class CityConnectorController {
    private final Logger LOG = LoggerFactory.getLogger(CityConnectorController.class);

    @Autowired
    CityConnectorService cityConnectorService;

    @ApiOperation(value = "Returns whether origin and destination connected", response = String.class, tags = "findCitiesRoute")
    @GetMapping("/connected")
    public ResponseEntity<String> findCitiesRoute(@RequestParam @NonNull String origin, @RequestParam @NonNull String destination){
    	LOG.info("Inside CityConnectorController: origin :"+origin+" "+destination);
    	
        return new ResponseEntity<>(cityConnectorService.findCitiesRoute(origin, destination), HttpStatus.OK);
    }
}
