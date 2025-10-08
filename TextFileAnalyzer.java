import java.io.*;
import java.util.Scanner;

public class TextFileAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Ask for file path
            System.out.print("Enter the file name or path: ");
            String fileName = scanner.nextLine();
            
            File file = new File(fileName);
            
            if (!file.exists()) {
                System.out.println("File not found!");
                return;
            }

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            // Read the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                String[] words = line.trim().split("\\s+"); // split by whitespace
                if (line.trim().length() > 0)
                    wordCount += words.length;
            }

            reader.close();

            System.out.println("\n--- File Analysis ---");
            System.out.println("Total Lines: " + lineCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Total Characters: " + charCount);

            // Search for a word
            System.out.print("\nEnter a word to search: ");
            String searchWord = scanner.nextLine().toLowerCase();

            int occurrences = 0;
            BufferedReader searchReader = new BufferedReader(new FileReader(file));

            while ((line = searchReader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\W+"); // split by non-word characters
                for (String word : words) {
                    if (word.equals(searchWord)) {
                        occurrences++;
                    }
                }
            }

            searchReader.close();

            System.out.println("The word \"" + searchWord + "\" occurs " + occurrences + " times.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
