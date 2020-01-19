# AMT - Project Two : Stalkerlog

## Description

A simple Spring application that exposes a REST API allowing users to record the cities they know their favourite stars have visited, get a list of the recorded stars and cities and manipulate their recorded visits.

## Navigate the documentation

- **Guidelines** for the project in [this file](doc/Guidelines.md).
- ~~Full description of **what is implemented** in [this file](doc/functionalAspects.md).~~ Sadly, we didn't have time to document the features...
- Description of the **application's architecture** in [this file](doc/architecture.md).
- ~~Description of the **testing strategy** in [this file](doc/testing.md)~~ Unfortunately, we didn't have time to document the tests we have...
- ~~Report for the **performance tests** in [this file](doc/loadTesting.md).~~ Regrettably, we didn't have time to create performance test scenarios...

## How to build the app

To build the app easily, you can use `docker` and `docker-compose`. In order to do so, make sure you are in the `docker/` folder and run the following command.

```
docker-compose up -d --build
```

The main API should then be available at [this address](http://localhost/api) and the auth API at [this address](http://localhost/auth).

**Warning:** a last minute issue that we couldn't fix broke the connection to the db containers from the API containers. Please read the next point to run the APIs.

## Run the APIs without docker

Launching the db containers and then the projects manually from IntelliJ still works... `docker-compose up apidb authdb`

The APIs are then available at [this address](http://localhost:8080/api) and the auth API at [this address](http://localhost:8081/auth).

### Test data

- Data is automatically generated for the `country`, `city` and `user` tables.
- Generation of data for the `star` and `visit` tables is not yet done.

We have one admin user and one simple user defined with the following credentials usable for tests:

- `admin@sl.ch`, `paSSw0rd`
- `notadmin@gm.com`, `Sidney413`

## How to run the tests (Cucumber)

To run the few tests we managed to complete, be sure to have the stalkerlog project and its database running. Then in stalkerlog-specs, do a `mvn clean test`.
