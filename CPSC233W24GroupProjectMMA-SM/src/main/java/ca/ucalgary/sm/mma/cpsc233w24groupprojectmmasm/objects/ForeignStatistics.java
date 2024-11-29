package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

/**
 * Enums for foreign stats
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public enum ForeignStatistics {;

    //Hashmap containing the different currency conversions from CAD
    public static final Map<String, Double> Currencies = new HashMap<>() {{
        put("USA", 0.740755);
        put("Italy" , 0.684438);
        put("UK" , 0.584325);
        put("India" , 61.394783);
        put( "Australia" , 1.128399);
        put( "Singapore" , 0.994932);
        put("Switzerland" , 0.652570);
        put("Malaysia" , 3.538738);
        put("Japan" , 111.400330);
        put("China" , 5.329932);
        put("Pakistan", 203.22);
    }};

    //Hashmap containing the different interest rates in different countries
    public static final Map<String, Double> InterestRate = new HashMap<>() {{
        put("USA", 0.055);
        put("Canada", 0.05);
        put("Switzerland" , 0.0175);
        put("UK" , 0.0525);
        put("India" , 0.065);
        put( "Australia" , 0.0435);
        put( "Singapore" , 0.0375);
        put("Italy" , 0.0381);
        put("Malaysia" , 0.03);
        put("China" , 0.0345);
        put("Pakistan", 0.22);
    }};

    public static final ArrayList<String> countryLists = new ArrayList<>() {{
        add("USA");
        add("Canada");
        add("Switzerland");
        add("UK");
        add("India");
        add("Australia");
        add("Singapore");
        add("Italy");
        add("Malaysia");
        add("China");
        add("Pakistan");
    }};

    //
    public static final Map<Integer , String> suggestiveHoliday = new HashMap<>() {{
        put(1, "Havana, Dominican Republic, Cuba");
        put(2, "Netherlands Antilles, Mexico, Grenada, Guadeloupe");
        put(3, "Dubai UAE, Australia, New Zealand, Japan");
    }};

}
