package airlines;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LongestPathFinderTest {
    LongestPathFinder longestPathFinder;

    @BeforeEach
    void setup() {
        longestPathFinder = new LongestPathFinder();
    }

    @Test
    void readFromFile() {
        assertThrows(
                IllegalArgumentException.class,
                () -> longestPathFinder.readFromFile("./test/dataWithoutFirstCityId.json")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> longestPathFinder.readFromFile("./test/dataWithoutCities.json")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> longestPathFinder.readFromFile("./test/dataWithoutWays.json")
        );
    }

    @Test
    void find() throws IOException, ParseException {
        City Vancouver = new City("Vancouver", "Vancouver");
        City Edmonton = new City("Edmonton", "Edmonton");
        City Winnipeg = new City("Winnipeg", "Winnipeg");
        ArrayList<City> cities = new ArrayList<>();

        cities.add(Vancouver);
        cities.add(Edmonton);
        cities.add(Winnipeg);
        Ways ways = new Ways();

        ways.addWay(Vancouver.getName(), Edmonton.getName());

        longestPathFinder.setFirstCity(Vancouver);
        longestPathFinder.setCities(cities);
        longestPathFinder.setWays(ways);

        List<City> longestPath = longestPathFinder.find();
        List<City> expected = Arrays.asList(Vancouver, Edmonton, Vancouver);

        assertTrue(longestPath.equals(expected));

        ways.addWay(Edmonton.getName(), Winnipeg.getName());
        ways.addWay(Vancouver.getName(), Winnipeg.getName());

        longestPath = longestPathFinder.find();

        assertTrue(longestPath.size() == 4);
    }
}