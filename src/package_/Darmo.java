package package_;

import java.text.DecimalFormat;

public class Darmo extends Samochod {
    private Integer maxDistance;
    private Double price;
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Darmo(String mark, Integer distance) {
        super(mark, distance, SamochodTyp.DARMO);
        String[] values = Cennik.getMemory().get(mark);
        if (values != null) {
            this.maxDistance = Integer.parseInt(values[1]);
        }
        if (this.maxDistance < super.getDistance()){
            super.setDistance(maxDistance);
        }
        this.price = 0.00;
    }
    //https://www.baeldung.com/java-deep-copy
    public Darmo(Darmo darmo) {
        super(darmo.getMark(), darmo.getDistance(), darmo.getType()); // Call the superclass constructor to copy common fields
        this.maxDistance = darmo.getMaxDistance(); // Copy maxDistance
        this.price = darmo.getPrice(); // Copy price
    }

    @Override
    public String toString() {
        return super.getMark() + ", typ: " + super.getType().toString().toLowerCase() + ", ile: " + super.getDistance() + ", ceny " + df.format(this.price);
    }

    public Double getPrice() {
        return this.price;
    }
    @Override
    public Integer getCost(){
        return 0;
    }
    public Integer getMaxDistance() {
        return maxDistance;
    }
    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
