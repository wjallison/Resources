package com.example.will.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Building {
    public String displayName;
    public Map<Resource, Integer> storage = new HashMap<Resource, Integer>();
    public Map<Resource, Integer> maxStorage = new HashMap<Resource, Integer>();
    public int maxWorkers;
    public int currentWorkers;
    public List<Citizen> workers = new ArrayList<>();
    public int level;

    //Only relevant for houses:
    public int livingSpace;

    public double workProgress;
    public int workNeeded;
    public String relevantSkill;
    public String relevantBaseSkill;
    public double expPer;
    public Map<Resource,Integer> recipe = new HashMap<Resource,Integer>();
    public Map<Resource,Integer> output = new HashMap<Resource,Integer>();

    public void Tick(){
        double work = 0;
        if(currentWorkers > 0){
            for(int i = 0; i < currentWorkers; i++){
                work += workers.get(i).skills.get(relevantSkill) * .01;
            }
        }
        workProgress += work;
        if(workProgress > workNeeded){
            if(Build(recipe,output)){
                for(Resource r : output.keySet()){
                    storage.put(r,storage.get(r)+output.get(r));
                }
                workProgress -= workNeeded;
                for(Citizen c : workers){
                    c.Learn(relevantSkill,relevantBaseSkill,expPer);
                }
            }
            else {
                workProgress -= work;
            }
        }
    }

    public int Receive(Resource r,Integer amnt){
        int open = maxStorage.get(r) - storage.get(r);
        if(open >= amnt){
            storage.put(r,storage.get(r) + amnt);
            return 0;
        }
        else{
            storage.put(r,storage.get(r) + open);
            return amnt - open;
        }

    }

    public boolean Build(Map<Resource, Integer> requirements, Map<Resource,Integer> output){
//        Set<Resource> resUsed = requirements.keySet();
//        for(int i = 0; i < requirements.size();i++){
//            if(requirements.get(resUsed[i]))
//        }
        for(Resource r : requirements.keySet()){
            if(requirements.get(r) > storage.get(r)){
                return false;
            }
        }

        for(Resource r : requirements.keySet()){
            storage.put(r,storage.get(r) - requirements.get(r));
        }
        return true;
    }
}