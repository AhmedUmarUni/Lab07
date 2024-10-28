package Task01;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EnhancedFileSearcherTest {

    @Test
    public void testFileFound() {
        // Arrange: Create a temporary directory and file for testing
        File testDirectory = new File("testDir");
        File testFile = new File("testDir/testFile.txt");
        testDirectory.mkdir();
        try {
            testFile.createNewFile();
        } catch (Exception e) {
            fail("Setup failed: Unable to create test file.");
        }

        // Act: Search for the file in the test directory
        int result = EnhancedFileSearcher.searchFiles(testDirectory, "testFile.txt", true, new ArrayList<>());

        // Assert: Verify that the file is found once
        assertEquals(1, result);

        // Cleanup: Delete the test file and directory
        testFile.delete();
        testDirectory.delete();
    }

    @Test
    public void testFileNotFound() {
        File testDirectory = new File("testDir");

        // Act: Search for a non-existent file
        int result = EnhancedFileSearcher.searchFiles(testDirectory, "nonExistentFile.txt", true, new ArrayList<>());

        // Assert: Verify that the file is not found
        assertEquals(0, result);
    }

    @Test
    public void testCaseInsensitiveSearch() {
        File testDirectory = new File("testDir");
        File testFile = new File("testDir/CaseTest.txt");
        testDirectory.mkdir();
        try {
            testFile.createNewFile();
        } catch (Exception e) {
            fail("Setup failed: Unable to create test file.");
        }

        // Act: Search for the file case-insensitively
        int result = EnhancedFileSearcher.searchFiles(testDirectory, "casetest.txt", false, new ArrayList<>());

        // Assert: Verify that the file is found despite case difference
        assertEquals(1, result);

        // Cleanup: Delete the test file and directory
        testFile.delete();
        testDirectory.delete();
    }
}
