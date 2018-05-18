/*
 * Copyright (c) 2018 Eduard Azymov
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package airlines;

import java.util.List;
import java.util.ArrayList;

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

        System.out.println("Начинаем путь с города " + firstCity.name);

        for (int idx = 1; idx < longestWay.size() - 1; idx++) {
            System.out.println("-> Летим в " + longestWay.get(idx).name);
        }

        System.out.println("Возвращаемся в " + firstCity.name);
    }
}
