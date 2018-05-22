package airlines;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpersTest {

    @Test
    void getListPermutations() {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        List<List<Integer>> permutations = Helpers.getListPermutations(list);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1),
                Arrays.asList(1, 3, 2),
                Arrays.asList(3, 1, 2),
                Arrays.asList(3, 2, 1)
        );

        assertTrue(permutations.equals(expected));
    }
}