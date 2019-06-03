package com.example.asus.beiyangtong;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Path {
    private List<Way> ways;
    private double length;
    private Set<String> tags = new HashSet<>();
    private List<Place> places;

    public Path(){
        ways = new ArrayList<>();
        length = 1000000;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void change(Path path){
        ways.removeAll(ways);
        ways.addAll(path.getWays());
        length = path.getLength();
    }

    public void change(Path path, Way way){
        ways.removeAll(ways);
        ways.addAll(path.getWays());
        length = path.getLength();
        add(way);
    }

    public void add(Way way){
        ways.add(way);
        length += way.getLength();
    }

    public double getLength() {
        return length;
    }

    public List<Way> getWays() {
        return ways;
    }

    public String toString(int tag){
        String s = "";
        for(Way way:ways){
            s += toString(way, tag);
        }
        return s;
    }

    public String toString(Way way, int tag){
        if(way.getName()!=null && tag == 1){
            return "沿" + way.getName() + "向" + way.getDirection() + "行走" + way.getLength() + "米，到达" + way.getEnd().getName() + "。\n→";
        }else if(way.getName() != null && tag == 2){
            return "沿" + way.getName() + "向" + way.getDirection() + "行驶" + way.getLength() + "米，到达" + way.getEnd().getName() + "。\n→";
        }else if(tag == 1){
            return "沿当前道路向" + way.getDirection() + "行走" + way.getLength() + "米，到达" + way.getEnd().getName() + "。\n→";
        }else{
            return "沿当前道路向" + way.getDirection() + "行驶" + way.getLength() + "米，到达" + way.getEnd().getName() + "。\n→";
        }
    }

    public String tagsSet(){
        String r = "途经";
        for(Way way : ways){
            tags.add(way.getEnd().getTag());
        }
        for (String p : tags){
            r += p + "、";
        }
        r = r.substring(0,r.length()-1);
        r += "。";
        return  r;
    }

    public List<Place> getPlaces() {
        places = new ArrayList<>();
        for(Way way:ways){
            places.add(way.getEnd());
        }
        return places;
    }

    public void clean(){
        ways.removeAll(ways);
        if(places!=null)
            places.removeAll(places);
        length = 1000000;
        if(tags!=null)
            tags.removeAll(tags);
    }
}
