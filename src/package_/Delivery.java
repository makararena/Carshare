package package_;

import java.text.DecimalFormat;

public class Delivery extends Car {
    private Double price;
    private Integer distanceWithBasicPrice;
    private Double priceAfterXKM;
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public Delivery(String brand, int distance){
        super(brand, distance, CarType.DELIVERY);
        String[] values = Price.getMemory().get(brand);
        if (values != null){
            this.price = Double.parseDouble(values[1]);
            this.distanceWithBasicPrice = Integer.parseInt(values[2]);
            this.priceAfterXKM = Double.parseDouble(values[3]);
        }
    }
    public Delivery(Delivery delivery) {
        super(delivery.getBrand(), delivery.getDistance(), delivery.getType());
        this.price = delivery.getPrice();
        this.distanceWithBasicPrice = delivery.getDistanceWithBasicPrice();
        this.priceAfterXKM = delivery.getPriceAfterXKM();
    }
    @Override
    public String toString() {
//        String price = this.price != null ? "price " + String.valueOf(this.price) : " no price";
        if (super.getDistance() <= this.distanceWithBasicPrice) return super.getBrand() + ", type: " + super.getType().toString().toLowerCase() + ", how many: " + super.getDistance() + " km, " + "price " + df.format(this.price);
        return super.getBrand() + ", type: " + super.getType().toString().toLowerCase() + ", how many: " + super.getDistance() + " km, " + df.format(this.price) + " (until " + distanceWithBasicPrice + "), " + df.format(priceAfterXKM) + " (from " + (distanceWithBasicPrice + 1) + ")";
    }

    @Override
    public Integer getCost(){
        int sum = 0;
        if (super.getDistance() > distanceWithBasicPrice){
            int kmOver= super.getDistance() -distanceWithBasicPrice;

            sum += (int) ((kmOver * this.priceAfterXKM) + (distanceWithBasicPrice * this.price));
        }
        else {
            sum += (int) (super.getDistance() * this.getPrice());

        }
        return Integer.valueOf(sum);
    }

    public Double getPrice() {return this.price;}
    public Integer getDistanceWithBasicPrice() {
        return distanceWithBasicPrice;
    }
    public Double getPriceAfterXKM() {
        return priceAfterXKM;
    }
}
