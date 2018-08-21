# Minlia Rocket  
Minlia rocket is a set of libraries that makes it easy to development enterprise platform.  

# Dry  

# Powered by Spring&reg; Products  

# Api problem conception 

# Api code conception  

# Stateful body conception  

# Samples  


### Running by source code  
```
cd minlia-rocket/minlia-rocket-samples/minlia-rocket-sample-web
mvn spring-boot run
http://localhost:7001/swagger-ui.html

```

### Running by thin jar file  

```
mvn clean install
java -Dthin.root=./target -Dserver.port=1025 -Dspring.profiles.active=dev -jar target/minlia-rocket-sample-web.jar
```

# Quick start  

```
<parent>
  <groupId>com.minlia.rocket</groupId>
  <artifactId>minlia-rocket-starter-parent</artifactId>
  <version>2.0.4.RELEASE</version>
  <relativePath/>
</parent>



<dependency>
  <groupId>com.minlia.rocket</groupId>
  <artifactId>minlia-rocket-starter-loggable</artifactId>
</dependency>

<dependency>
  <groupId>com.minlia.rocket</groupId>
  <artifactId>minlia-rocket-starter-problem</artifactId>
</dependency>

<dependency>
<groupId>com.minlia.rocket</groupId>
<artifactId>minlia-rocket-starter-swagger</artifactId>
</dependency>


<dependency>
  <groupId>com.minlia.rocket</groupId>
  <artifactId>minlia-rocket-starter-data-jpa</artifactId>
</dependency>

<dependency>
  <groupId>com.minlia.rocket</groupId>
  <artifactId>minlia-rocket-starter-data-mybatis</artifactId>
</dependency>

```


# Release

```
mvn clean deploy -P release -P release-sign-artifacts
```


# Setup
```
cat ~/.bash_profile

alias mc="mvn clean -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
alias mi="mvn install -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
alias ms="mvn spring-boot:run -DskipITs=true -DskipTests=true -Dmaven.test.skip=true -DdownloadSources=false -DdownloadJavadocs=false $*"
```


