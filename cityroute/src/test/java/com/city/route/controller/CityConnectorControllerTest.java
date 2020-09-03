package com.city.route.controller;

import com.city.route.services.CityConnectorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(CityConnectorController.class)
public class CityConnectorControllerTest {
	@Autowired
    private MockMvc mockmvc;
    private String YES ="Yes";
    private String NO = "No"; 

    @MockBean
    private CityConnectorService service;


    @Test
    public void testFindCitiesRouteValid() throws Exception{
        when(service.findCitiesRoute("Boston", "New York")).thenReturn(YES);
        mockmvc.perform(get("/connected?origin=Boston&destination=New York"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(YES));
    }   

    @Test
    public void testFindCitiesRouteCityNotInFile() throws Exception{
        when(service.findCitiesRoute("Boston", "Hartford")).thenReturn(NO);
        mockmvc.perform(get("/connected?origin=Boston&destination=Hartford"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(NO));
    }
    
    @Test
    public void testFindCitiesRouteEmptyOrigin() throws Exception{
        when(service.findCitiesRoute("", "New York")).thenReturn(NO);
        mockmvc.perform(get("/connected?origin=&destination=New York"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(NO));
    }
    
    @Test
    public void testFindCitiesRouteEmptyDestination() throws Exception{
        when(service.findCitiesRoute("Boston", "")).thenReturn(NO);
        mockmvc.perform(get("/connected?origin=Boston&destination="))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(NO));
    }
    
    @Test
    public void testFindCitiesRouteConnectedThroughAnotherCity() throws Exception{
        when(service.findCitiesRoute("Boston", "Philadelphia")).thenReturn(YES);
        mockmvc.perform(get("/connected?origin=Boston&destination=Philadelphia"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(YES));
    }
    
    @Test
    public void testFindCitiesRouteCityNameUpperCase() throws Exception{
        when(service.findCitiesRoute("BOSTON", "NEWARK")).thenReturn(YES);
        mockmvc.perform(get("/connected?origin=BOSTON&destination=NEWARK"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(YES));
    }
    
    @Test
    public void testFindCitiesRouteCityNameLowerCase() throws Exception{
        when(service.findCitiesRoute("boston", "newark")).thenReturn(YES);
        mockmvc.perform(get("/connected?origin=boston&destination=newark"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(YES));
    }
}
