package airlines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaysTest {
    Ways ways;

    City Vancouver;
    City Toronto;
    City Another;

    @BeforeEach
    void setup() {
        Vancouver = new City("0", "Vancouver");
        Toronto = new City("1", "Toronto");
        Another = new City("2", "Another");

        ways = new Ways();
    }

    @Test
    void addWay() {
        assert ways.hasWay(Vancouver, Toronto) == false;

        ways.addWay(Vancouver.getId(), Toronto.getId());

        assert ways.hasWay(Vancouver, Toronto) == true;
    }

    @Test
    void hasWay() {
        assert ways.hasWay(Vancouver, Toronto) == false;

        ways.addWay(Vancouver.getId(), Toronto.getId());

        assert ways.hasWay(Vancouver, Toronto) == true;
        assert ways.hasWay(Another, Toronto) == false;
    }
}