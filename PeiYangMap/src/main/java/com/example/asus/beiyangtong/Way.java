package com.example.asus.beiyangtong;

public class Way {
    private String name;
    private double length;
    private Place end;
    private String direction;
    private int tag;//标记道路规模，1表示人行道，2表示机动车道

    public Way(double length, Place end, String direction, int tag){
        this.length = length;
        this.end = end;
        this.direction = direction;
        this.tag = tag;
    }

    public  Way(double length, Place end, String direction, int tag, String name){
        this.name = name;
        this.length = length;
        this.end = end;
        this.direction = direction;
        this.tag = tag;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLength(double length){
        this.length = length;
    }

    public void setEnd(Place end){
        this.end = end;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public String getName(){
        return name;
    }

    public double getLength(){
        return length;
    }

    public int getTag() {
        return tag;
    }

    public Place getEnd(){
        return end;
    }

}
