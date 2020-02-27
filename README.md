# JWT integration with Spring Security

This project demonstrats JWT integration with spring security

  - Generation of JWT with jjwt library
  - Setting up a JWT request filter
  - Extraction of data and validation of JWT

This project is an enhacement from a ready to use spring security project. Following are the steps followed for the process. 

> Details about the JWT can be found here on the official site https://jwt.io/

### Installation
To get started with building and running the application, Open your favorite Terminal and run these commands. No need to provide profile or environment for initial run.

```sh
$ mvn clean install
```
```sh
$ mvn spring-boot:run
```
### Default credentials

The default run is based on in memory H2 database and pre-set up of the default credentails are provided in the `data.sql` file. This will help load initial data.

Following are the credentails can be used to verify things are working well.

```sh
username : vishal
password : test
```
