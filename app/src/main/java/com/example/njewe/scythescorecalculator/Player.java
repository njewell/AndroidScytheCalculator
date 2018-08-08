package com.example.njewe.scythescorecalculator;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private  String name;
    private final int popularity;
    private final int stars;
    private final int territory;
    private final int resources;
    private final int structure;
    private final int money;
    private final int total;
    public ArrayList<Object> values;

    public Player(int playerNum){
        this.name = "Player " + playerNum;
        popularity = 0;
        stars = 0;
        territory = 0;
        resources = 0;
        structure = 0;
        money = 0;
        total = 0;
        values = new ArrayList<Object>(Arrays.asList(name, popularity, stars, territory, resources, structure, money, total));
    }

    public int getPopularity() {
        return popularity;
    }

    public int getStars() {
        return stars;
    }

    public int getTerritory() {
        return territory;
    }

    public int getResources() {
        return resources;
    }

    public int getStructure() {
        return structure;
    }

    public int getMoney() {
        return money;
    }

    public int getTotal() {
        return total;
    }

}
