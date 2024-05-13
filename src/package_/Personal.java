package package_;

import java.text.DecimalFormat;

public class Personal extends Car {
    private Double priceWithAbonement;
    private Double priceWithoutAbonement;
    private Double distanceWithBasicPrice;
    private Double priceAfterXKm;
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Personal(String brand, Integer distance) {
        super(brand, distance, CarType.PERSONAL);
        String[] values = Price.getMemory().get(brand);
        if (values != null) {
            this.priceWithAbonement = Double.parseDouble(values[1]);
            this.priceWithoutAbonement = Double.parseDouble(values[2]);
            this.distanceWithBasicPrice = Double.parseDouble(values[3]);
            this.priceAfterXKm = Double.parseDouble(values[4]);
        }
    }
    public Personal(Personal personal) {
        super(personal.getBrand(), personal.getDistance(), personal.getType());
        this.priceWithAbonement = personal.getPriceWithAbonement();
        this.priceWithoutAbonement = personal.getPriceWithoutAbonement();
        this.distanceWithBasicPrice = personal.getDistanceWithBasicPrice();
        this.priceAfterXKm = personal.getPriceAfterXKm();
    }
    @Override
    public String toString() {return super.getBrand() + ", type: " + super.getType().toString().toLowerCase() + ", how many: " + super.getDistance() + " km, price " + df.format(priceWithAbonement);}
    @Override
    public Double getPrice() {return this.priceWithoutAbonement;}
    @Override
    public Integer getCost() {
        double cena;
        if (super.getDistance() > distanceWithBasicPrice) {
            int kmOver = (int) (super.getDistance() - distanceWithBasicPrice);
            cena = (kmOver * priceAfterXKm) + (distanceWithBasicPrice * (super.getHasAbonement() ? priceWithAbonement : priceWithoutAbonement));
        } else {
            cena = super.getDistance() * (super.getHasAbonement() ? priceWithAbonement : priceWithoutAbonement);
        }
        return (int) cena;
    }

    public Double getPriceWithAbonement() {
        return priceWithAbonement;
    }
    public Double getPriceWithoutAbonement() {
        return priceWithoutAbonement;
    }
    public Double getDistanceWithBasicPrice() {
        return distanceWithBasicPrice;
    }
    public Double getPriceAfterXKm() {
        return priceAfterXKm;
    }
}
