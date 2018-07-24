# Minlia Rocket  
Minlia rocket is a set of libraries that makes it easy to development enterprise platform.

# Dry

# Powered by Spring&reg; Products

# Api problem conception 

# Api code conception

# Stateful body conception

# Quick start

```
<parent>
  <groupId>com.minlia.rocket</groupId>
  <artifactId>minlia-rocket-starter-parent</artifactId>
  <version>2.0.3.RELEASE</version>
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
