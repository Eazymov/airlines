/*
 * The MIT License
 *
 * Copyright 2018 Eduard Azymov.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package airlines;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LongestPathFinder {
    private City firstCity;
    private WaysList waysList;
    private List<City> cities;
    
    /**
     * Инициализирует значения по умолчанию
     */
    public LongestPathFinder() {
        this.cities = new ArrayList<>();
        this.waysList = new WaysList();
    }
    
    /**
     * Задает поле cities
     *
     * @param cities список городов
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    /**
     * Возвращает поле cities
     *
     * @return {@code List<City>}
     */
    public List<City> getCities() {
        return this.cities;
    }
    
    /**
     * Задает поле waysList
     *
     * @param waysList набор всех путей между городами
     */
    public void setWaysList(WaysList waysList) {
        this.waysList = waysList;
    }
    
    /**
     * Возвращает поле waysList
     *
     * @return {@code WaysList}
     */
    public WaysList getWaysList() {
        return this.waysList;
    }
    
    /**
     * Задает поле firstCity
     *
     * @param firstCity город с которого начинается путешествие
     */
    public void setFirstCity(City firstCity) {
        this.firstCity = firstCity;
    }
    
    /**
     * Возвращает поле firstCity
     *
     * @return {@code City}
     */
    public City getFirstCity() {
        return this.firstCity;
    }
    
    public void readFromFile(String src) {
        JSONParser parser = new JSONParser();

        try {
            Object text = parser.parse(new FileReader(src));

            JSONObject json = (JSONObject) text;
            JSONArray citiesJson = (JSONArray) json.get("cities");
            JSONArray waysListJson = (JSONArray) json.get("ways");
            
            Iterator<JSONObject> citiesIterator = citiesJson.iterator();
            
            while (citiesIterator.hasNext()) {
                JSONObject cityJson = citiesIterator.next();
                String cityName = (String) cityJson.get("name");
                City city = new City(cityName);
                
                this.cities.add(city);
            }
            
            Iterator<JSONArray> waysListIterator = citiesJson.iterator();
            
            while (waysListIterator.hasNext()) {
                JSONArray citiesPair = waysListIterator.next();
                
                // this.waysList.addWay(citiesPair.get(0), citiesPair.get(1));                
            }
        } catch (IOException | ParseException e) {
        }
    }

    /**
     * Находит длиннейший возможный путь
     * 
     * @return {@code City<List>}
     */
    public List<City> find() {
        List<List<City>> allPaths = Helpers.getListPermutations(cities);
        List<List<City>> correctPaths = this.getCorrectPaths(allPaths);

        return this.getLongestPath(correctPaths);
    }

    /**
     * Возвращает длиннейший путь из списка переданных
     * 
     * @param paths список путей
     * @return {@code List<City>}
     */
    private List<City> getLongestPath(List<List<City>> paths) {
        List<City> longestPath = new ArrayList<>();

        for (int idx = 0; idx < paths.size(); idx++) {
            List<City> curPath = paths.get(idx);

            if (curPath.size() > longestPath.size()) {
                longestPath = curPath;
            }
        }

        return longestPath;
    }

    /**
     * Возвращает отфильтрованный список состоящий из корректных
     * неразрывных путей
     *
     * @param paths корректные пути
     * @return {@code List<List<City>>}
     */
    public List<List<City>> getCorrectPaths(List<List<City>> paths) {
        List<List<City>> correctPaths = new ArrayList<>();

        for (int idx = 0; idx < paths.size(); idx++) {
            List<City> path = paths.get(idx);

            if (path.isEmpty()) {
                continue;
            }

            if (path.get(0) != firstCity) {
                continue;
            }

            List<City> correctedPath = this.correctPath(path);
            City lastCity = correctedPath.get(correctedPath.size() - 1);

            if (waysList.hasWay(lastCity, firstCity)) {
                correctedPath.add(firstCity);
                correctPaths.add(correctedPath);
            }
        }

        return correctPaths;
    }

    /**
     * Возвращает путь обрезанный на точке разрыва
     * 
     * @param path путь
     * @return {@code List<City>}
     */
    private List<City> correctPath(List<City> path) {
        int size = path.size();
        int breakPoint = size;

        for (int idx = 1; idx < size; idx++) {
            City currentCity = path.get(idx);
            City previousCity = path.get(idx - 1);

            boolean hasWay = this.waysList.hasWay(previousCity, currentCity);

            if (!hasWay) {
                breakPoint = idx;
            }
        }

        return path.subList(0, breakPoint);
    }
}
