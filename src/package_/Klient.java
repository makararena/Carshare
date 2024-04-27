package package_;

import static package_.SamochodTyp.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class Klient {
    String imie;
    Double kwota;
    Boolean posiadaAbonement;
    ArrayList<Samochod> zyczenia = new ArrayList<>();

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
    public void zaplac(PaymentType type, boolean bullshit){
        Koszyk koszyk = pobierzKoszyk();
        int cenaKoszyka = cenaKoszyka(koszyk); // Calculate the total price of the shopping cart
        Double prowizja = 0.0; // Initialize commission

        if (type == PaymentType.KARTA) {
            prowizja = (cenaKoszyka * 0.01); // Calculate commission if paying by card
        }

        Double doZaplaty = cenaKoszyka + prowizja; // Total amount to pay

        if (type == PaymentType.KARTA) {
            // Handle cash payment
            if (kwota >= doZaplaty) {
                kwota -= doZaplaty; // Subtract the amount paid from the client's balance
                // Clear the shopping cart and wishlist
                koszyk.zyczenia.clear();
            } else {
                System.out.println("Niewystarczająca ilość pieniędzy w portfelu.");
            }
        } else if (type == PaymentType.PRZELEW) {
            // Handle payment by transfer
            if (kwota >= doZaplaty) {
                kwota -= doZaplaty; // Subtract the amount paid from the client's balance
                koszyk.zyczenia.clear();
            } else {
                System.out.println("Niewystarczająca ilość pieniędzy w portfelu.");
            }
        }
    }

    // Helper method to calculate the total price of the shopping cart
    private int cenaKoszyka(Koszyk koszyk) {
        int suma = 0;
        for (Samochod samochod : koszyk.zyczenia) {
            suma += samochod.getPrice();
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
                if (samochod.getBasicPrice() != null) goToKoszyk.add(samochod);
                else arrStayInLista.add(samochod);
            }
            this.zyczenia = arrStayInLista;
            this.koszyk = new Koszyk(goToKoszyk, this.imie);
        }
        return this.koszyk;
    }
    public void przepakuj(){

    }

    public void zwroc(SamochodTyp typ, String marka, int distance){};

    public String pobierzPortfel() {
        return df.format(this.kwota);
    };
}
