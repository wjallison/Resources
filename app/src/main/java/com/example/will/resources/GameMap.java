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

    public void CheckForJobs(){
        boolean jobsAvailable = false;
        for(Building b : buildingList){
            if (b.maxWorkers - b.currentWorkers > 0){
                jobsAvailable = true;
                break;
            }
        }
        if(jobsAvailable) {
            for (Citizen c : census) {

                if (!c.hasJob) {
                    //Create list of buildings with openings
                    List<Integer> indList = new ArrayList<>();
                    for (int i = 0; i < buildingList.size(); i++) {
                        if (buildingList.get(i).maxWorkers - buildingList.get(i).currentWorkers > 0) {
                            indList.add(i);
                        }
                    }
                    String best = "bleh";
                    double max = 0;
                    Map<String, Double> temp = c.skills;
//                for(int i =0; i < c.skills.keySet().size();i++)
                    boolean notDone = true;
                    while (notDone) {
                        for (Map.Entry<String, Double> e : temp.entrySet()) {
                            if (e.getValue() > max) {
                                best = e.getKey();
                                max = e.getValue();
                            }
                        }
                        for(Building b: buildingList){
                            if(b.maxWorkers-b.currentWorkers>0){
                                if (b.relevantSkill == best){
                                    b.workers.add(c);
                                    c.hasJob = true;
                                    notDone = false;
                                    break;
                                }
                            }
                        }
                        temp.remove(best);
                        max = 0;
                    }
                }
            }
        }
    }

    public void Tick(){
        //Things to accomplish in a tick:
        //Buildings produce
        //Unemployed check for jobs
        //Resources move
        //Chance for people to move into homes

        //Buildings produce
        for(Building b : buildingList){
            b.Tick();
        }

        //Unemployed check for jobs
        CheckForJobs();

        //Resources move

        //Move-ins

    }

}
