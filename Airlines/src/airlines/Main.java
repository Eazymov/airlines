/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author eazymov
 */
public class Main {
    public static void main(String[] args) {
        City Vancouver = new City("Vancouver");
        City Yellowknife = new City("Yellowknife");
        City Edmonton = new City("Edmonton");
        City Calgary = new City("Calgary");
        City Winnipeg = new City("Winnipeg");
        City Toronto = new City("Toronto");
        City Montreal = new City("Montreal");
        City Halifax = new City("Halifax");

        WaysList waysList = new WaysList();

        ArrayList<City> cities = new ArrayList<>();
        
        cities.add(Vancouver);
        cities.add(Yellowknife);
        cities.add(Edmonton);
        cities.add(Calgary);
        cities.add(Winnipeg);
        cities.add(Toronto);
        cities.add(Montreal);
        cities.add(Halifax);
        
        waysList.addWay(Vancouver, Edmonton);
        waysList.addWay(Vancouver, Calgary);
        waysList.addWay(Calgary, Winnipeg);
        waysList.addWay(Winnipeg, Toronto);
        waysList.addWay(Toronto, Halifax);
        waysList.addWay(Montreal, Halifax);
        waysList.addWay(Edmonton, Montreal);
        waysList.addWay(Edmonton, Yellowknife);
        waysList.addWay(Edmonton, Calgary);
        
        City firstCity = Vancouver;
        LongestPathFinder longestPathFinder = new LongestPathFinder();
        List<City> longestWay = longestPathFinder.find(cities, waysList, firstCity);

        for (int idx = 0; idx < longestWay.size(); idx++) {
            System.out.println(longestWay.get(idx).name);
        }
    }
}
