// #Task 1 = NumberGuessingGame 
/**
 * The NumberGuessingGame class implements a simple number guessing game.
 * The game generates a random target number between a lower and upper bound.
 * The player has a limited number of attempts to guess the target number.
 * After each round, the player is asked if they want to play another round.
 * The game calculates and displays the average number of attempts per round
 * after the player decides to stop playing.
 */
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    /**
     * This method starts and manages the number guessing game.
     */
    public static void playGuessingGame() 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        final int LOWER_BOUND = 1;
        final int UPPER_BOUND = 100;
        final int MAX_ATTEMPTS = 10;
        
        int rounds = 0;             // Number of rounds played
        int totalAttempts = 0;      // Total attempts made by the player

        while (true) 
        {
            rounds++;
            int targetNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
            System.out.println("\nRound " + rounds + " - Guess the number between " + LOWER_BOUND + " and " + UPPER_BOUND);

            // Loop for each attempt in a round
            for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) 
            {
                System.out.print("Attempt " + attempt + "/" + MAX_ATTEMPTS + ": Enter your guess: ");

                int userGuess;
                while (true) 
                {
                    try 
                    {
                        userGuess = scanner.nextInt();      // Get user input
                        break;
                    } 
                    catch (Exception e) 
                    {
                        scanner.nextLine();     // Clear the input buffer
                        System.out.print("Invalid input. Please enter a valid number: ");
                    }
                }

                // Compare user's guess with the target number
                if (userGuess < targetNumber) 
                {
                    System.out.println("Too low! Try a higher number.");
                } 
                else if (userGuess > targetNumber) 
                {
                    System.out.println("Too high! Try a lower number.");
                } else 

                {
                    System.out.println("Congratulations! You guessed the correct number " + targetNumber + " in " + attempt + " attempts.");
                    totalAttempts += attempt;   // Update total attempts
                    break;      // End the current round
                }
            }

            // Ask if the player wants to play another round
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) 
            {
                break;      // Exit the game loop
            }
        }

        // Calculate and display average attempts per round
        double averageAttempts = (double) totalAttempts / rounds;
        System.out.println("\nGame over! You played " + rounds + " rounds with an average of " + String.format("%.2f", averageAttempts) + " attempts per round.");

        scanner.close();    // Close the scanner
    }

    public static void main(String[] args) 
    {
        playGuessingGame();
    }

}