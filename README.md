# Spring Boot REST API with H2 Database

A small example in the form of a note-taking application built using Spring Boot for the REST API with H2 for a database. Demonstrates elementary CRUD operations in response to HTTP as well as returning JSON.

#### !!! TODO:  FULL TESTS !!!

## Getting Started


### Installing
Make sure to edit your ```application.properties``` and change the database information to reflect where you would like H2 to create the DB file.
It is currently set to the directly from which you are compiling the application.
```sh
spring.datasource.url=jdbc:h2:file:./notesDB
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=berti
spring.datasource.password=
```
To test out this application, you need Maven to build the dependencies.

- First, install the dependencies

```sh
mvn clean install
```
### Running
- Second, run the production build with live reload
```sh
mvn spring-boot:run
```
When the application is first built, it will create a database file in the directory specified in the ```application.properties``` file. 

## * Testing *

### Maven Tests
```
mvn test
```
### Curl Tests

The notes API lives at the route ```/api/notes```. If your application is running on localhost:8080, you would access the API via http://localhost:8080/api/notes.
```json
{
 "id" : 1,
 "body" : "Ask Larry about the TPS reports."
}
```
To create a new note, post a JSON payload to the API endpoint as modeled below:
```curl
POST /api/notes
BODY a note
```
Returns: a saved note...
Example
```curl
curl -i -H "Content-Type: application/json" -X POST -d '{"body" : "Pick up milk!"}' http://localhost:8080/api/notes
```
Returns:
```json
{
 "id" : 2,
 "body" : "Pick up milk!"
}
```
Get a note using an API call:
```
GET /api/notes/{id}
```
Returns: the requested note..
Example:
```curl
curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes/1
```
Returns:
```json
{
 "id" : 1,
 "body" : "Ask Larry about the TPS reports."
}
```
I can get all notes using an API call:
```
GET /api/notes
```
Returns: A list of my notes

Example:

```
curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes
```
Returns:
```json
[
 {
 "id" : 2,
 "body" : "Pick up milk!"
 },
 {
 "id" : 1,
 "body" : "Ask Larry about the TPS reports."
 }
]
```
To search notes by their bodies, use the 'query' parameter in the GET request
Example:
```curl
curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes?query=milk
```
Returns a list of every note with the word 'milk' in it.

To delete an individual note use the endpoint ```/api/notes/delete``` with a parameter of 'id' signifying the note you wish to remove.
```curl
POST /api/notes/delete
ID int value of id to delete
```
Returns: the updated list of notes...
Example
```curl
curl -i -H "Content-Type: application/json" -X POST  http://localhost:8080/api/notes/delete?id=1
```

## Built With


* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Quick start Spring Framework web applications
* [H2 Database Engine](https://h2database.com/) - the Java SQL database
* [JSON.simple](https://github.com/fangyidong/json-simple) -JSON.simple is a simple Java toolkit for JSON



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

