# RESTAppln
Techstack: SpringBoot,Junit 4,5 Mockito,H2 Database.
    Spring    : Basically to avoid boiler plate code like resolving dependencies on the go. Handling transactions by default. 
	SpringBoot: No need to have separate dependency like Spring,hibernate,Junit, Mockito etc..
				Tomcat comes by default. Good to test locally.
	H2        : In memory database, no need to have explicitly for sample proj. For actual, we can still configure based on need.
	Mockito	  : To mock actual service,repository, so that can invoke in junit test cases.

Dependency: 
  spring-boot-starter-data-jpa
	usage: Spring data jpa, hibernate
  org.springframework.boot
    usage: Spring, basic security, web, test, tomcat based deployment
  com.h2database
    usage: In memory database to store and retreive data.
  org.springframework.security
    usage: particularly to mock user to run secured REST service. Tag Ref: WithMockUser
	
Documentation for end user:

  There are 2 APIs and it is deployed in Deployed in AWS.
  
  
  
    1) HTTP Method: GET-->http://ec2-3-133-95-7.us-east-2.compute.amazonaws.com:8080/Auction-0.0.1-SNAPSHOT/auction?status=RUNNING
	   Authorization: Not required. 
	   For localhost: http://localhost:8080/auction?status=RUNNING
	   Optional parameters usage 1: page=0&size=4(if total values count is 4)
					Desc: Retreives 4 values(max) from initial page.
	   Optional parameters usage 2: page=2&size=2(if total values are 4 then for each page 2 will get displayed)
					Desc: Retreives 4 values(max) from initial page.	
           Sort by : Itemcode.									
	
    2) HTTP Method: POST-->http://ec2-3-133-95-7.us-east-2.compute.amazonaws.com:8080/Auction-0.0.1-SNAPSHOT/auction/BMW8/bid
	  Authorization: Basic Auth. sample users: user1,user2,user3,user4. password: password
	  For localhost: http://localhost:8080/auction/BMW1/bid
		Param Desc: BMW1-->Itemcode. Can be retreived by running GET API.
		Req Body: {"bidAmount":1000}
		Note: Amount should be greater or equal to last/maxbid/base price.

Build and deployment:
      To test as standalone: Navigate to parent dir of Auction proj and run 'mvn spring-boot:run'
                      Desc: As it is developed in spring boot, tomcat comes by default. WAR file will get deployed by default. Localhost URL mentioned in documentation section.
      To deploy in AWS EC2 tomcat: Have written yaml script as Actions. Whenever commit happens in GIT, it will build and deploy in tomcat server. Actual URL mentioned in documentation section.
