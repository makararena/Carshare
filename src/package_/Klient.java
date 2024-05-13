package package_;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Klient {
    private String name;
    private Double amount;
    private Boolean hasAbonement;
    private ArrayList<Car> wishes = new ArrayList<>();
    private ArrayList<Car> lastTransaction;
    private Cart cart;

    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // Simple Klient constructor
    public Klient(String name, Integer amount, Boolean hasAbonement) {
        this.name = name;
        this.amount = (double) amount;
        this.hasAbonement = hasAbonement;
    }
    // Adding car to wishes
    public void add(Car car){
        car.setHasAbonement(hasAbonement);
        this.wishes.add(car);
    }
    // Paying for a car
    public void pay(PaymentType type, boolean wantPayByParts){
        lastTransaction = new ArrayList<>();
        Cart cart = getCart();
        int cartPrice = calculateCartPrice(cart);

        // Adding commission if needed
        Double commission = 0.0;
        if (type == PaymentType.CARD) {
            commission = cartPrice * 0.01;
        }
        Double toPay = cartPrice + commission;

        // Handling the case when we have enough money to pay for all cars
        if (amount >= toPay) {
            amount -= toPay;
            ArrayList<Car> wishes = cart.getWishes();
            lastTransaction.addAll(wishes);
            cart.getWishes().clear();
        }
        // Handling the case when we need to pay by parts
        else if (wantPayByParts) {
            cart.getWishes().sort(Comparator.comparingDouble(Car::getPrice).reversed());
            for (Car car : cart.getWishes()) {
                Double carPrice = car.getPrice() * car.getDistance();
                // if we have enough money to pay for the car fully
                if (amount >= carPrice) {
                    amount -= carPrice;
                    addTransaction(car);
                    car.setDistance(0);
                }
                // if we have enough money to pay only for some distance (need to be called only once)
                else {
                    int distanceToCover = (int) Math.floor(amount / car.getPrice());
                    amount -= distanceToCover * car.getPrice();
                    addTransaction(car);
                    car.setDistance(car.getDistance() - distanceToCover);
                    break;
                }
            }
            // Remove cars if we have paid for them
            cart.getWishes().removeIf(car -> car.getDistance() <= 0);
        }
        // Handling the case when we don't have enough money and we don't want to pay by parts
        else {
            System.out.println("Insufficient funds in the wallet.");
        }
    }
    //  Helper function to avoid code redundancy
    private void addTransaction(Car car) {
        if (car instanceof Personal) {
            lastTransaction.add(new Personal((Personal) car));
        } else if (car instanceof Delivery) {
            lastTransaction.add(new Delivery((Delivery) car));
        } else if (car instanceof Historic) {
            lastTransaction.add(new Historic((Historic) car));
        } else if (car instanceof Free) {
            lastTransaction.add(new Free((Free) car));
        }
    }

    // Helper method to calculate the total price of the Cart
    private int calculateCartPrice(Cart cart) {
        int total = 0;
        for (Car car : cart.getWishes()) {
            total += car.getCost();
        }
        return total;
    }

    // Getter for wish list
    public WishList getWishList(){
        return new WishList(name, wishes);
    }

    // Getter for Cart
    public Cart getCart(){
        if (this.cart == null){
            ArrayList<Car> carsInList = new ArrayList<>();
            ArrayList<Car> carsInCart = new ArrayList<>();
            for (Car car : wishes){
                if (car.getPrice() != null) carsInCart.add(car);
                else carsInList.add(car);
            }
            this.wishes = carsInList;
            this.cart = new Cart(carsInCart, this.name);
        }
        return this.cart;
    }

    // Sorry, I added all the logic in the getCart method.
    public void repack(){}

    public void refund(CarType type, String brand, int distance) {
        for (Car car : lastTransaction) {
            if (car.getType() == type && car.getBrand().equals(brand) && car.getDistance() >= distance) {
                double refundAmount = car.getPrice();
                amount += (refundAmount * distance);
                boolean foundInCart = false;
                for (Car cartCar : cart.getWishes()) {
                    if (cartCar.getBrand().equals(brand)) {
                        foundInCart = true;
                        cartCar.setDistance(cartCar.getDistance() + distance);
                        break;
                    }
                }
                if (!foundInCart) {
                    car.setDistance(distance);
                    cart.getWishes().add(car);
                }
                lastTransaction.remove(car);
                return;
            }
        }
        System.out.println("No matching car");
    }
    public String getWallet() {
        return df.format(this.amount);
    };
}
