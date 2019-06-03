package com.example.asus.beiyangtong;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Guide {
    private Place begin;
    private Place end;
    private Set<Place> places;

    public Guide(Place begin, Place end, int tag){
        this.begin = begin;
        this.end = end;
        places = new HashSet<>();
        places.add(begin);
        begin.getP()[0].setLength(0);
    }

    public boolean makeWay2(Place place){
        if(places.size() == PeiYangMap.getNUMBEROFPLACES()){
            return true;
        }
        List<Way> ways = new ArrayList<>();
        Way min = new Way(100000,null,null,0);
        boolean tag = true;
        for(Way way : place.getWays()){
            if(way.getTag()== 2){
                if(way.getLength()+place.getP()[0].getLength()<way.getEnd().getP()[0].getLength()){
                    way.getEnd().getP()[1].change(way.getEnd().getP()[0]);
                    way.getEnd().getP()[0].change(place.getP()[0], way);
                    ways.add(way);
                    if(min.getLength() > way.getLength()){
                        min = way;
                        tag = false;
                    }
                }else if(way.getLength()+place.getP()[1].getLength()<way.getEnd().getP()[1].getLength()&&!place.getP()[1].getPlaces().contains(place)){
                    way.getEnd().getP()[1].change(place.getP()[1], way);
                    ways.add(way);
                    if(min.getLength() > way.getLength()){
                        min = way;
                        tag = false;
                    }
                }
            }

        }
        if(tag) return false;
        places.add(min.getEnd());
        while (!makeWay2(min.getEnd())){
            ways.remove(min);
            if(ways.size()==0){
                return false;
            }
            min = getShortest(ways);
            places.add(min.getEnd());
        }
        return false;
    }

    public boolean makeWay(Place place){
        place.getP()[0].setLength(0);
        if(places.size() == PeiYangMap.getNUMBEROFPLACES()){
            return true;
        }
        List<Way> ways = new ArrayList<>();
        Way min = new Way(100000,null,null,0);
        boolean tag = true;
        for(Way way : place.getWays()){
            if(way.getTag()== 2){
                if(way.getLength()+place.getP()[0].getLength()<way.getEnd().getP()[0].getLength()){
                    way.getEnd().getP()[1].change(way.getEnd().getP()[0]);
                    way.getEnd().getP()[0].change(place.getP()[0], way);
                    ways.add(way);
                    if(min.getLength() > way.getLength()){
                        min = way;
                        tag = false;
                    }
                }else if(way.getLength()+place.getP()[1].getLength()<way.getEnd().getP()[1].getLength()&&!place.getP()[1].getPlaces().contains(place)){
                    way.getEnd().getP()[1].change(place.getP()[1], way);
                    ways.add(way);
                    if(min.getLength() > way.getLength()){
                        min = way;
                        tag = false;
                    }
                }
            }

        }
        if(tag) return false;
        places.add(min.getEnd());
        while (!makeWay2(min.getEnd())){
            ways.remove(min);
            if(ways.size()==0){
                return false;
            }
            min = getShortest(ways);
            places.add(min.getEnd());
        }
        return false;
    }

    public boolean makePath2(Place place){
//        place.setVisited(true);
        if(places.size() == PeiYangMap.getNUMBEROFPLACES()){
            return true;
        }
        List<Way> ways = new ArrayList<>();
//        if(place.getName().equals(end.getName())) return false;
        Way min = new Way(100000,null,null,0);
        boolean tag = true;
        for(Way way : place.getWays()){
//            if(way.getEnd().isVisited()) continue;
            if(way.getLength()+place.getP()[0].getLength()<way.getEnd().getP()[0].getLength()){
                way.getEnd().getP()[1].change(way.getEnd().getP()[0]);
                way.getEnd().getP()[0].change(place.getP()[0], way);
//                System.out.println(way.getEnd().getName()+":");
//                System.out.println(way.getEnd().getP()[0].getWays().size());
//                for(Way w:way.getEnd().getP()[0].getWays()){
//                    System.out.println(w.getEnd().getName());
//                }
                ways.add(way);
                if(min.getLength() > way.getLength()){
                    min = way;
                    tag = false;
                }
            }
            else if(way.getLength()+place.getP()[1].getLength()<way.getEnd().getP()[1].getLength()&&!place.getP()[1].getPlaces().contains(place)){
                way.getEnd().getP()[1].change(place.getP()[1], way);
                ways.add(way);
                if(min.getLength() > way.getLength()){
                    min = way;
                    tag = false;
                }
            }
        }
        if(tag) return false;
        places.add(min.getEnd());
        while (!makePath2(min.getEnd())){
//            min.getEnd().setVisited(false);
            ways.remove(min);
            if(ways.size()==0){
                return false;
            }
            min = getShortest(ways);
            places.add(min.getEnd());
        }
        return false;
    }

    public boolean makePath(Place place){
        place.getP()[0].setLength(0);
        if(places.size() == PeiYangMap.getNUMBEROFPLACES()){
            return true;
        }
        List<Way> ways = new ArrayList<>();
        Way min = new Way(100000,null,null,0);
        boolean tag = true;
        for(Way way : place.getWays()){
            if(way.getLength()+place.getP()[0].getLength()<way.getEnd().getP()[0].getLength()){
                way.getEnd().getP()[1].change(way.getEnd().getP()[0]);
                way.getEnd().getP()[0].change(place.getP()[0], way);
                ways.add(way);
                if(min.getLength() > way.getLength()){
                    min = way;
                    tag = false;
                }
            }
            else if(way.getLength()+place.getP()[1].getLength()<way.getEnd().getP()[1].getLength()&&!place.getP()[1].getPlaces().contains(place)){
                way.getEnd().getP()[1].change(place.getP()[1], way);
                ways.add(way);
                if(min.getLength() > way.getLength()){
                    min = way;
                    tag = false;
                }
            }
        }
        if(tag) return false;
        places.add(min.getEnd());
        while (!makePath2(min.getEnd())){
//            min.getEnd().setVisited(false);
            ways.remove(min);
            if(ways.size()==0){
                return false;
            }
            min = getShortest(ways);
            places.add(min.getEnd());
        }
        return false;
    }

    public String get1(){
        fresh(begin);
        makePath(begin);
        return begin.toString(begin) + end.getP()[0].toString(1) + "结束。";
    }

    public String get2(){
        fresh(begin);
        makePath(begin);
        return begin.toString(begin) + end.getP()[1].toString(1) + "结束。";
    }

    public String get3(){
            Place place = begin;
            Place place1 = end;
            fresh(begin);
            while (!place.hasCarWay()){
                place = place.getNearWay();
            }
            while (!place1.hasCarWay()){
                place1 = place1.getNearWay();
            }
            if(place != begin && place1 != end){
                makePath(begin);
                String path = "第一步：" + begin.toString(begin) + place.getP()[0].toString(1) + "结束。" + "\n" + place.getP()[0].tagsSet() + "\n";
                fresh(begin);
                makeWay(place);
                path += "第二步：" + place.toString(place) + place1.getP()[0].toString(2) + "结束。\n" + place1.getP()[0].tagsSet() + "\n";
                fresh(place1);
                makePath(place1);
                path += "第三步：" + place1.toString(place1) + end.getP()[0].toString(1) + "结束。" + "\n" + end.getP()[0].tagsSet();
                return path;
            }else if(place == begin && place1 !=end) {
                makeWay(place);
                String path = "第一步：" + place.toString(place) + place1.getP()[0].toString(2) + "结束。\n" + place1.getP()[0].tagsSet() + "\n";
                fresh(place);
                makePath(place1);
                path += "第二步：" + place1.toString(place1) + end.getP()[0].toString(1) + "结束。" + "\n" + end.getP()[0].tagsSet();
                return path;
            }else if(place != begin && place1 == end) {
                makePath(begin);
                String path = "第一步：" + begin.toString(begin) + place.getP()[0].toString(1) + "结束。" + "\n" + place.getP()[0].tagsSet() + "\n";
                fresh(begin);
                makeWay(place);
                path += "第二步：" + place.toString(place) + place1.getP()[0].toString(2) + "结束。" + "\n" + place1.getP()[0].tagsSet() + "\n";
                return path;
            }else {
                makeWay(place);
                String path = place.toString(place) + place1.getP()[0].toString(2) + "结束。" + "\n" + place1.getP()[0].tagsSet() + "\n";
                return path;
            }
    }

    public String get4(){
        Place place = begin;
        Place place1 = end;
        fresh(begin);
        while (!place.hasCarWay()){
            place = place.getNearWay();
        }
        while (!place1.hasCarWay()){
            place1 = place1.getNearWay();
        }
        if(place != begin && place1 != end){
            makePath(begin);
            String path = "第一步：" + begin.toString(begin) + place.getP()[0].toString(1) + "结束。" + "\n" + place.getP()[0].tagsSet() + "\n";
            fresh(begin);
            makeWay(place);
            path += "第二步：" + place.toString(place) + place1.getP()[1].toString(2) + "结束。\n" + place1.getP()[1].tagsSet() + "\n";
            fresh(place);
            makePath(place1);
            path += "第三步：" + place1.toString(place1) + end.getP()[0].toString(1) + "结束。" + "\n" + end.getP()[0].tagsSet();
            return path;
        }else if(place == begin && place1 !=end) {
            makeWay(place);
            String path = "第一步：" + place.toString(place) + place1.getP()[1].toString(2) + "结束。\n" + place1.getP()[1].tagsSet() + "\n";
            fresh(place);
            makePath(place1);
            path += "第二步：" + place1.toString(place1) + end.getP()[0].toString(1) + "结束。" + "\n" + end.getP()[0].tagsSet();
            return path;
        }else if(place != begin && place1 == end) {
            makePath(begin);
            String path = "第一步：" + begin.toString(begin) + place.getP()[0].toString(1) + "结束。" + "\n" + place.getP()[0].tagsSet() + "\n";
            fresh(begin);
            makeWay(place);
            path += "第二步：" + place.toString(place) + place1.getP()[1].toString(2) + "结束。" + "\n" + place1.getP()[1].tagsSet() + "\n";
            return path;
        }else {
            makeWay(place);
            String path = place.toString(place) + place1.getP()[1].toString(2) + "结束。" + "\n" + place1.getP()[1].tagsSet() + "\n";
            return path;
        }
    }

    public double getlength1(){
        return end.getP()[0].getLength();
    }

    public double getlength2(){
        return end.getP()[1].getLength();
    }

    public double getlength3(){
        Place place = begin;
        Place place1 = end;
        fresh(begin);
        double length = 0;
        while (!place.hasCarWay()){
            place = place.getNearWay();
        }
        while (!place1.hasCarWay()){
            place1 = place1.getNearWay();
        }
        if(place != begin && place1 != end){
            makePath(begin);
            length += place.getP()[0].getLength();
            fresh(place);
            makeWay(place);
            length += place1.getP()[0].getLength();
            fresh(place1);
            makePath(place1);
            length += end.getP()[0].getLength();
            return length;
        }else if(place == begin && place1 !=end) {
            makeWay(place);
            length += place1.getP()[0].getLength();
            fresh(place1);
            makePath(place1);
            length += end.getP()[0].getLength();
            return length;
        }else if(place != begin && place1 == end) {
            makePath(begin);
            length += place.getP()[0].getLength();
            fresh(place);
            makeWay(place);
            length += place1.getP()[0].getLength();
            return length;
        }else {
            makeWay(place);
            return end.getP()[0].getLength();
        }
    }

    public double getlength4(){
        Place place = begin;
        Place place1 = end;
        fresh(begin);
        double length = 0;
        while (!place.hasCarWay()){
            place = place.getNearWay();
        }
        while (!place1.hasCarWay()){
            place1 = place1.getNearWay();
        }
        if(place != begin && place1 != end){
            makePath(begin);
            length += place.getP()[0].getLength();
            fresh(place);
            makeWay(place);
            length += place1.getP()[1].getLength();
            fresh(place1);
            makePath(place1);
            length += end.getP()[0].getLength();
            return length;
        }else if(place == begin && place1 !=end) {
            makeWay(place);
            length += place1.getP()[1].getLength();
            fresh(place1);
            makePath(place1);
            length += end.getP()[0].getLength();
            return length;
        }else if(place != begin && place1 == end) {
            makePath(begin);
            length += place.getP()[0].getLength();
            fresh(place);
            makeWay(place);
            length += place1.getP()[1].getLength();
            return length;
        }else {
            makeWay(place);
            return place1.getP()[1].getLength();
        }
    }

    public String get1tags(){
        return end.getP()[0].tagsSet();
    }

    public String get2tags(){
        return end.getP()[1].tagsSet();
    }

    public Way getShortest(List<Way> list){
        Way min = new Way(100000,null,null,0);
        for(Way way: list){
            if(min.getLength()>way.getLength()){
                min = way;
            }
        }
        return min;
    }

    public void fresh(Place place){
        for(Place p : places){
            p.getP()[0].clean();
            p.getP()[1].clean();
        }
        places.remove(places);
        places.add(place);
    }
}
