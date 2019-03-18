package ru.job4j.controller;

import ru.job4j.service.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlaceHolder {

    private List<Place> placeList = new ArrayList<>();
    private Map<Integer, Place> placeMap = new ConcurrentHashMap<>();

    private static PlaceHolder instance = new PlaceHolder();
    public static PlaceHolder getInstance() {
        return instance;
    }

    /**
     * Заполняем все места в кинотеатре. Стоимость среднего ряда дороже.
     */
    private PlaceHolder() {
        for (int i = 0; i < 9; i++) {
            if (i > 2 && i < 6) {
                placeMap.put(i, new Place(i, 300));
            } else {
                placeMap.put(i, new Place(i, 200));
            }
        }
        placeMap.get(5).setCondition(true);
    }

    public Map<Integer, Place> getPlaceMap() {
        return placeMap;
    }

    public boolean getPlace(int placeNumber) {
        boolean result = false;
        if (!this.placeMap.get(placeNumber).isCondition()) {
            this.placeMap.get(placeNumber).setCondition(true);
            result = true;
        }
        return result;
    }


}
