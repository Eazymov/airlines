package airlines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void getId() {
        String id = "id";
        String name = "name";
        City city = new City(id, name);

        assert city.getId() == id;
    }

    @Test
    void getName() {
        String id = "id";
        String name = "name";
        City city = new City(id, name);

        assert city.getName() == name;
    }
}