import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StringManipulationTest {
    private StringManipulation stringManipulation = new StringManipulation();

    private String input;
    private String expected;

    @Parameterized.Parameters
    public static Collection<String[]> testConditions() {
        String[][] expectedOutput = {
            {"ABCD", "BCD"},
            {"AABC", "BC"},
            {"PLIFF", "PLIFF"},
            {"AAAA", "AA"}
        };

        return Arrays.asList(expectedOutput);
    }

    public StringManipulationTest(String input, String expected) {
        super();
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void truncateTest() {
        Assert.assertEquals(expected, stringManipulation.truncateAInFirstTwoPositions(input));
    }

    @Test
    public void testFirstTwoAreSameAsLastTwo() {
        Assert.assertTrue(stringManipulation.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }

    @Test
    public void testFirstTwoAreNotSameAsLastTwo() {
        Assert.assertFalse(stringManipulation.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }
}
