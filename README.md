### Deploy Steps

#### Test
* Test - `mvn clean test`
* Sonar - `mvn install verify sonar:sonar`
#### Install
`mvn install`

#### Deploy 
* WAR  - `mvn clean package` 
* Docker - `mvn clean package docker:build`


### Configs

#####In Application.properties

`feed.feedSource `: `https://www1.cbn.com/app_feeds/rss/news/rss.php?section=world` \
`feed.retrievalSize` :`10` \
`cron.expression` : `0 */5 * * * ?`

###End Point
`<host>/api/news`\
`<host>/api/news?size=<size>`\
** Note that running port for docker is changed to 80


#### Reference
* <a>https://start.spring.io
* <a>https://spring.io
* <a>https://stackoverflow.com
* <a>docs.sonarqube.org
* <a>community.sonarsource.com
* <a>medium.com
* <a>codefresh.io


