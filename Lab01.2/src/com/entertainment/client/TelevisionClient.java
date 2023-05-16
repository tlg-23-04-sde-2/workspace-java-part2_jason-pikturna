package com.entertainment.client;

import com.entertainment.Television;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TelevisionClient {

    public static void main(String[] args) {

        Television tv1 = new Television();
        Television tv2 = new Television("RCA", 10);

        System.out.println(tv1); //toString() automatically called
        System.out.println(tv2);

        tv2.changeChannel(9);
        System.out.println(tv2);
        System.out.println();


        Television tvA = new Television("Sony", 50); // 54
        Television tvB = new Television("Sony", 50); // 54
        Television tvC = new Television("Sony", 52);
        Television tvD = new Television("Sony", 12);


        System.out.println("tvA == tvB: " + (tvA == tvB));
        System.out.println("tvA.equals(tvB): " + tvA.equals(tvB));
        System.out.println();

        Set<Television> tvs = new TreeSet<>();
        tvs.add(tvA);
        tvs.add(tvB);// should be rejected as a duplicate, and size is still 1
        tvs.add(tvC);
        tvs.add(tvD);

        for (Television tv: tvs) {
            System.out.println(tv);
        }

        System.out.println("the size of the Set is:  " + tvs.size());

    }
}