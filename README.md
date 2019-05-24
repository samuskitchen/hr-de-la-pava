# Sales

It's the KometSales Technical Test

## Installation

The project uses gradle to automate the construction of the project, please download the dependencies and then execute the following commands

Windows

```bash
gradlew clean build
``` 
Linux/MAC

```bash
./gradlew clean build
``` 

## Flyway
The project uses flyway for the generation of the tables and insert of configuration to be able to connect it to the local database change in the appication.properties DataSource settings, but before that a database must be created

## Paths

##### Get all sellers
##### GET
```bash
/api/sales/seller/findall/
```

##### Get seller by id
##### GET
```bash
/api/sales/seller/{id}
```

##### Load the sales
##### POST
```bash
/api/sales/load
```



## Healt
The health was added to be able to verify the status of the api

##### Check the status of the api
##### GET
```bash
/api/sales/actuator/health
```

## PostMan

It shares the path of the api by postman to generate ease at the time of testing

https://www.getpostman.com/collections/2065dd35f4d7a9ab0753