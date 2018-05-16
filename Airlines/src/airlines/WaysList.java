/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlines;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author eazymov
 */
public class WaysList {
    private final Map<String, Map<String, Boolean>> map;

    public WaysList() {
        this.map = new HashMap<>();
    }
    
    public void addWay(City cityA, City cityB) {
        String cityAid = cityA.id;
        String cityBid = cityB.id;

        if (!map.containsKey(cityAid)) {
            map.put(cityAid, new HashMap<>());
        }

        if (!map.containsKey(cityBid)) {
            map.put(cityBid, new HashMap<>());
        }
        
        map.get(cityAid).put(cityBid, true);
        map.get(cityBid).put(cityAid, true);
    }
    
    public boolean hasWay(City cityA, City cityB) {
        String cityAid = cityA.id;
        String cityBid = cityB.id;

        Map<String, Boolean> cityAways = map.get(cityAid);
        
        return cityAways.get(cityBid);
    }
}
