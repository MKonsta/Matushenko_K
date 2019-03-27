package ru.job4j.controller;

import ru.job4j.service.Place;

public class TempPlaceHolder {
    private static TempPlaceHolder placeHolder;

    private static final TempPlaceHolder INSTANCE = new TempPlaceHolder();

    private static Place tempPlace = null;

    public static TempPlaceHolder getINSTANCE() {
        return INSTANCE;
    }

    public Place getTempPlace() {
        return tempPlace;
    }

    public void setPlace(Place place) {
        if (tempPlace == null) {
            tempPlace = place;
        }
    }

    public void setPlaceNull() {
        tempPlace = null;
    }

}
