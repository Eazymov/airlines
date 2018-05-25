package airlines;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void main() {
        String[] ioExceptionArgs = {"NOT A PATH"};
        String[] parseExceptionArgs = {"./test/wrongFormatData.txt"};
        String[] rightArgs = {"./test/data.json"};
        String[] zeroWayArgs = {"./test/dataWithZeroWay.json"};

        assertThrows(
                IllegalArgumentException.class,
                () -> Main.main(ioExceptionArgs)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> Main.main(parseExceptionArgs)
        );

        Main.main(rightArgs);
        assertTrue(outContent.toString().length() > 0);

        outContent.reset();

        Main.main(zeroWayArgs);
        assertTrue(outContent.toString().length() > 0);
    }
}