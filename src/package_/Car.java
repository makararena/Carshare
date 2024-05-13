package package_;

public abstract class Car {
    private String brand;
    private Integer distance;
    private Boolean hasAbonement;
    private CarType type;

    Car(String brand, Integer distance, CarType type){
        this.brand = brand;
        this.distance = distance;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public abstract String toString();

    public abstract Double getPrice();
    public abstract Integer getCost();

    public void setHasAbonement(Boolean hasAbonement) {
        this.hasAbonement = hasAbonement;
    }

    public Boolean getHasAbonement() {return hasAbonement;}

    public CarType getType() {
        return type;
    }
}
