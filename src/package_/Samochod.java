package package_;

public abstract class Samochod {
    String mark;
    Integer distance;
    private Boolean posiadaAbonement;

    Samochod(String mark, Integer distance){
        this.mark = mark;
        this.distance = distance;
    }
    public abstract String toString();

    public abstract Double getBasicPrice();
    public abstract Integer getPrice();

    public void setPosiadaAbonement(Boolean posiadaAbonement) {
        this.posiadaAbonement = posiadaAbonement;
    }

    public Boolean getPosiadaAbonement() {
        return posiadaAbonement;
    }
}
