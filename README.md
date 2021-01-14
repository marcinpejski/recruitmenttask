# Recruitment Task

Example application to show how to combine two data values fetched from two different data sources.

##Based on
- JDK 11
- Spring Boot
- maven
- H2
- Mockito

##Example usage
Use GET HTTP method on REST endpoint:

```combination/v1/decimal```

to fetch random combined number

#Requirements
- JDK 11

##Build

```mvn clean install```

###Testing

```mvn verify```

###Skip Integration Tests

```mvn vertify -DskipTests=true```

###Skip Integration Tests

```mvn vertify -DskipUTs=true```

###Skip Unit Tests

```mvn vertify -DskipUTs=true```
