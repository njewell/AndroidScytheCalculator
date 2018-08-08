package com.example.njewe.scythescorecalculator;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private  String name;
    private int popularity;
    private int stars;
    private int territory;
    private int resources;
    private int structure;
    private int money;
    private int total;
    private int id;
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

    public void setPopularity(int pop){
        popularity = pop;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int star){
        stars = star;
    }

    public int getTerritory() {
        return territory;
    }

    public void setTerritory(int territory){
        this.territory = territory;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resource){
        resources = resource;
    }

    public int getStructure() {
        return structure;
    }

    public void setStructure(int structureBonus){
        structure = structureBonus;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int monies){
        money = monies;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id =id;
    }

    public void setCategory(int category, int value) {
        switch(category){

            case 1:
                setPopularity(value);
                break;
            case 2:
                setStars(value);
                break;
            case 3:
                setTerritory(value);
                break;
            case 4:
                setResources(value);
                break;
            case 5:
                setStructure(value);
                break;
            case 6:
                setMoney(value);
                break;
            case 7:
                setTotal(value);
                break;
        }
    }

    public void calculateScore() {
        total = 0;
        int resourceWorth = 0, territoryWorth = 0, starWorth = 0;

        if(popularity <= 6){
            starWorth = 3;
            territoryWorth = 2;
            resourceWorth = 1;

        }else if (popularity <= 12){
            starWorth = 4;
            territoryWorth = 3;
            resourceWorth = 2;

        }else if(popularity <= 18){
            starWorth = 5;
            territoryWorth = 4;
            resourceWorth = 3;
        }

        total+= (starWorth * stars) + (territoryWorth * territory) + (resourceWorth * resources);
        total+= structure;
        total+= money;
    }
}
