package airlines;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public List<List<City>> getAllPaths(List<City> arr) {
        if (arr.size() > 1) {
            City beg = arr.get(0);
            List<List<City>> supArr = this.getAllPaths(arr.subList(1, arr.size()));
            List<List<City>> subArr = new ArrayList<>();

            for (int i = 0; i < supArr.size(); i++) {
                List<City> childArr = supArr.get(i);

                for (int j = 0; j <= childArr.size(); j++) {
                    List<City> temp = supArr.get(i).subList(0, j);
                    int supArrSize = supArr.size();
                    
                    temp.add(beg);
                    if (supArrSize > j) {
                        temp.addAll(supArr.get(i).subList(j, supArrSize));
                    }

                    subArr.add(temp);
                }
            }

            return subArr;
        } else {
            List<List<City>> result = new ArrayList<>();

            result.add(arr);

            return result;
        }
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
            
            if (path.size() == 0) continue;

            if (path.get(0) != firstCity) continue;

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

            if (noWay) breakPoint = idx;
        }

        return path.subList(0, breakPoint);
    }
}
