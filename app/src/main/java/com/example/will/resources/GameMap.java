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

    //INCOMPLETE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void CheckForJobs(){
        for(Citizen c : census){
            boolean jobsAvailable = false;
            for(Building b : buildingList){
                if (b.maxWorkers - b.currentWorkers > 0){
                    jobsAvailable = true;
                }
            }
            if(jobsAvailable && !c.hasJob){
                //Create list of buildings with openings
                List<Integer> indList = new ArrayList<>();
                for(int i = 0; i < buildingList.size();i++){
                    if(buildingList.get(i).maxWorkers - buildingList.get(i).currentWorkers > 0){
                        indList.add(i);
                    }
                }


                String best;
                double max = 0;
                Map<String, Double> temp = c.skills;
//                for(int i )
//                for(Map.Entry<String, Double> e : c.skills.entrySet()){
//                    if(e.getValue() > max){
//                        best = e.getKey();
//                        max = e.getValue();
//                    }
//                }


                //Create ordered list of citizen's skills
//                List<String> orderedSkillList = new ArrayList<>();
//                double maxVal = 0;
//                int ind;
//                for(int i = 3; i < c.skills.size();i++){
//                    if(c.skills.get(c.skills.keySet()[i]))
//                }
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
//        CheckForJobs();

        //Resources move

        //Move-ins

    }

}
