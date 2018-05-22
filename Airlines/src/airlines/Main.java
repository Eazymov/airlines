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

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class Main {

    /**
     * Получает путь к файлу с данными и выводит длиннейший путь исходя
     * из этих данных
     *
     * @param args аргументы программы
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Path to file is not passed");
        }

        try {
            String src = args[0];
            LongestPathFinder longestPathFinder = new LongestPathFinder();
            longestPathFinder.readFromFile(src);
            List<City> longestPath = longestPathFinder.find();

            printPath(longestPath);

        } catch (IOException e) {
            throw new IllegalArgumentException("File not exists");

        } catch (ParseException e) {
            throw new IllegalArgumentException("File is incorrect");
        }
    }

    /**
     * Выводит путь
     *
     * @param path список городов
     */
    private static void printPath(List<City> path) {
        if (path.size() < 3) {
            System.out.println("Путь не может быть построен");
            return;
        }

        City firstCity = path.get(0);
        System.out.println("Начинаем путь с города " + firstCity.getName());

        for (int idx = 1; idx < path.size() - 1; idx++) {
            System.out.println("-> Летим в " + path.get(idx).getName());
        }

        System.out.println("Возвращаемся в " + firstCity.getName());
    }
}
