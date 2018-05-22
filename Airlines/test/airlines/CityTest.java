package airlines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CityTest {
    String id = "id";
    String name = "name";

    City city;

    @BeforeEach
    void setup() {
        city = new City(id, name);
    }

    @Test
    void getId() {
        assert city.getId() == id;
    }

    @Test
    void getName() {
        assert city.getName() == name;
    }
}