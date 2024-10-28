package Task01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EnhancedFileSearcher {
    public static void main(String[] args) {
        // Validate command-line arguments
        if (args.length < 2) {
            System.out.println("Usage: java EnhancedFileSearcher <directory_path> <file_name1> [<file_name2> ...] [case_sensitive=true/false]");
            return;
        }

        // Parse command-line arguments
        String directoryPath = args[0];
        List<String> fileNames = new ArrayList<>();
        boolean caseSensitive = true;

        // Collect file names and check for case sensitivity option
        for (int i = 1; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("case_sensitive=false")) {
                caseSensitive = false;
            } else if (args[i].equalsIgnoreCase("case_sensitive=true")) {
                caseSensitive = true;
            } else {
                fileNames.add(args[i]);
            }
        }

        // Validate if the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a directory.");
            return;
        }

        // Start the file search
        for (String fileName : fileNames) {
            int count = searchFiles(directory, fileName, caseSensitive, new ArrayList<>());
            if (count == 0) {
                System.out.println("File not found: " + fileName);
            } else {
                System.out.println("Occurrences of '" + fileName + "': " + count);
            }
        }
    }

    /**
     * Recursively searches for the specified file within the given directory and its subdirectories.
     *
     * @param directory The directory to search within.
     * @param fileName The name of the file to search for.
     * @param caseSensitive Determines if the search should be case-sensitive.
     * @param foundFiles List to keep track of found file paths.
     * @return The number of times the file was found.
     */
    public static int searchFiles(File directory, String fileName, boolean caseSensitive, List<String> foundFiles) {
        File[] files = directory.listFiles();
        int count = 0;

        if (files == null) {
            return 0; // Return if directory is inaccessible
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively search in subdirectories
                count += searchFiles(file, fileName, caseSensitive, foundFiles);
            } else {
                // Check if the file matches the search criteria
                if (matchesFileName(file.getName(), fileName, caseSensitive)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    foundFiles.add(file.getAbsolutePath());
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if the file name matches the target file name, considering case sensitivity.
     *
     * @param actualName The actual file name.
     * @param targetName The target file name.
     * @param caseSensitive If true, the comparison is case-sensitive.
     * @return True if the names match, false otherwise.
     */
    private static boolean matchesFileName(String actualName, String targetName, boolean caseSensitive) {
        if (caseSensitive) {
            return actualName.equals(targetName);
        } else {
            return actualName.equalsIgnoreCase(targetName);
        }
    }
}