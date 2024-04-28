package package_;

import static package_.SamochodTyp.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
public class Klient {
    private String imie;
    private Double kwota;
    private Boolean posiadaAbonement;
    private ArrayList<Samochod> zyczenia = new ArrayList<>();
    private ArrayList<Samochod> lastTransaction;


    // https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/
    private static final DecimalFormat df = new DecimalFormat("0.00");

    Koszyk koszyk;
    public Klient(String imie, Integer kwota, Boolean posiadaAbonement) {
        this.imie = imie;
        this.kwota = (double) kwota;
        this.posiadaAbonement = posiadaAbonement;
    }
    public void dodaj(Samochod samochod){
        samochod.setPosiadaAbonement(posiadaAbonement);
        this.zyczenia.add(samochod);
    }
    public void zaplac(PaymentType type, boolean wantPayByParts){
        lastTransaction = new ArrayList<>();
        Koszyk koszyk = pobierzKoszyk();
        int cenaKoszyka = cenaKoszyka(koszyk); // Calculate the total price of the shopping cart
        Double prowizja = 0.0; // Initialize commission

        if (type == PaymentType.KARTA) {
            prowizja = cenaKoszyka * 0.01; // Calculate commission if paying by card
        }

        Double doZaplaty = cenaKoszyka + prowizja; // Total amount to pay

        // Handle payment by cash or transfer
        if (kwota >= doZaplaty) {
            kwota -= doZaplaty; // Subtract the amount paid from the client's balance]
            ArrayList<Samochod> zyczenia = koszyk.getZyczenia();
            lastTransaction.addAll(zyczenia);
            koszyk.getZyczenia().clear(); // Clear the shopping cart and wishlist

        } else if (wantPayByParts) {
            koszyk.getZyczenia().sort(Comparator.comparingDouble(Samochod::getPrice).reversed());
            for (Samochod samochod : koszyk.getZyczenia()) {
                Double cenaSamochodu = samochod.getPrice() * samochod.getDistance();
                if (kwota >= cenaSamochodu) {
                    kwota -= cenaSamochodu;
                    if (samochod instanceof Osobowy) {
                        lastTransaction.add(new Osobowy((Osobowy) samochod));
                    } else if (samochod instanceof Dostawczy) {
                        lastTransaction.add(new Dostawczy((Dostawczy) samochod));
                    } else if (samochod instanceof Zabytkowy) {
                        lastTransaction.add(new Zabytkowy((Zabytkowy) samochod));
                    } else if (samochod instanceof Darmo) {
                        lastTransaction.add(new Darmo((Darmo) samochod));
                    }
                    samochod.setDistance(0);
                } else {
                    int distanceCanBeCovered = (int) Math.floor(kwota / samochod.getPrice());
                    kwota -= distanceCanBeCovered * samochod.getPrice();
                    if (samochod instanceof Osobowy) {
                        lastTransaction.add(new Osobowy((Osobowy) samochod));
                    } else if (samochod instanceof Dostawczy) {
                        lastTransaction.add(new Dostawczy((Dostawczy) samochod));
                    } else if (samochod instanceof Zabytkowy) {
                        lastTransaction.add(new Zabytkowy((Zabytkowy) samochod));
                    } else if (samochod instanceof Darmo) {
                        lastTransaction.add(new Darmo((Darmo) samochod));
                    }
                    samochod.setDistance(samochod.getDistance() - distanceCanBeCovered);
                    break; // Stop processing items once payment is made
                }
            }
            koszyk.getZyczenia().removeIf(samochod -> samochod.getDistance() <= 0); // Remove cars with zero distance remaining
        } else {
            System.out.println("Niewystarczająca ilość pieniędzy w portfelu.");
        }
    }


    // Helper method to calculate the total price of the shopping cart
    private int cenaKoszyka(Koszyk koszyk) {
        int suma = 0;
        for (Samochod samochod : koszyk.getZyczenia()) {
            suma += samochod.getCost();
        }
        return suma;
    }

    public ListaZyczen pobierzListeZyczen(){
        return new ListaZyczen(imie, zyczenia);
    }

    public Koszyk pobierzKoszyk(){
        if (this.koszyk == null){
            ArrayList<Samochod> arrStayInLista = new ArrayList<>();
            ArrayList<Samochod> goToKoszyk = new ArrayList<>();
            for (Samochod samochod : zyczenia){
                if (samochod.getPrice() != null) goToKoszyk.add(samochod);
                else arrStayInLista.add(samochod);
            }
            this.zyczenia = arrStayInLista;
            this.koszyk = new Koszyk(goToKoszyk, this.imie);
        }
        return this.koszyk;
    }
    public void przepakuj(){

    }

    public void zwroc(SamochodTyp typ, String marka, int distance) {
        for (Samochod samochod : lastTransaction) {
            // Check if the current samochod matches the criteria
            if (samochod.getType() == typ && samochod.getMark().equals(marka) && samochod.getDistance() >= distance) {
                // Calculate the refund amount based on the price of the samochod
                double refundAmount = samochod.getPrice();
                // Refund the amount to the kwota
                kwota += (refundAmount * distance);

                // Check if samochod with the same marka exists in the koszyk
                boolean foundInKoszyk = false;
                for (Samochod koszykSamochod : koszyk.getZyczenia()) {
                    if (koszykSamochod.getMark().equals(marka)) {
                        foundInKoszyk = true;
                        koszykSamochod.setDistance(koszykSamochod.getDistance() + distance);
                        break;
                    }
                }

                // If not found in koszyk, add samochod with the updated distance
                if (!foundInKoszyk) {
                    samochod.setDistance(distance);
                    koszyk.getZyczenia().add(samochod);
                }

                // Remove the samochod from lastTransaction
                lastTransaction.remove(samochod);
                return;
            }
        }
        System.out.println("No matching samochod");
    }

    public String pobierzPortfel() {
        return df.format(this.kwota);
    };

    public String getImie() {
        return imie;
    }
    public Boolean getPosiadaAbonement() {
        return posiadaAbonement;
    }

    public ArrayList<Samochod> getZyczenia() {
        return zyczenia;
    }

    public Koszyk getKoszyk() {
        return koszyk;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setKwota(Double kwota) {
        this.kwota = kwota;
    }

    public void setPosiadaAbonement(Boolean posiadaAbonement) {
        this.posiadaAbonement = posiadaAbonement;
    }

    public void setZyczenia(ArrayList<Samochod> zyczenia) {
        this.zyczenia = zyczenia;
    }

    public void setKoszyk(Koszyk koszyk) {
        this.koszyk = koszyk;
    }

}
