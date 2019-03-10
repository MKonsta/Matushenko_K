package ru.job4j.servlets.webarchitecturejsp.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CountriesAndCities {

    private static CountriesAndCities cities;

    private static Map<String, List<String>> cityMap = new ConcurrentHashMap<>();

    private static void fillMap() {
        List<String> germany = new ArrayList<>();
        germany.add("Berlin");
        germany.add("Bavaria");
        germany.add("Muhen");
        germany.add("Hamburg");
        cityMap.put("Gemany", germany);

        List<String> italy = new ArrayList<>();
        germany.add("Rim");
        germany.add("Neapol");
        germany.add("Milan");
        cityMap.put("Italy", italy);

        List<String> kyrgyzstan = new ArrayList<>();
        germany.add("Bishkek");
        germany.add("Osh");
        germany.add("Batken");
        germany.add("Karakol");
        cityMap.put("Kyrgyzstan", kyrgyzstan);

        List<String> russia = new ArrayList<>();
        germany.add("Moskow");
        germany.add("Barnaul");
        germany.add("Novosibirsk");
        germany.add("Tumen");
        germany.add("Rostov");
        cityMap.put("Russia", russia);
    }

    public static synchronized CountriesAndCities getCities() {
        if (cities == null) {
            cities = new CountriesAndCities();
            fillMap();
        }
        return cities;
    }

    private CountriesAndCities() {}

    public static Map<String, List<String>> getCityMap() {
        return cityMap;
    }
}
