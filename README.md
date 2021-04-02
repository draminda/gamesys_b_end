### Deploy Steps

#### Test
* Test - `mvn clean test`
* Sonar - `mvn install verify sonar:sonar`
#### Install
`mvn install`

#### Deploy 
* WAR  - `mvn clean package` 
* Docker - `mvn clean package docker:build`
