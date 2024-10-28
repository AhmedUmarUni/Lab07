package Task02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class StringPermutationsTest {

    @Test
    void testEmptyString() {
        List<String> result = StringPermutations.generatePermutations("");
        assertTrue(result.isEmpty(), "Empty string should return an empty list.");
    }

    @Test
    void testSingleCharacter() {
        List<String> result = StringPermutations.generatePermutations("a");
        assertEquals(1, result.size(), "Single character string should return itself.");
        assertTrue(result.contains("a"), "Result should contain the original character.");
    }

    @Test
    void testTwoCharacters() {
        List<String> result = StringPermutations.generatePermutations("ab");
        assertEquals(2, result.size(), "Two characters should return two permutations.");
        assertTrue(result.contains("ab") && result.contains("ba"), "Result should contain both permutations.");
    }

    @Test
    void testThreeCharacters() {
        List<String> result = StringPermutations.generatePermutations("abc");
        assertEquals(6, result.size(), "Three characters should return six permutations.");
        assertTrue(result.contains("abc") && result.contains("acb") &&
                   result.contains("bac") && result.contains("bca") &&
                   result.contains("cab") && result.contains("cba"),
                   "Result should contain all six permutations.");
    }

    @Test
    void testDuplicateCharactersAllowDuplicates() {
        List<String> result = StringPermutations.generatePermutations("aab");
        assertEquals(6, result.size(), "With duplicates allowed, there should be 6 permutations.");
    }

    @Test
    void testDuplicateCharactersNoDuplicates() {
        List<String> result = StringPermutations.generateUniquePermutations("aab");
        assertEquals(3, result.size(), "With duplicates disallowed, there should be 3 unique permutations.");
        assertTrue(result.contains("aab") && result.contains("aba") && result.contains("baa"),
                   "Unique permutations should be aab, aba, and baa.");
    }

    @Test
    void testCaseSensitivity() {
        List<String> result = StringPermutations.generatePermutations("Aa");
        assertEquals(2, result.size(), "Permutations should be case-sensitive.");
        assertTrue(result.contains("Aa") && result.contains("aA"), "Result should be case-sensitive.");
    }

    @Test
    void testIterativePermutation() {
        List<String> recursiveResult = StringPermutations.generatePermutations("abc");
        List<String> iterativeResult = StringPermutations.generatePermutationsIterative("abc");

        Set<String> recursiveSet = new HashSet<>(recursiveResult);
        Set<String> iterativeSet = new HashSet<>(iterativeResult);

        assertEquals(recursiveSet, iterativeSet, "Iterative and recursive permutations should match.");
    }
}
