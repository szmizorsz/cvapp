README for cvapp
==========================

1. You need the following datasource configuration depending on the profile (dev / prod) that you run the application with:
- Development profile:
	- Configuration file: src\main\resources\config\application-dev.yml
	- Relational database: H2 - no extra configuration needed
	- NoSQL: MongoDB - properties: spring.data.mongodb
- Production profile: 
	- Configuration file: src\main\resources\config\application-prod.yml
	- Relational database: MySQL - properties: spring.datasource
	- NoSQL: MongoDB - properties: spring.data.mongodb
	
2. You can run the application locally with Maven:
- Development profile: 
	mvn spring-boot:run
- Production profile: 
	mvn -Pprod spring-boot:run
- Application will be available at  http://localhost:8080
- You can log in with default admin / admin

3. Data load:
- Relational: no need to do anything: Liquidbase loads the data at application startup
- NoSQL: you can find collection dumps in src\main\resources\config\mongeez\dump\Data\ that you can load with mongorestore

4. Mail configuration:
- If you want to set up mail notifications you need to define the properties in src\main\resources\config\application.yml
	- spring.mail...