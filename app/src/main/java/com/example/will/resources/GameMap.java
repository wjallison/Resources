package com.example.will.resources;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class GameMap {
    public int availHousing;
    public int population;
    public List<Building> buildingList = new ArrayList<Building>();
//    public List<List<Building>> buildingCategoryList = new ArrayList<List<Building>>();

    public List<Citizen> census = new ArrayList<Citizen>();

    public Map<Resource, Integer> totalAvailStorage = new HashMap<Resource, Integer>();
    public Map<Resource, Integer> totalUsedStorage = new HashMap<Resource, Integer>();

    public int funds;

    public GameMap(){

        //Initialize buildingCategoryList
//        buildingCategoryList.add(new ArrayList<Building>());
    }

}
