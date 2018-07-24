

## Quick start  

### Run with h2 database  

```
mvn -P h2 -Dspring.profiles.active=dev-h2 spring-boot:run

```


### Run with mysql database  

Please note that you need to change your database configurations to suit your local settings.

```
mvn -P mysql -Dspring.profiles.active=dev-mysql spring-boot:run
```