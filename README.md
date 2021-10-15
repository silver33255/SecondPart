# SecondPart
## Task 7. SpringSecurity
`Description`\
Rework your pet project using Spring Security. \
Add authorization for users with different roles (use at least two roles - ROLE_USER, ROLE_ADMIN).\
Project should contain pages with restricted access (“only for admins” and “only for users”).\
If you don’t have such segregation in your project right now - add some new pages and restrict access to them.\
In your SecurityConfiguration use jdbc as a datasource for users and roles.

## Task 8. SpringTesting
`Description`
1.	Choose controller and write unit test for it
2.	Choose controller and write IT for it
3.	Choose service and write IT for it
4. To populate your DB use EntityManager.

## Task9. Cloud
#### Homework
`git clone https://gitlab.com/pigorv/spring-cloud-overview`

`cd spring-cloud-overview`

`git checkout homework`

Add the project to your repository with changes written below.
#### Notification server
* Run service at `localhost:8484`
* Notification service should be registered in Eureka Server with `notifications` service id
* Add route to API Gateway. `localhost:8080/notifications/**` should be routed to `notfication` service running at port `8484` 
* Add Spring MVC annotations and some logic to `NotificationController.class` to handle GET and POST request.
    * POST - adds Notification for given user to list  
    * GET - returns collection of notifications  

#### Order service
* Rewrite `OrdersController.createNewOrder()` to use `FeignClient's` instead of `RestTeamplate`.
* Add POST HTTP call in `OrdersController.createNewOrder()` to `notification` service using `FeignClient`

#### Success criteria
As a homework-checker I should:

* Run services: `eureka-server`, `notification`, `orders`, `products`, `users` and `gateway`

* Create user 
``` bash
curl -X POST http://localhost:8080/users
```
* Create product 
``` bash
curl -X POST http://localhost:8080/products
```
* Create order 
``` bash
curl --url http://localhost:8080/orders \
     -H "Content-Type: application/json" \
     -d '{"userName": "{сreatedUserName}", "product": "{сreatedProductName}"}'
```
* `OrdersController.class` in `orders` module should have no `RestTamplate` and should user Feign interfaces to make HTTP calls.

* Check is `notification` service have handled POST request
``` bash
curl -X GET http://localhost:8080/notifications
Response should look like: [{"user":"{сreatedUserName}","notifyBy":"EMAIL"}]%  
```

 * Call `notification` service directly without API Gateway should responds with the same result.
``` bash
curl -X GET http://localhost:8484/
Response should look like: [{"user":"{сreatedUserName}","notifyBy":"EMAIL"}]%  
```

## Task 10, 11. ORM
`Description`\
Based on example 5:\
You need to create CRUD service with Entities, DTO, Converter, Repositories. 

1. Use your domain model.
2. Use UUID as identifiers.

3. Required relationships: \
Bidirectional  One-To-Many (Like in Tourist <--> Journey)\
Unidirectional Many-To-One (Like in Journey <-- Country )

4. There should be Integrational Tests for each operation: Create, Read, Update, Delete
