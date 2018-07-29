### Environment Setup

#### Windows
Create bat files under system `%PATH%`

##### mi.bat
```
mvn install -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false %*
```

##### mc.bat
```
mvn clean -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false %*
```

##### ms.bat

```
mvn spring-boot:run -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false %*
```


#### MacOS/Linux
```
cat ~/.bash_profile

alias mc="mvn clean -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
alias mi="mvn install -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
alias ms="mvn spring-boot:run -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
```

### Running
mc && ms -Pdev-h2


### CORS Testing
```

https://www.test-cors.org/#?client_method=POST&client_credentials=true&client_headers=accept%3A%20application%2Fjson%3Bcharset%3DUTF-8%0Aorigin%3A%20http%3A%2F%2Flocalhost%3A8080%0AContent-Type%3A%20application%2Fjson%0AX-Auth-Token%3A%20Bearer%20eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6ImQ0MGNlZDAwLTk0ZjktNGE4ZC04MjlhLTJjM2FkNWJmNThlOSIsInNjb3BlcyI6ImEsYixjIiwiaXNzIjoibWlubGlhLmNvbSIsImlhdCI6MTUzMjYyMzE5NiwiZXhwIjoxNTM1MjE1MTk2fQ.wF4OLvzm1Pjzt3zbVxrCL_uwhDScp1ULfz3kSY7UbDLa__phoYGDHrs5-W8wZVi1gj09Y8HI5lGNQ1Zk6bPg2A%0A&client_postdata=%7B%20%22name%22%3A%20%22string%22%7D&server_url=http%3A%2F%2Flocalhost%3A7001%2Fapi%2Fv1%2Fqueen%2Fcount&server_enable=true&server_status=200&server_credentials=false&server_tabs=remote
```