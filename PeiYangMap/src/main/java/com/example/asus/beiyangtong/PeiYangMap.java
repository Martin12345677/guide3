package com.example.asus.beiyangtong;

import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class PeiYangMap{
    private static Set<Place> map = new HashSet<>();
    private static String name = "天津大学北洋园地图";
    private static final int NUMBEROFPLACES = 136;

    private static PeiYangMap peiYangMap;

    public static void init(InputStream inputStream){
        peiYangMap = new PeiYangMap(inputStream);
    }

    private PeiYangMap(InputStream inputStream){
        try {
            initMap(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PeiYangMap(){
        try {
            initMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PeiYangMap getPeiYangMap() {
        return peiYangMap;
    }

    public static void initMap() throws IOException {
        File file = new File("app\\src\\main\\java\\com\\example\\asus\\beiyangtong\\map");
        InputStream input = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte tmp[] = new byte[1];
        byte content[];
        int a = 0;
        while((a = input.read(tmp)) != -1){
            byteArrayOutputStream.write(tmp);
        }
        content = byteArrayOutputStream.toByteArray();
        String str = new String(content, "utf-8");
        String line[] = str.split(System.lineSeparator());
        for(int i = 0; i < Integer.parseInt(line[0]); i++){
            String word[] = line[i+1].split(" ");
            Place place = new Place(word[0],word[1]);
            addPlace(place);
        }
        for (int i = Integer.parseInt(line[0]) + 1; i < line.length ; ) {
            String word[] = line[i].split(" ");
            int begin = i;
            i++;
            while (i < begin + Integer.parseInt(word[1]) + 1) {
                String p[] = line[i].split(" ");
                //              System.out.println(line[i]);
                if (p.length == 4) {
                    Way way = new Way(Float.parseFloat(p[0]), getPlace(p[1]), p[2], Integer.parseInt(p[3]));
                    getPlace(word[0]).addWays(way);
                    Way way0 = new Way(Float.parseFloat(p[0]), getPlace(word[0]), getOppositeDirection(p[2]), Integer.parseInt(p[3]));
                    getPlace(p[1]).addWays(way0);
                } else if (p.length == 5) {
                    Way way = new Way(Float.parseFloat(p[0]), getPlace(p[1]), p[2], Integer.parseInt(p[3]), p[4]);
                    getPlace(word[0]).addWays(way);
                    Way way0 = new Way(Float.parseFloat(p[0]), getPlace(word[0]), getOppositeDirection(p[2]), Integer.parseInt(p[3]), p[4]);
                    getPlace(p[1]).addWays(way0);
                } else return;
                i++;
            }
        }
    }

    public static void initMap(InputStream input) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte tmp[] = new byte[1];
        byte content[];
        int a = 0;
        while((a = input.read(tmp)) != -1){
            byteArrayOutputStream.write(tmp);
        }
        content = byteArrayOutputStream.toByteArray();
        String str = new String(content, "utf-8");
        String line[] = str.split(System.lineSeparator());
        line[0] = toNoBom(line[0]);
        for(int i = 0; i < Integer.parseInt(line[0]); i++){
            String word[] = line[i+1].split(" ");
            Place place = new Place(word[0],word[1]);
            addPlace(place);
        }
        for (int i = Integer.parseInt(line[0]) + 1; i < line.length ; ){
            line[i] = toNoBom(line[i]);
            String word[] = line[i].split(" ");
            int begin = i;
            i++;
            while (i < begin + Integer.parseInt(word[1]) + 1){
                line[i] = toNoBom(line[i]);
                String p[] = line[i].split(" ");
  //              System.out.println(line[i]);
                if(p.length == 4){
                    Way way = new Way(Float.parseFloat(p[0]), getPlace(p[1]), p[2], Integer.parseInt(p[3]));
                    getPlace(word[0]).addWays(way);
                    Way way0 = new Way(Float.parseFloat(p[0]), getPlace(word[0]), getOppositeDirection(p[2]), Integer.parseInt(p[3]));
                    getPlace(p[1]).addWays(way0);
                }else if(p.length == 5){
                    Way way = new Way(Float.parseFloat(p[0]), getPlace(p[1]), p[2], Integer.parseInt(p[3]), p[4]);
                    getPlace(word[0]).addWays(way);
                    Way way0 = new Way(Float.parseFloat(p[0]), getPlace(word[0]), getOppositeDirection(p[2]), Integer.parseInt(p[3]), p[4]);
                    getPlace(p[1]).addWays(way0);
                }else return;
                i++;
            }
        }
//        Place z4 = new Place("知园四斋","学生宿舍");
//        addPlace(z4);
//        Place z5 = new Place("知园五斋","学生宿舍");
//        addPlace(z5);
//        Place cheng6 = new Place("诚园六斋","学生宿舍");
//        addPlace(cheng6);
//        Place cheng7 = new Place("诚园七斋","学生宿舍");
//        addPlace(cheng7);
//        Place cheng8 = new Place("诚园八斋","学生宿舍");
//        addPlace(cheng8);
//        Place z9 = new Place("正园九斋","学生宿舍");
//        addPlace(z9);
//        Place z10 = new Place("正园十斋","学生宿舍");
//        addPlace(z10);
//        Place mei = new Place("梅园","餐厅");
//        addPlace(mei);
//        Place bchao1 = new Place("北洋超市诚园店","超市");
//        addPlace(bchao1);
//        Place bchao2 = new Place("北洋水果超市","超市");
//        addPlace(bchao2);
//        Place atm1 = new Place("中国银行诚园店ATM", "ATM");
//        addPlace(atm1);
//        Place atm2 = new Place("交通银行诚园店ATM", "ATM");
//        addPlace(atm2);
//        Place j50 = new Place("第五十教学楼（化工学院）","教学楼");
//        addPlace(j50);
//        Place j55 = new Place("第五十五教学楼（智能与计算学部）","教学楼");
//        addPlace(j55);
//        Place l1 = new Place("诚园八斋理发店","理发店");
//        addPlace(l1);
//        WayOfcheng8:{
//            Way cw1 = new Way(54,atm1,"东",1);
//            Way cw2 = new Way(17,bchao1,"西",1);
//            Way cw3 = new Way(98,cheng7,"东",1);
//            Way cw4 = new Way(123,j55,"西",1);
//            Way cw5 = new Way(162,mei,"北",2,"七星北路");
//            Way cw6 = new Way(64,atm2,"南",1);
//            Way cw7 = new Way(67,l1,"北",1);
//            cheng8.addWays(cw1);
//            cheng8.addWays(cw2);
//            cheng8.addWays(cw3);
//            cheng8.addWays(cw4);
//            cheng8.addWays(cw5);
//            cheng8.addWays(cw6);
//            cheng8.addWays(cw7);
//        }
//        WayOfcheng7:{
//            Way cw1 = new Way(176,j50,"东",1);
//            Way cw2 = new Way(56,atm1,"西",1);
//            Way cw3 = new Way(239,j50,"东",2,"明德北道");
//            Way cw4 = new Way(50,cheng6,"北",1);
//            Way cw5 = new Way(200,z9,"南",2, "西沽北路");
//            cheng7.addWays(cw1);
//            cheng7.addWays(cw2);
//            cheng7.addWays(cw3);
//            cheng7.addWays(cw4);
//            cheng7.addWays(cw5);
//        }
//        WayOfcheng6:{
//            Way cw1 = new Way(130,j50,"东",1);
//            Way cw3 = new Way(150,j50,"东",2,"亲民北道");
//            Way cw2 = new Way(50,l1,"西",1);
//            Way cw4 = new Way(130,z5,"北",1);
//            Way cw5 = new Way(150,z5,"北",2, "西沽北路");
//            Way cw6 = new Way(70,cheng7,"南",1);
//            cheng6.addWays(cw1);
//            cheng6.addWays(cw2);
//            cheng6.addWays(cw3);
//            cheng6.addWays(cw4);
//            cheng6.addWays(cw5);
//            cheng6.addWays(cw6);
//        }
//        WayOfATM1:{
//            Way cw1 = new Way(43,cheng7,"东",1);
//            Way cw2 = new Way(56,cheng8,"西",1);
//            Way cw4 = new Way(56,bchao2,"北",1);
//            atm1.addWays(cw1);
//            atm1.addWays(cw2);
//            atm1.addWays(cw4);
//        }
//        WayOfATM2:{
//            Way cw1 = new Way(120,cheng7,"东",2,"明德北道");
//            Way cw2 = new Way(100,j55,"西",2,"明德北道");
//            Way cw3 = new Way(63,cheng8,"北",2,"七星北路");
//            Way cw4 = new Way(63,cheng8,"北",1);
//            Way cw5 = new Way(130,z10,"南",2,"七星北路");
//            atm2.addWays(cw1);
//            atm2.addWays(cw2);
//            atm2.addWays(cw3);
//            atm2.addWays(cw4);
//            atm2.addWays(cw5);
//        }
//        WayOfZ5:{
//            Way w1 = new Way(120,cheng7,"东",2,"明德北道");
//            Way w2 = new Way(63,mei,"西",1);
//            Way w4 = new Way(63,z4,"北",1);
//            Way w5 = new Way(130,z10,"南",2,"七星北路");
//            z5.addWays(w1);
//            z5.addWays(w2);
////            z5.addWays(w3);
//            z5.addWays(w4);
//            z5.addWays(w5);
//        }
    }

    public static void addPlace(Place place){
        map.add(place);
    }

    public static int getNUMBEROFPLACES() {
        return NUMBEROFPLACES;
    }

    public static String getName() {
        return name;
    }

    public static Set<Place> getMap(){
        return map;
    }

    public static Place getPlace(String name){
        for(Place place:map){
            if(place.getName().equals(name)){
                return place;
            }
        }
        return null;
    }

//    public static String[] getShortestPath(String begin, String end){
//        Place place = getPlace(begin);
//        place.getShortestPath(0, end);
//        return new String[]{place.getPath(),place.getMinPathLength()+"米"};
//    }
//
//    public static String[] get2SPath(String begin, String end){
//        Place place = getPlace(begin);
//        place.get2SPath(0, end);
//        return new String[]{place.getPath(),place.getMinPathLength2()+"米"};
//    }
//驾车路线
//    public static String[] getShortestWay(String begin, String end){
//        Place place = getPlace(begin);
//        Place place1 = getPlace(end);
//        while (!place.hasCarWay()){
//            place = place.getNearWay();
//        }
//        while (!place1.hasCarWay()){
//            place1 = place1.getNearWay();
//        }
//        getPlace(begin).getShortestPath(0,place.getName());
//        String path1 = getPlace(begin).getPath();
//        double length = getPlace(begin).getMinPathLength();
//        fresh();
//        Place.setMinPathLength(10000);
//        place.getShortestWay(0, place1.getName());
//        String path2 = place.getPath();
//        length += place.getMinPathLength();
//        fresh();
//        Place.setMinPathLength(10000);
//        place1.getShortestPath(0,end);
//        String path3 = place1.getPath();
//        length += place1.getMinPathLength();
//        Place.setMinPathLength(length);
//        return new String[]{path1 + path2 + path3,length+"米"};
//    }

//    public static String[] get2SWay(String begin, String end){
//        Place place = getPlace(begin);
//        Place place1 = getPlace(end);
//        while (!place.hasCarWay()){
//            place = place.getNearWay();
//        }
//        while (!place1.hasCarWay()){
//            place1 = place1.getNearWay();
//        }
//        getPlace(begin).get2SPath(0,place.getName());
//        String path1 = getPlace(begin).getPath();
//        double length = getPlace(begin).getMinPathLength2();
//        fresh();
//        Place.setMinPathLength2(10000);
//        place.get2SWay(0, place1.getName());
//        String path2 = place.getPath();
//        length += place.getMinPathLength2();
//        fresh();
//        Place.setMinPathLength2(10000);
//        place1.get2SPath(0,end);
//        String path3 = place1.getPath();
//        length += place1.getMinPathLength2();
//        Place.setMinPathLength2(length);
//        return new String[]{path1 + path2 + path3,length+"米"};
//    }
//
//    public static void fresh(){
//        for(Place p : map){
//            p.setVisited(false);
//        }
//    }

    public static String getOppositeDirection(String direction){
        switch (direction){
            case "东":return "西";
            case "西":return "东";
            case "南":return "北";
            case "北":return "南";
        }
        return null;
    }

    public static String toNoBom(String s){
        char[] c = s.toCharArray();
        char[] cc = new char[c.length - 1];
        for(int i = 0; i < cc.length; i++){
            cc[i] = c[i];
        }
        return String.valueOf(cc);
    }

    public String[] searchTag(String tag){
        Set<Place> result = new HashSet<>();
        for(Place place : map){
            if(place.getTag().contains(tag)){
                result.add(place);
            }
        }
        String s[] = new String[result.size()];
        int i = 0;
        for(Place place : result){
            s[i] = place.getName();
            i++;
        }
        return s;
    }

//    public static void main(String[] args){
//        PeiYangMap peiYangMap = new PeiYangMap();
//        Place begin = getPlace("天津大学幼儿园");
//        Place end = getPlace("诚园八斋理发店");
//        Guide guide = new  Guide(begin,end,2);
//        System.out.println(guide.get3());
//        System.out.println(guide.getlength3());
////        System.out.println(guide.get4());
////        for(Way way:getPlace("1895停车场").getP()[0].getWays()){
////            System.out.println(way.getEnd().getName());
////        }
//
////        String s[] = getShortestPath("诚园八斋","梅园（学一）");
////        System.out.println(s[0]);
////        System.out.println(s[1]);
////        fresh();
////        String ss[] = get2SPath("诚园八斋","梅园（学一）");
////        System.out.println(ss[0]);
////        System.out.println(ss[1]);
//    }
}
