import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextDiffTool {

    public static void main(String[] args) {
        String filePath1 = "path/to/firstFile.txt";
        String filePath2 = "path/to/secondFile.txt";
        boolean caseSensitive = false; // Toggle for case sensitivity

        try {
            List<String> lines1 = Files.readAllLines(Paths.get(filePath1));
            List<String> lines2 = Files.readAllLines(Paths.get(filePath2));

            if (lines1.size() != lines2.size()) {
                System.out.println("Files have different number of lines and cannot be compared directly.");
            } else {
                for (int i = 0; i < lines1.size(); i++) {
                    compareLines(lines1.get(i), lines2.get(i), i + 1, caseSensitive);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }

    private static void compareLines(String line1, String line2, int lineNumber, boolean caseSensitive) {
        if (!caseSensitive) {
            line1 = line1.toLowerCase();
            line2 = line2.toLowerCase();
        }

        if (!line1.equals(line2)) {
            System.out.printf("Difference found at line %d:\n", lineNumber);
            int minLength = Math.min(line1.length(), line2.length());
            for (int index = 0; index < minLength; index++) {
                if (line1.charAt(index) != line2.charAt(index)) {
                    int start = index;
                    while (index < minLength && line1.charAt(index) != line2.charAt(index)) {
                        index++;
                    }
                    int end = index;
                    System.out.printf("Difference from index %d to %d: '%s' vs '%s'\n",
                            start, end - 1, line1.substring(start, end), line2.substring(start, end));
                }
            }
            if (line1.length() != line2.length()) {
                System.out.printf("Additional characters from index %d in %s: '%s'\n",
                        minLength, line1.length() > line2.length() ? "File1" : "File2",
                        line1.length() > line2.length() ? line1.substring(minLength) : line2.substring(minLength));
            }
        }
    }
}
