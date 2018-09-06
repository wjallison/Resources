package com.example.will.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Building {
    public String displayName;
    public Map<Resource, Integer> storage = new HashMap<Resource, Integer>();
    public int maxWorkers;
    public int currentWorkers;
    public List<Citizen> workers = new ArrayList<>();
    public int level;

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

class Woodcutter extends Building{


}