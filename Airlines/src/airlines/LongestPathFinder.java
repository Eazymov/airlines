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
public class LongestPathFinder {
    City firstCity;
    WaysList waysList;
    List<City> cities;

    public List<City> find(
            List<City> cities,
            WaysList waysList,
            City firstCity
    ) {
        this.cities = cities;
        this.waysList = waysList;
        this.firstCity = firstCity;

        List<List<City>> allPaths = this.getAllPaths(cities);
        List<List<City>> correctPaths = this.getCorrectPaths(allPaths);

        return this.getLongestPath(correctPaths);
    }

    private List<List<City>> getAllPaths(List<City> original) {
        if (original.isEmpty()) {
            List<List<City>> result = new ArrayList<>();

            result.add(new ArrayList<>());

            return result;
        }

        City firstElement = original.remove(0);
        List<List<City>> returnValue = new ArrayList<>();
        List<List<City>> permutations = getAllPaths(original);

        permutations.stream().forEach(smallerPermutated -> {
            for (int idx = 0; idx <= smallerPermutated.size(); idx++) {
                List<City> temp = new ArrayList<>(smallerPermutated);

                temp.add(idx, firstElement);
                returnValue.add(temp);
            }
        });

        return returnValue;
    }

    private List<City> getLongestPath(
            List<List<City>> paths
    ) {
        List<City> longestPath = new ArrayList<>();

        for (int idx = 0; idx < paths.size(); idx++) {
            List<City> curPath = paths.get(idx);

            if (curPath.size() > longestPath.size()) {
                longestPath = curPath;
            }
        }

        return longestPath;
    }

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

    private List<City> correctPath(List<City> path) {
        int size = path.size();
        int breakPoint = size;

        for (int idx = 1; idx < size; idx++) {
            City currentCity = path.get(idx);
            City previousCity = path.get(idx - 1);

            boolean noWay = !this.waysList.hasWay(previousCity, currentCity);

            if (noWay) {
                breakPoint = idx;
            }
        }

        return path.subList(0, breakPoint);
    }
}
