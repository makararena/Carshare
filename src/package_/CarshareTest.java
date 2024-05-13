package package_;

import static package_.CarType.*;
import static package_.PaymentType.*;

public class CarshareTest {
    // price of cars of a given type from the cart
    static int price(Cart k, String CarBrand) {
        Integer suma = 0;
        for (Car car : k.getWishes()) {
            if (car.getBrand().equals(CarBrand)) {
                suma += car.getCost();
            }
        }
        return suma;
    }
    public static void main(String[] args) {

        // price
        Price price = Price.getPrice();

        // adding new prices to the price list
        price.add(PERSONAL, "Syrena", 1.5, 2.5, 100, 1.85);
        // 1.5 PLN for 1 km if the customer has a subscription
        // otherwise: 2.5 zł per 1 km (up to 100 km), 1.85 zł per 1 km (from the 101st kilometer)

        price.add(DELIVERY, "Żuk", 4, 150, 3);
        // 4 PLN per 1 km (up to 150 km),
        // 3 PLN for 1 km (from the 151st kilometer)

        price.add(HISTORIC, "Ford T", 10);
        // 10 PLN for 1 km


        price.add(FREE, 50, "Tuk-Tuk");
        // free ride for subscribers only (up to 50 km)


        // The f1 customer declares an amount of PLN 900 for orders; true means that the customer has a subscription
        Klient f1 = new Klient("f1", 900, true);

        // F1 customer adds different cars to wish list:
        // "Syrena" passenger type Max. 80 km
        // Delivery "Żuk" at max. 200 km,
        // "Lublin" of historical type on Max. 30 km,
        // "Tuk-Tuk" type of free for Max. 60 km (but maybe only up to 50 km).
        f1.add(new Personal("Syrena", 80));
        f1.add(new Delivery("Żuk", 200));
        f1.add(new Historic("Lublin", 30));
        f1.add(new Free("Tuk-Tuk", 60));

        // F1 customer wish list
        WishList listF1 = f1.getWishList();

        System.out.println("Customer wish list " + listF1);

        // Before paying, the customer repackages the cars from the wish list to the cart (after cleaning).
        // It is possible that there are cars on the wish list that do not have a price in the price list,
        // in that case, they wouldn't be in the basket.
        Cart cartF1 = f1.getCart();
        f1.repack();

        // Co jest na liście życzeń klienta f1
        System.out.println("After repackaging, customer f1 wish list: " + f1.getWishList());

        // Co jest w koszyku klienta f1
        System.out.println("After repackaging, customer cart: " + cartF1);

        // Ile wynosi cena wszystkich samochodów typu osobowego w koszyku klienta f1
        System.out.println("The cost of Syrena cars in customer f1's cart: " + price(cartF1, "Syrena"));

        // Klient zapłaci...
        f1.pay(CARD, false);	// pays by debit card, 1% commission
        // true means that in case of insufficient funds, the application will automatically set aside excess kilometers/cars,
        // false means opting out of paying, clearing the cart and wish list

        // How much money does customer f1 have left?
        System.out.println("After payment, customer f1 has: " + f1.getWallet() + " PLN");

        // If the customer had insufficient funds, optionally, cars/kilometers can be set aside,
        // otherwise, the cart is empty after payment
        System.out.println("After payment, customer cart: " + f1.getCart());
        System.out.println("After payment, customer cart: " + cartF1);

        // Now comes the dakar customer,
        // declares 850 PLN for orders
        Klient dakar = new Klient("dakar", 850, false);

        // Ordered too much for that amount
        dakar.add(new Delivery("Żuk", 100));
        dakar.add(new Historic("Ford T", 50));

        // What does customer dakar have on their wish list
        System.out.println("Customer wish list " + dakar.getWishList());

        Cart cartDakar = dakar.getCart();
        dakar.repack();

        // What's on customer dakar's wish list now
        System.out.println("After repackaging, customer dakar wish list: " + dakar.getWishList());

        // And what's in customer dakar's cart
        System.out.println("After repackaging, customer dakar cart: " + dakar.getCart());

        // customer dakar pays
        dakar.pay(TRANSFER, true);	// pays by bank transfer, no commission

        // How much money does customer dakar have left?
        System.out.println("After payment, customer dakar has: " + dakar.getWallet() + " PLN");

        // What's left in customer dakar's cart (not enough money)
        System.out.println("After payment, customer cart: " + cartDakar);

        dakar.refund(DELIVERY, "Żuk", 50);	// refund (to cart) 50 km of delivery "Żuk" from the last transaction

        // How much money does customer dakar have left?
        System.out.println("After refund, customer dakar has: " + dakar.getWallet() + " PLN");

        // What's left in customer dakar's cart
        System.out.println("After refund, customer cart: " + cartDakar);

    }
}
