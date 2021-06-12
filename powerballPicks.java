import java.util.Scanner;
import java.util.Arrays;


public class powerballPicks {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        
        int[] picks;
        int[] lastWeek;
        int numberOfNumbers;
        int entry;
        int debug = 1;
        int min;
        int max;
        int random;

        //Set how many numbers the game draws, and the range of numbers (eg Keno picks 20 from 1-80)
        System.out.println("How many numbers are drawn for the game?");
        numberOfNumbers = Integer.valueOf(scanner.nextLine());
        System.out.println("What is the lowest possible number that can be drawn? (Usually 1)");
        min = Integer.valueOf(scanner.nextLine());
        System.out.println("What is the highest number that can be drawn?");
        max = Integer.valueOf(scanner.nextLine());

        picks = new int[numberOfNumbers];
        lastWeek = new int[numberOfNumbers];

        //Loop through lastWeek array and fill with user entered numbers.        
        for (int i = 0; i < numberOfNumbers; i++){
            //Check number is valid powerball number
            while (true) {
                System.out.println("Enter last weeks Number " + (i+1) + ":");
                entry = Integer.valueOf(scanner.nextLine());
                if (entry <= max && entry >= min){
                    lastWeek[i] = entry;
                    break;
                } else {
                    System.out.println("Number must be between " + min + " and " + max + ".");
                    continue;
                } 
            }    

        }

        if (debug == 0) {
            System.out.println("Last weeks array filled");
            for (int d = 0; d < numberOfNumbers; d++) {
                System.out.print(lastWeek[d] + ", ");
            }
            System.out.println();
        }

        //Loop through picks array, generate random number, test if chosen already, or used last week, or less than 31 
        // to exclude birthdays, and enter into array

        for (int j = 0; j < numberOfNumbers; j++){
            while(true){
                random = (int)Math.floor(Math.random()*(max-min+1)+min);  
                boolean matchLastWeek = contains(lastWeek, random);
                boolean pickedThisWeek = contains(picks, random);
                if(debug == 0){
                    System.out.println("Last week " + random + " was picked: " + matchLastWeek);
                    System.out.println("This week " + random + " already picked: " + pickedThisWeek);
                }
                if(matchLastWeek == true || pickedThisWeek == true){
                    continue;
                }
                if (random >= 1 && random <= 31){
                    continue;
                } 
                else{
                    picks[j] = random;
                    break;
                }
            }
        }
        if (debug == 0){
            System.out.println("New picks array filled");
            for (int b = 0; b < numberOfNumbers; b++) {
                System.out.print(picks[b] + ", ");
            }
            System.out.println();
        }

        //output picks
        System.out.println("Your randomized, less common numbers to pick are: ");
        printArray(picks);
        System.out.println();
        System.out.println("GOOD LUCK!");

        scanner.close();
    }

    // Method for checking if value is in an array
    public static boolean contains(final int[] arr, final int key){
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    // Method for printing array
    public static void printArray(int arr[]) {
        int l = arr.length;
        for (int p = 0; p < l; p++){
            System.out.print(arr[p] + " ");
        }
    }
    
}
