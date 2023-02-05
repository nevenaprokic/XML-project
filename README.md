# XML-project

### Subject: XML i veb servisi
### Students: Nataša Laković SW2/2019, Nevena Prokić SW6/2019, Katarina Komad SW28/2019

<br/>
<br/>

# Project setup:

## Requirements:
- JDK version 1.8
- Node.js
- Angular 15+
- Maven
- Spring Boot 2.3.7
- apache-tomme-plus-8.0.13
- apace-jena-fuseki-3.14.0
- exist-4.6.1.war

## Frontend application:
Commands to run in the terminal:
``` 
    npm start
    ng serve
```

## Backend applications:

- in projekat folder run autorskoDelo (http://localhost:8900/xml)
- in projekat folder run patent (http://localhost:8901/xml)
- in projekat folder run zig (http://localhost:8902/xml)
- in projekat folder run korisnik (http://localhost:8903/xml)

## Database:

- add [Apache Jena Fuseki SPARQL server](https://jena.apache.org/download/index.cgi#apache-jena-fuseki)
- add [eXist XML database](https://bintray.com/existdb/releases/exist/4.6.1/view)
- run [apache-tomme](http://tomee.apache.org/download-ng.html)
- link to autorsko delo exist database (http://localhost:8080/existA)
- link to korisnik exist database (http://localhost:8080/exist)
- link to patent exist database (http://localhost:8080/existP)
- link to zig exist database (http://localhost:8080/existZ)
- link to fuseki database (http://localhost:8080/fuseki), you need to create following persistent datasets: AutorskoDelo, Patent and Zig
- Username: admin
- Password: no password needed