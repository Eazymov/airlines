package airlines;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaysTest {

    @Test
    void addWay() {
        Ways ways = new Ways();
        City Vancouver = new City("id", "Vancouver");
        City Toronto = new City("id", "Toronto");

        assert ways.hasWay(Vancouver, Toronto) == false;

        ways.addWay(Vancouver.getId(), Toronto.getId());

        assert ways.hasWay(Vancouver, Toronto) == true;
    }

    @Test
    void hasWay() {
        Ways ways = new Ways();
        City Vancouver = new City("id", "Vancouver");
        City Toronto = new City("id", "Toronto");

        assert ways.hasWay(Vancouver, Toronto) == false;

        ways.addWay(Vancouver.getId(), Toronto.getId());

        assert ways.hasWay(Vancouver, Toronto) == true;
    }
}