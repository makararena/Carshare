package package_;

import java.text.DecimalFormat;

public class Osobowy extends Samochod {
    private Double priceWithAbonement;
    private Double priceWithoutAbonement;
    private Double distanceWithBasicPrice;
    private Double priceAfterXKm;
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Osobowy(String mark, Integer distance) {
        super(mark, distance, SamochodTyp.OSOBOWY);
        String[] values = Cennik.getMemory().get(mark);
        if (values != null) {
            this.priceWithAbonement = Double.parseDouble(values[1]);
            this.priceWithoutAbonement = Double.parseDouble(values[2]);
            this.distanceWithBasicPrice = Double.parseDouble(values[3]);
            this.priceAfterXKm = Double.parseDouble(values[4]);
        }
    }
    public Osobowy(Osobowy osobowy) {
        super(osobowy.getMark(), osobowy.getDistance(), osobowy.getType()); // Call the superclass constructor to copy common fields
        this.priceWithAbonement = osobowy.getPriceWithAbonement(); // Copy priceWithAbonement
        this.priceWithoutAbonement = osobowy.getPriceWithoutAbonement(); // Copy priceWithoutAbonement
        this.distanceWithBasicPrice = osobowy.getDistanceWithBasicPrice(); // Copy distanceWithBasicPrice
        this.priceAfterXKm = osobowy.getPriceAfterXKm(); // Copy priceAfterXKm
    }
    @Override
    public String toString() {
        return super.getMark() + ", typ: " + super.getType().toString().toLowerCase() + ", ile: " + super.getDistance() + " km, cena " + df.format(priceWithAbonement);
    }
    public Double getPriceAbonement() {
        return this.priceWithAbonement;
    }
    @Override
    public Double getPrice() {
        return this.priceWithoutAbonement;
    }
    @Override
    public Integer getCost() {
        double cena;
        if (super.getDistance() > distanceWithBasicPrice) {
            int kmOver = (int) (super.getDistance() - distanceWithBasicPrice);
            cena = (kmOver * priceAfterXKm) + (distanceWithBasicPrice * (super.getPosiadaAbonement() ? priceWithAbonement : priceWithoutAbonement));
        } else {
            cena = super.getDistance() * (super.getPosiadaAbonement() ? priceWithAbonement : priceWithoutAbonement);
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
    public void setPriceWithAbonement(Double priceWithAbonement) {
        this.priceWithAbonement = priceWithAbonement;
    }

    public void setPriceWithoutAbonement(Double priceWithoutAbonement) {
        this.priceWithoutAbonement = priceWithoutAbonement;
    }

    public void setDistanceWithBasicPrice(Double distanceWithBasicPrice) {
        this.distanceWithBasicPrice = distanceWithBasicPrice;
    }

    public void setPriceAfterXKm(Double priceAfterXKm) {
        this.priceAfterXKm = priceAfterXKm;
    }
}
