# RESTful Web Server Spring Framework

## Example:

Run Server **in terminal**: mvn tomcat7:run

Run Auth App from the folder AuthApplication **in terminal**: mvn tomcat7:run

### Commands for list of pizzas:

-GET Request: http://localhost:8080/pizzas

-POST Request: **in terminal**
    curl -v -X POST http://localhost:8080/pizza -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"pizzaName":"Cheesy","doughType":"0","size":35}'

-PUT Request: **in terminal**
    curl -v -X PUT http://localhost:8080/pizza -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"pizzaName":"Cheesy","doughType":"0","size":35}'

-DELETE Request **in terminal**
    curl -v -X DELETE http://localhost:8080/pizza/0

### Commands for Cart:

-GET Request: http://localhost:8080/cart -> Shows all pizzas in cart

-PUT Request: **in terminal**
    curl -v -X PUT http://localhost:8080/cart/add/1 -> Adds pizza with id=1 to cart.

-DELETE Request: **in terminal**
    curl -v -X DELETE http://localhost:8080/cart/remove/1 -> Removes pizza with id=1 from cart.
 
### Commands for Order:

#### !!!!!Works only if you authorized!!!!!

-Put Request: **in terminal**
    curl -v -X PUT http://localhost:8080/order/make?token=[ *your token* ] -> Takes pizzas from cart and creates order. 

-GET Request: http://localhost:8080/order/my?token=[ *your token* ] -> Shows orders of current user. 

-DELETE Request: **in terminal**
    curl -v -X DELETE http://localhost:8080/order/delete/1?token=[ *your token* ] -> Deletes order with id=1.
    
-GET Request: http://localhost:8080/order/all?token=[ *your token* ] -> Shows list of orders.
    
### Commands for Auth:

-GET Request: http://localhost:8080/register -> redirected to auth page

-GET Request: http://localhost:8080/auth -> redirected to auth page

-GET Request: http://localhost:8081/register?username=kerell&password=1234 -> registers user kerell with password 1234.

-GET Request: http://localhost:8081/auth?username=kerell&password=1234 -> Login. You will be redirected to page with token.
