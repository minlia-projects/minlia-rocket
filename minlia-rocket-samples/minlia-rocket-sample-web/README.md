### Environment Setup

#### Setup JDK 8

#### Setup Maven

#### On Windows Like OS
Create these batch files under the system environment variable `%PATH%`, then we can execute the following commands everywhere

```
mc
mi
ms
```

##### mi.bat
```
mvnw install -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false %*
```

##### mc.bat
```
mvnw clean -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false %*
```

##### ms.bat

```
mvnw spring-boot:run -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false %*
```


#### On MacOS or Linux OS 
```
~/.bashrc and ~/.bash_profile are scripts that might be executed when bash is invoked. The ~/.bashrc file gets executed when you run bash using an interactive shell that is not a login shell. The ~/.bash_profile only gets executed during a login shell. What does this all mean? The paragraphs below explains interactive shells, login shells, .bashrc, .bash_profile and other bash scripts that are executed during login.
```

Create alias in bash_profile, then we can execute these commands everywhere.

```
cat ~/.bash_profile

alias mc="mvnw clean -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
alias mi="mvnw install -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
alias ms="mvnw spring-boot:run -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"

source ~/.bash_profile
```

### Running
mc && ms -Pdev-h2


### CORS Testing
```

https://www.test-cors.org/#?client_method=POST&client_credentials=true&client_headers=accept%3A%20application%2Fjson%3Bcharset%3DUTF-8%0Aorigin%3A%20http%3A%2F%2Flocalhost%3A8080%0AContent-Type%3A%20application%2Fjson%0AX-Auth-Token%3A%20Bearer%20eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6ImQ0MGNlZDAwLTk0ZjktNGE4ZC04MjlhLTJjM2FkNWJmNThlOSIsInNjb3BlcyI6ImEsYixjIiwiaXNzIjoibWlubGlhLmNvbSIsImlhdCI6MTUzMjYyMzE5NiwiZXhwIjoxNTM1MjE1MTk2fQ.wF4OLvzm1Pjzt3zbVxrCL_uwhDScp1ULfz3kSY7UbDLa__phoYGDHrs5-W8wZVi1gj09Y8HI5lGNQ1Zk6bPg2A%0A&client_postdata=%7B%20%22name%22%3A%20%22string%22%7D&server_url=http%3A%2F%2Flocalhost%3A7001%2Fapi%2Fv1%2Fqueen%2Fcount&server_enable=true&server_status=200&server_credentials=false&server_tabs=remote
```