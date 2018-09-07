package com.example.will.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Citizen {
    public String name;
    public int idNo;
    public Map<String, Double> skills = new HashMap<String, Double>();

    public double skillsToBaseSkillsRate = 0.1;

    public Citizen(){
        skills.put("Basic Intelligence",WeightedRandom());
        skills.put("Basic Strength",WeightedRandom());
        skills.put("Basic Dexterity",WeightedRandom());

        skills.put("Woodcutting",WeightedRandom(skills.get("Basic Strength")));
        skills.put("Mining",WeightedRandom(skills.get("Basic Strength")));
        skills.put("Smelting",WeightedRandom(skills.get("Basic Dexterity")));
        skills.put("Farming",WeightedRandom(skills.get("Basic Dexterity")));
        skills.put("Weaving",WeightedRandom(skills.get("Basic Dexterity")));
        skills.put("Smithing",WeightedRandom(skills.get("Basic Strength")));
        skills.put("Mechanisms",WeightedRandom(skills.get("Basic Intelligence")));
        skills.put("Trading",WeightedRandom(skills.get("Basic Intelligence")));
    }

    public double WeightedRandom(){
//        int randNum = ThreadLocalRandom.current().nextInt(1,100);
        Random rand = new Random();
        int randNum = rand.nextInt(50-10+1)+10;
        return randNum;
    }
    public double WeightedRandom(double base){
        Random rand = new Random();
        int randNum = rand.nextInt(20);
        return base + randNum - 10;
    }

    public void Learn(String skill, String baseSkill, double exp){

            skills.put(skill,skills.get(skill) + exp * skills.get(baseSkill));
            skills.put(baseSkill,skills.get(baseSkill) + skillsToBaseSkillsRate * exp * skills.get(baseSkill) );

        if(skills.get(skill)>150){
            skills.put(skill,150.0);
        }
        if(skills.get(baseSkill) > 100){
            skills.put(baseSkill,150.0);
        }
//        if(skills.get())
    }
}
