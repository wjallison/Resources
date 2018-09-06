package com.example.will.resources;

import java.util.HashMap;
import java.util.Map;

public class BuildingBlueprint {
    public String displayName;
    public Map<Resource, Integer> constructionMaterials = new HashMap<Resource,Integer>();
    public int progress;
}
