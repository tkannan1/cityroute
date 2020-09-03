# cityroute
# Requirement: 
Create Spring boot application to check whether given two cities are connected. List of City connected given in City.txt. If connection exist then return Yes else return No. Application need to be tested using automated test cases and swagger need to be better API documentation purpose 

# Implemention Steps:
Create basic Spring boot application using the Spring Initializr. Added the following files

city.txt: This file consists of pair of cities in each line with comma separated. 

CityConnectorController.java : This Rest controller have a GET method which takes two city names as query parameters. 

CityRouteApplication.java: This Application class is used to bootstrap and launch a Spring application from a Java main method. This class automatically creates the ApplicationContext from the classpath, scan the configuration classes and launch the application. 

CityConnectorService.java:  This Service classes used to checks whether cities are connected using the information in the Map.

CityDataLoadFromFile.java: Reads the content in city.txt file using BufferedReader

CityRoute.java: Used to link the cities using LinkedList and LinkedHashSet

Search.java : Used to traverse the cities connection using Depth First Search algorithm.

# Testing:
CityConnectorControllerTest.java: Created this test class to validate the output. Used Mockito framework for the purpose of test driven development

Used springfox-swagger2 and springfox-swagger-ui to generate interactive API documentation that lets your users try out the API calls directly in the browser. Added EnableSwagger2 annotations in CityRouteApplication class and Added SwaggerDefinition, API and ApiOperation annotations in controller. 
URL: http://localhost:8080/swagger-ui.html

# Unit testing 
	1. Using Maven
	2. Using Postman
	3. Using Eclipse 
	4. Using Swagger in browser

# 1. Testing Using Maven command line:
Run 'mvn clean install' in the command line. In console output, you can see the unit test cases passed in CityConnectorControllerTest.java without any failure.


# 2. Testing Using PostMan:
Sample Endpoints.
   - http://localhost:8080/connected?origin=Boston&destination=newark
   
   Output: Yes
   
   - http://localhost:8080/connected?origin=New York&destination=Philadelphia
   
   Output: Yes
   
   - http://localhost:8080/connected?origin=Philadelphia&destination=Boston
   
   Output: Yes
   
   - http://localhost:8080/connected?origin=Trenton&destination=Albany
   
   Output: Yes
   
   - http://localhost:8080/connected?origin=BOSTON&destination=Albany
   
   Output: No

# 3. Testing Using Eclipse:
Run the junit testing using the Eclipse. Test result attached in the unit test document.

# 4. Testing Using Swagger: http://localhost:8080/swagger-ui.html

# Application Deployment:
  - Download the project zip file or clone from github.
  - Open command line and go to the source code folder
  - Build using "mvn clean install" in command line. Make sure maven available in class path.
  - To start the server, use java -jar cityroute-0.0.1-SNAPSHOT.jar  

For more information, You can refer to setup document in 'cityroute/docs/Build_and_Start_Application.docx'

Once server is up, you can validate the project using the sample endpoints link above.

# Unit testing screen shots available in 'cityroute/docs/Unit_testing.docx'




