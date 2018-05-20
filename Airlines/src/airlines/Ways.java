/*
 * The MIT License
 *
 * Copyright 2018 eazymov.
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

import java.util.Map;
import java.util.HashMap;

class Ways {
    private final Map<String, Map<String, Boolean>> map;

    /**
     * Инициализирует поле map
     */
    public Ways() {
        this.map = new HashMap<>();
    }

    /**
     * Добавляет путь между двумя городами 
     *
     * @param firstCityId  уникальный идентификатор первого города
     * @param secondCityId уникальный идентификатор второго города
     */
    public void addWay(String firstCityId, String secondCityId) {
        if (!map.containsKey(firstCityId)) {
            map.put(firstCityId, new HashMap<>());
        }

        if (!map.containsKey(secondCityId)) {
            map.put(secondCityId, new HashMap<>());
        }

        map.get(firstCityId).put(secondCityId, true);
        map.get(secondCityId).put(firstCityId, true);
    }
    
    /**
     * Проверяет есть ли путь между двумя городами
     *
     * @param firstCity  первый город
     * @param secondCity второй город
     * @return true если путь между двумя городами существует, иначе false
     */
    public boolean hasWay(City firstCity, City secondCity) {
        String firstCityId = firstCity.getId();
        String secondCityId = secondCity.getId();
        
        if (!map.containsKey(firstCityId)) return false;

        Map<String, Boolean> firstCityWays = map.get(firstCityId);

        return firstCityWays.containsKey(secondCityId);
    }
}
