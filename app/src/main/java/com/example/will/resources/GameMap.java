package com.example.will.resources;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GameMap {
    public int availHousing;
    public int population;
    public List<Building> buildingList = new ArrayList<Building>();
//    public List<List<Building>> buildingCategoryList = new ArrayList<List<Building>>();

    public List<Citizen> census = new ArrayList<Citizen>();

    public Map<Resource, Integer> totalAvailStorage = new HashMap<Resource, Integer>();
    public Map<Resource, Integer> totalUsedStorage = new HashMap<Resource, Integer>();

    public Map<Resource, Integer> spareResources = new HashMap<>();

    public int funds;

    public GameMap(){


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

    public void MoveResources(){
/*
        First, if a building has some of a resource but doesn't need it, remove that resource from its inventory
        and place it in the spare inventory.
*/
//        Map<Resource, Integer> tempResources = new HashMap<>();
        for(int i = 0; i < buildingList.size();i++){
            Set<Resource> neededRes = buildingList.get(i).recipe.keySet();
            Set<Resource> containedRes = buildingList.get(i).storage.keySet();

            for(Resource r : containedRes){
                if(neededRes.contains(r)){
                    continue;
                }
                else {
                    if(spareResources.containsKey(r)){
                        spareResources.put(r,spareResources.get(r) + buildingList.get(i).storage.get(r));
                        buildingList.get(i).storage.remove(r);
                    }
                    else {
                        spareResources.put(r,buildingList.get(i).storage.get(r));
                        buildingList.get(i).storage.remove(r);
                    }
                }
            }
        }

        //For each resoruce, build two lists: a list of the demand for each resource for each building, and a list of how full that building's inventory
        //is of that resource.  Then, assign the resource to buildings.
        List<Integer> demandList = new ArrayList<>();
        List<Double> filledList = new ArrayList<>();
        List<Integer> openSpace = new ArrayList<>();
        Integer totalOpenSpace = 0;
        for(Resource r : totalUsedStorage.keySet()) {
            demandList.clear();
            filledList.clear();
            openSpace.clear();
            totalOpenSpace = 0;
            for (int j = 0; j < buildingList.size(); j++) {
                if (buildingList.get(j).recipe.containsKey(r)) {
                    demandList.add(buildingList.get(j).recipe.get(r));
                    filledList.add(1 - (double) buildingList.get(j).storage.get(r) / (double) buildingList.get(j).maxStorage.get(r));
                    openSpace.add(buildingList.get(j).maxStorage.get(r) - buildingList.get(j).storage.get(r));
                    totalOpenSpace += openSpace.get(j);
                }
            }
            /*Two cases: there are more resources than buildings that require them
            * OR
            * there is more space in buildings than resources available.
            * */
            //More open space than resources available
            if(totalOpenSpace > spareResources.get(r)){

            }

            //open space exactly equals the number of resources available
            else if(totalOpenSpace == spareResources.get(r)){

            }

            //Less open space than available resources
            else{

            }

        }
//        }
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
