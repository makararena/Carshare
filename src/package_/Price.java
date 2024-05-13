package package_;

import java.util.HashMap;
import java.util.Map;

public class Price {
    private static Price price;
    private static Map<String, String[]> memory;

    private Price() {
        memory = new HashMap<>();
    }

    public static Price getPrice() {
        if (price == null) {
            price = new Price();
        }
        return price;
    }

    // Personal
    public void add(CarType typ, String brand, Double priceWithAbonement, Double priceWithoutAbonement, Integer distanceWithBasicPrice, Double priceAfterXKm) {
        String[] values = {typ.toString(), String.valueOf(priceWithAbonement), String.valueOf(priceWithoutAbonement), String.valueOf(distanceWithBasicPrice), String.valueOf(priceAfterXKm)};
        memory.put(brand, values);
    }

    // Delivery
    public void add(CarType typ, String brand, Integer priceBasic, Integer distanceWithBasicPrice, Integer priceAfterXKM) {
        String[] values = {typ.toString(), String.valueOf(priceBasic), String.valueOf(distanceWithBasicPrice), String.valueOf(priceAfterXKM)};
        memory.put(brand, values);
    }
    // Historic
    public void add(CarType typ, String brand, Integer priceBasic) {
        String[] values = {typ.toString(), String.valueOf(priceBasic)};
        memory.put(brand, values);
    }
    // Free
    public void add(CarType typ, Integer maxDistance, String brand) {
        String[] values = {typ.toString(), String.valueOf(maxDistance)};
        memory.put(brand, values);
    }

    public static Map<String, String[]> getMemory() {
        return memory;
    }

    public static void setPrice(Price price) {
        Price.price = price;
    }

    public static void setMemory(Map<String, String[]> memory) {
        Price.memory = memory;
    }
}

