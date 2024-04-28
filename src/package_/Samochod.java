package package_;

public abstract class Samochod {
    private String mark;
    private Integer distance;
    private Boolean posiadaAbonement;

    private SamochodTyp type;

    Samochod(String mark, Integer distance, SamochodTyp type){
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

    public void setPosiadaAbonement(Boolean posiadaAbonement) {
        this.posiadaAbonement = posiadaAbonement;
    }

    public Boolean getPosiadaAbonement() {
        return posiadaAbonement;
    }

    public SamochodTyp getType() {
        return type;
    }

    public void setType(SamochodTyp type) {
        this.type = type;
    }
}
