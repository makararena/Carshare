# Carshare

## Project Description
This project simulates a simplified process of ordering and paying for car rentals in a company that rents cars by kilometers. The application allows customers to register, declare the amount of money they want to spend on rentals, choose cars from different types, and place them on their wishlist. Customers can then finalize their orders by moving cars from the wishlist to the basket and paying for its content.

The company offers various types of cars, each with its brand/model and type (e.g., personal, delivery, historic, etc.). Cars can be ordered for a certain distance measured in kilometers, and prices for rentals are listed in the price list. Customers can modify the price list at any time.

Customers, after registration, receive identifiers and declare the amount of money they want to spend on rentals. Customers can optionally have a subscription with the company. They select cars and place them on their wishlist. Then, possibly after some time, to finalize the order, they move cars to the basket (after cleaning it) and pay for its content. Each customer can have only one wishlist and one basket. Customers can pay by card or bank transfer. After payment, the customer has the option to return (only from the last transaction) some or all of the unused kilometers.

The application should be written in a way that the provided sample program `CarshareTest` produces similar results as described below:

## Program Output Example:
```
Customer wish list f1
Syrena, type: personal, how many: 80 km, price 1.50
Żuk, type: delivery, how many: 200 km, 4.00 (until 150), 3.00 (from 151)
Lublin, type: historic, how many: 30, no price
Tuk-Tuk, type: free, how many: 50, price 0.00

After repackaging, customer f1 wish list: f1
Lublin, type: historic, how many: 30, no price

After repackaging, customer cart: f1:
Syrena, type: personal, how many: 80 km, price 1.50
Żuk, type: delivery, how many: 200 km, 4.00 (until 150), 3.00 (from 151)
Tuk-Tuk, type: free, how many: 50, price 0.00

The cost of Syrena cars in customer f1's cart: 120
After payment, customer f1 has: 21.30 PLN
After payment, customer cart: f1 -- blank

After payment, customer cart: f1 -- blank

Customer wish list dakar
Żuk, type: delivery, how many: 100 km, price 4.00
Ford T, type: historic, how many: 50, price: 10.00

After repackaging, customer dakar wish list: dakar -- blank

After repackaging, customer dakar cart: dakar:
Żuk, type: delivery, how many: 100 km, price 4.00
Ford T, type: historic, how many: 50, price: 10.00

After payment, customer dakar has: 2.00 PLN
After payment, customer cart: dakar:
Żuk, type: delivery, how many: 13 km, price 4.00

After refund, customer dakar has: 202.00 PLN
After refund, customer cart: dakar:
Żuk, type: delivery, how many: 63 km, price 4.00
```

## Java Technologies Used:
- Classes/interfaces
- Inheritance
- Collections
- Streams
