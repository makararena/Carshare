package package_;

import java.text.DecimalFormat;

public class Free extends Car {
    private Integer maxDistance;
    private Double price;
    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Free(String mark, Integer distance) {
        super(mark, distance, CarType.FREE);
        String[] values = Price.getMemory().get(mark);
        if (values != null) {this.maxDistance = Integer.parseInt(values[1]);}
        if (this.maxDistance < super.getDistance()){super.setDistance(maxDistance);}
        this.price = 0.00;
    }
    //https://www.baeldung.com/java-deep-copy
    public Free(Free free) {
        super(free.getMark(), free.getDistance(), free.getType());
        this.maxDistance = free.getMaxDistance();
        this.price = free.getPrice();
    }

    @Override
    public String toString() {return super.getMark() + ", type: " + super.getType().toString().toLowerCase() + ", how many: " + super.getDistance() + ", price " + df.format(this.price);}
    public Double getPrice() {return this.price;}
    public Integer getCost(){
        return 0;
    }
    public Integer getMaxDistance() {
        return maxDistance;
    }
}
