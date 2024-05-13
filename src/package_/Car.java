package package_;

public abstract class Car {
    private String mark;
    private Integer distance;
    private Boolean hasAbonement;
    private CarType type;

    Car(String mark, Integer distance, CarType type){
        this.mark = mark;
        this.distance = distance;
        this.type = type;
    }

    public String getMark() {
        return mark;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public abstract String toString();

    public abstract Double getPrice();
    public abstract Integer getCost();

    public void setHasAbonement(Boolean posiadaAbonement) {
        this.hasAbonement = hasAbonement;
    }

    public Boolean getHasAbonement() {return hasAbonement;}

    public CarType getType() {
        return type;
    }
}
