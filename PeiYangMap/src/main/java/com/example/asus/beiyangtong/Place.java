package com.example.asus.beiyangtong;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Place {
    private String name;
    private String tag;
    private List<Way> ways;
    private Place prePlace;
//    private boolean isVisited;
//    private String path;
//    private double pathLenth;
//    private static double minPathLength = 100000;
//    private static double minPathLength2 = 100000;
    private Path p[];

    public Place(String name, String tag){
        this.name = name;
        this.tag = tag;
        ways = new LinkedList<>();
//        isVisited = false;
        prePlace = null;
        initP();
    }

    public Place(String name, String tag, Place prePlace){
        this.name = name;
        this.tag = tag;
        ways = new LinkedList<>();
//        isVisited = false;
        prePlace = null;
        this.prePlace = prePlace;
        initP();
    }

    public void initP(){
        p = new Path[2];
        p[0] = new Path();
        p[1] = new Path();
    }

    public Path[] getP() {
        return p;
    }

    public void addWays(Way way){
        this.ways.add(way);
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrePlace(Place prePlace) {
        this.prePlace = prePlace;
    }

    public Place getPrePlace() {
        return prePlace;
    }

//    public void setVisited(boolean visited) {
//        isVisited = visited;
//    }
//
//    public boolean isVisited() {
//        return isVisited;
//    }

    public List<Way> getWays() {
        return ways;
    }

//    public String getPath() {
//        return path;
//    }
//
//    public double getPathLenth() {
//        return pathLenth;
//    }
//
//    public double getMinPathLength() {
//        return minPathLength;
//    }
//
//    public double getMinPathLength2() {
//        return minPathLength2;
//    }
//
//    public static void setMinPathLength(double minPathLength) {
//        Place.minPathLength = minPathLength;
//    }
//
//    public static void setMinPathLength2(double minPathLength2) {
//        Place.minPathLength2 = minPathLength2;
//    }
//
//    public void getShortestPath(double d, String end){
//        this.path = "";
//        this.pathLenth = d;
//        int tag = 0;
//        if(d >= minPathLength){
//            return;
//        }
//        if(this.getName().equals(end)){
//            minPathLength = d;
//            return;
//        }
//        if(ways == null || ways.size()==0){
//            return;
//        }
//        this.path = toString(this);
//        this.isVisited = true;
//        for(Way way:ways){
//            String p = way.getEnd().getPath1(d + way.getLength(), end, this);
//            if(p!=null){
//                if(tag == 1){
//                    this.path = toString(this);
//                }
//                tag = 1;
//                this.path += toString(way, 1) +  p;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        return;
//    }
//
//    public String getPath1(double d, String end, Place pre){
//        if(this.isVisited) return null;
//        this.path = "";
//        this.pathLenth = d;
//        this.prePlace = pre;
//        if(d >= minPathLength){
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        if(this.getName().equals(end)){
//            this.prePlace.isVisited = false;
//            minPathLength = d;
//            return "。";
//        }
//        if(ways == null || ways.size()==0){
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        int tag = 0;
//        this.isVisited = true;
//        for(Way way:ways){
//            String p = way.getEnd().getPath1(d + way.getLength(), end, this);
//            if(p!=null){
//                if(tag == 1){
//                    this.path = toString(way, 1) + p;
//                }else{
//                    this.path += toString(way, 1) + p;
//                }
////                d += way.getLength();
//                tag = 1;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        this.isVisited = false;
//        if(tag == 1) return this.path;
//        return null;
//    }
//
//    public void get2SPath(double d, String end){
//        this.path = "";
//        this.pathLenth = d;
//        if(d >= minPathLength2){
//            return;
//        }
//        if(this.getName().equals(end)&&d > minPathLength){
//            minPathLength2 = d;
//            this.isVisited = true;
//            return;
//        }
//        if(ways == null || ways.size()==0){
//            return;
//        }
//        this.path = toString(this);
//        this.isVisited = true;
//        int tag = 0;
//        for(Way way:ways){
//            String p = way.getEnd().getPath2(d + way.getLength(), end, this);
//            if(p!=null){
//                if(tag == 1){
//                    this.path = toString(this);
//                }
//                tag = 1;
//                this.path += toString(way, 1) + p;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        return;
//    }
//
//    public String getPath2(double d, String end, Place pre) {
//        if(this.isVisited) return null;
//        this.path = "";
//        this.pathLenth = d;
//        this.prePlace = pre;
//        if (d >= minPathLength2) {
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        if (this.getName().equals(end) && d > minPathLength) {
//            this.prePlace.isVisited = false;
//            minPathLength2 = d;
//            return "。";
//        }
//        if (ways == null || ways.size() == 0) {
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        int tag = 0;
//        this.isVisited = true;
//        for (Way way : ways) {
//            String p = way.getEnd().getPath2(d + way.getLength(), end, this);
//            if (p != null) {
//                if(tag == 1){
//                    this.path = toString(way, 1) + p;
//                }else{
//                    this.path += toString(way, 1) + p;
//                }
//                tag = 1;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        this.isVisited = false;
//        if(tag == 1) return this.path;
//        return null;
//    }

    //驾车路线
//    public void getShortestWay(double d, String end){
//        this.path = "";
//        this.pathLenth = d;
//        int tag = 0;
//        if(d >= minPathLength){
//            return;
//        }
//        if(this.getName().equals(end)){
//            minPathLength = d;
//            return;
//        }
//        if(ways == null || ways.size()==0){
//            return;
//        }
//        this.path = toString(this);
//        this.isVisited = true;
//        for(Way way:ways){
//            if(way.getTag()==1)continue;
//            String p = way.getEnd().getWay1(d + way.getLength(), end, this);
//            if(p!=null){
//                if(tag == 1){
//                    this.path = toString(this);
//                }
//                tag = 1;
//                this.path += toString(way, 2) + p;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        return;
//    }
//
//    public String getWay1(double d, String end, Place pre){
//        if(this.isVisited) return null;
//        this.path = "";
//        this.pathLenth = d;
//        this.prePlace = pre;
//        if(d >= minPathLength){
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        if(this.getName().equals(end)){
//            this.prePlace.isVisited = false;
//            minPathLength = d;
//            return "。";
//        }
//        if(ways == null || ways.size()==0){
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        int tag = 0;
//        this.isVisited = true;
//        for(Way way:ways){
//            if(way.getTag()==1)continue;
//            String p = way.getEnd().getWay1(d + way.getLength(), end, this);
//            if(p!=null){
//                if(tag == 1){
//                    this.path = toString(way, 2) + p;
//                }else{
//                    this.path += toString(way, 2) + p;
//                }
////                d += way.getLength();
//                tag = 1;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        this.isVisited = false;
//        if(tag == 1) return this.path;
//        return null;
//
//    }
//
//    public void get2SWay(double d, String end){
//        this.path = "";
//        this.pathLenth = d;
//        if(d >= minPathLength2){
//            return;
//        }
//        if(this.getName().equals(end)&&d > minPathLength){
//            minPathLength2 = d;
//            this.isVisited = true;
//            return;
//        }
//        if(ways == null || ways.size()==0){
//            return;
//        }
//        this.path = toString(this);
//        this.isVisited = true;
//        int tag = 0;
//        for(Way way:ways){
//            if(way.getTag()==1) continue;
//            String p = way.getEnd().getWay2(d + way.getLength(), end, this);
//            if(p!=null){
//                if(tag == 1){
//                    this.path = toString(this);
//                }
//                tag = 1;
//                this.path += toString(way, 2) + p;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        return;
//    }
//
//    public String getWay2(double d, String end, Place pre) {
//        if(this.isVisited) return null;
//        this.path = "";
//        this.pathLenth = d;
//        this.prePlace = pre;
//        if (d >= minPathLength2) {
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        if (this.getName().equals(end) && d > minPathLength) {
//            this.prePlace.isVisited = false;
//            minPathLength2 = d;
//            return "。";
//        }
//        if (ways == null || ways.size() == 0) {
//            this.prePlace.isVisited = false;
//            return null;
//        }
//        int tag = 0;
//        this.isVisited = true;
//        for (Way way : ways) {
//            if(way.getTag()==1) continue;
//            String p = way.getEnd().getWay2(d + way.getLength(), end, this);
//            if (p != null) {
//                if(tag == 1){
//                    this.path = toString(way, 2) + p;
//                }else{
//                    this.path += toString(way, 2) + p;
//                }
//                tag = 1;
//                this.path += p;
//                if(way.getEnd().getName().equals(end)) break;
//            }
//        }
//        this.isVisited = false;
//        if(tag == 1) return this.path;
//        return null;
//    }

    public boolean hasCarWay(){
        for (Way way:ways){
            if(way.getTag()==2)return true;
        }
        return false;
    }

    public Place getNearWay(){
        for (Way way:ways){
            if(way.getEnd().hasCarWay())
                return way.getEnd();
        }
        return ways.get(0).getEnd();
    }

//    public String toString(Way way, int tag){
//        if(way.getName()!=null && tag == 1){
//            return ",沿" + way.getName() + "向" + way.getDirection() + "行走" + way.getLength() + "米，到达" + way.getEnd().getName();
//        }else if(way.getName() != null && tag == 2){
//            return ",沿" + way.getName() + "向" + way.getDirection() + "行驶" + way.getLength() + "米，到达" + way.getEnd().getName();
//        }else if(tag == 1){
//            return ",沿当前道路向" + way.getDirection() + "行走" + way.getLength() + "米，到达" + way.getEnd().getName();
//        }else{
//            return ",沿当前道路向" + way.getDirection() + "行驶" + way.getLength() + "米，到达" + way.getEnd().getName();
//        }
//    }

    public boolean equals(Place place){
        if(place.getName().equals(this.getName())){
            return true;
        }else return false;
    }

    @Override
    public boolean equals( Object obj) {
        return this.equals((Place)obj);
    }

    public String toString(Place place){
        return "从" + place.getName() + "出发，";
    }

}
