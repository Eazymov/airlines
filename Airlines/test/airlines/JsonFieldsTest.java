package airlines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonFieldsTest {

    @Test
    void testToString() {
        String firstCityId = JsonFields.FIRST_CITY_ID.toString();
        String cities = JsonFields.CITIES.toString();
        String ways = JsonFields.WAYS.toString();

        assertNotNull(firstCityId);
        assertNotNull(cities);
        assertNotNull(ways);
    }
}