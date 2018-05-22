package airlines;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        longestPathFinder.readFromFile("./test/data.json");

        List<City> path = longestPathFinder.find();

        assertNotNull(path);
    }
}