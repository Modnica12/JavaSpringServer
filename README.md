# RESTful Web Server Spring Framework

## Example:

Run Server **in terminal**: mvn tomcat7:run

-GET Request: http://localhost:8080/pizzas

-POST Request: **in terminal**
    curl -v -X POST http://localhost:8080/pizza -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"pizzaName":"Cheesy","doughtType":"0","size":35}'

-PUT Request: **in terminal**
    curl -v -X PUT http://localhost:8080/pizza -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"pizzaName":"Cheesy","doughtType":"0","size":35}'

-DELETE Request **in terminal**
    curl -v -X DELETE http://localhost:8080/pizza/0
