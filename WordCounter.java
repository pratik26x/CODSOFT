import java.util.Scanner;

public class WordCounter 
{
    public static void main(String[] args) 
    {
        // Step 1: Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Step 2: Prompt the user to enter text
        System.out.println("Enter a text or provide a file to count the words:");
        
        // Step 3: Read the user's input as a string
        String input = scanner.nextLine();
        
        // Step 4: Split the input string into an array of words using spaces and punctuation as delimiters
        String[] words = input.split("[\\s\\p{Punct}]+");
        
        // Step 5: Initialize a counter variable to keep track of the number of words
        int wordCount = 0;
        
        // Step 6: Iterate through the array of words and increment the counter for each word encountered
        for (String word : words) 
        {
            // Ignore empty strings
            if (!word.isEmpty()) 
            {
                wordCount++;
            }
        }
        
        // Step 7: Display the total count of words to the user
        System.out.println("Total words: " + wordCount);
        
        // Step 8: Close the scanner to release resources
        scanner.close();
    }
}

