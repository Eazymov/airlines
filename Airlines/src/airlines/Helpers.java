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

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    /**
     * Возвращает список всех возможных вариаций расстановки элементов списка
     *
     * @param <T>  Тип элемента в массиве
     * @param list массив элементов
     * @return {@code List<List<T>}
     */
    public static <T> List<List<T>> getListPermutations(List<T> list) {
        if (list.isEmpty()) {
            List<List<T>> result = new ArrayList<>();

            result.add(new ArrayList<>());

            return result;
        }

        T firstElement = list.remove(0);
        List<List<T>> returnValue = new ArrayList<>();
        List<List<T>> permutations = getListPermutations(list);

        permutations.stream().forEach((List<T> smallerPermutated) -> {
            for (int idx = 0; idx <= smallerPermutated.size(); idx++) {
                List<T> temp = new ArrayList<>(smallerPermutated);

                temp.add(idx, firstElement);
                returnValue.add(temp);
            }
        });

        return returnValue;
    }
}
