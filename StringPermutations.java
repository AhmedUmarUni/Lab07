package Task02;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringPermutations {

    public static void main(String[] args) {
        // Check for valid input
        if (args.length < 2) {
            System.out.println("Usage: java StringPermutations <input_string> <allow_duplicates=true/false>");
            return;
        }

        String input = args[0];
        boolean allowDuplicates = Boolean.parseBoolean(args[1]);

        // Handle the case where the input string is empty
        if (input.isEmpty()) {
            System.out.println("The input string is empty. Please provide a valid string.");
            return;
        }

        // Generate and display permutations
        List<String> permutations;
        if (allowDuplicates) {
            permutations = generatePermutations(input);
        } else {
            permutations = generateUniquePermutations(input);
        }

        System.out.println("Permutations of '" + input + "':");
        for (String perm : permutations) {
            System.out.println(perm);
        }

        // Compare with non-recursive approach
        List<String> nonRecursivePermutations = generatePermutationsIterative(input);
        System.out.println("\nNon-recursive Permutations of '" + input + "':");
        for (String perm : nonRecursivePermutations) {
            System.out.println(perm);
        }
    }

    /**
     * Generates all permutations of the given string (including duplicates).
     *
     * @param input The input string for which permutations are to be generated.
     * @return A list of all permutations of the input string.
     */
    public static List<String> generatePermutations(String input) {
        List<String> permutations = new ArrayList<>();
        permute(input.toCharArray(), 0, permutations);
        return permutations;
    }

    /**
     * Generates all unique permutations of the given string, excluding duplicates.
     *
     * @param input The input string for which unique permutations are to be generated.
     * @return A list of unique permutations of the input string.
     */
    public static List<String> generateUniquePermutations(String input) {
        Set<String> uniquePermutations = new HashSet<>();
        permuteUnique(input.toCharArray(), 0, uniquePermutations);
        return new ArrayList<>(uniquePermutations);
    }

    /**
     * Recursively generates permutations by swapping characters.
     *
     * @param chars The character array of the input string.
     * @param index The current index for swapping.
     * @param permutations The list that collects all permutations.
     */
    private static void permute(char[] chars, int index, List<String> permutations) {
        if (index == chars.length - 1) {
            permutations.add(new String(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i); // Swap characters
            permute(chars, index + 1, permutations); // Recurse
            swap(chars, index, i); // Backtrack
        }
    }

    /**
     * Recursively generates unique permutations by swapping characters.
     *
     * @param chars The character array of the input string.
     * @param index The current index for swapping.
     * @param permutations The set that collects all unique permutations.
     */
    private static void permuteUnique(char[] chars, int index, Set<String> permutations) {
        if (index == chars.length - 1) {
            permutations.add(new String(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i); // Swap characters
            permuteUnique(chars, index + 1, permutations); // Recurse
            swap(chars, index, i); // Backtrack
        }
    }

    /**
     * Swaps characters at two positions in a character array.
     *
     * @param chars The character array.
     * @param i The first position.
     * @param j The second position.
     */
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    /**
     * Generates permutations using an iterative approach (non-recursive).
     *
     * @param input The input string for which permutations are to be generated.
     * @return A list of permutations generated iteratively.
     */
    public static List<String> generatePermutationsIterative(String input) {
        List<String> permutations = new ArrayList<>();
        int n = input.length();
        int[] indexes = new int[n];
        char[] chars = input.toCharArray();
        permutations.add(new String(chars));

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(chars, i % 2 == 0 ? 0 : indexes[i], i);
                permutations.add(new String(chars));
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }

        return permutations;
    }
}
