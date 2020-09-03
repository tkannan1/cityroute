package com.city.route.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class Search {
    final Logger LOG = LoggerFactory.getLogger(Search.class);
    private final static String YES ="Yes"; 
    private final static String NO ="No"; 
    public String origin;
    public String destination;
    public static List<String> routeList = new ArrayList<>();


    public String searchCityRoute(CityRoute cityRoute){
        Search.routeList = new ArrayList<>();
        LinkedList<String> cityLinkedList = new LinkedList<>();
        cityLinkedList.add(origin);
        depthFirst(cityRoute, cityLinkedList);

        return Search.routeList.size() > 0? YES : NO;
    }

    private void depthFirst(CityRoute cityRoute, LinkedList<String> cityLinkedList) {
        LinkedList<String> nodes = cityRoute.adjacentNodes(cityLinkedList.getLast());
        for (String node : nodes) {
            if (cityLinkedList.contains(node)) {
                continue;
            }
            if (node.equals(destination)) {
            	cityLinkedList.add(node);
                addCityRoutes(cityLinkedList);
                cityLinkedList.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (cityLinkedList.contains(node) || node.equals(destination)) {
                continue;
            }
            else{
            	cityLinkedList.addLast(node);
            	depthFirst(cityRoute, cityLinkedList);
                cityLinkedList.removeLast();
            }           
        }
    }

    private void addCityRoutes(LinkedList<String> cityLinkedList) {    	
        for (String node : cityLinkedList) { 
        	LOG.info(node);
            Search.routeList.add(node);
        }        
    }

	public void setOrigin(String origin) {
		this.origin = origin;
		
	}

	public void setDestination(String destination) {		
		this.destination = destination;
	}
}
