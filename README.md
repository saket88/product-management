# product-management
This project is borken into a collection of microservices. They are as below

Product Catalogue Service 

Microservice that provides the functionality to 
Add a product
Retrieve the list of products based on simple search criteria e.g. product type
Remove a product from the catalogue. 



Pricing Service

Microservice that provides the functionality to 
Return the price for a given product


There are four broad levels of Microservices used
1>RegistrationServer - This is used as a service discovery agent and all the other microservices registers into this.
2>ProductServer- This acts as a microservice for Product Catalogue Service. 
3> ProductPriceServer-This acts as a microservice for Pricing Service.
4>ResourceServer- This microservice provides an dactually wrapper to the functional services.

Inorder to start the application run all the classes mentioned above in the order mentioned above. 

http://localhost:1111/ is url for Eureka discovery server

http://localhost:3333/ acts as a basic url on which other microservices runs their function

There are following functions as below

1>Fetch a product catalog by its type
    eg  http://localhost:3333/products/type/Biscuit (GET)
    
2> Save a Product

    eg http://localhost:3333/product (POST)
    
    Data
        {
"code":"87687676",
 "name":"Parachute",
 "type":"Hair oil"
}

3> Delete a Product 

eg http://localhost:3333/product/1 (DELETE)

4> Find the price of the product by its code
eg http://localhost:3333/productPrice/code/123456789
