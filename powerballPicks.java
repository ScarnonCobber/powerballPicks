import java.util.Scanner;
import java.util.Arrays;
/* *************************************
   * Program to choose powerball numbers; 8 numbers between 1 and 45.
   * Aims to maximize winnings by not sharing any wins with other players. As people often choose
   * last weeks numbers or birthdates, the program asks the user to enter last weeks numbers and
   * generates random numbers excluding previous weeks and numbers under 12 (only using birth months
   * so as to leave reasonable amount of remaining options).
   * 
   * Ben Alias, 11/6/2021
   * 
   ************************************ 
*/

public class powerballPicks {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        
        int[] picks = {0,0,0,0,0,0,0,0};
        int[] lastWeek = {0,0,0,0,0,0,0,0};
        int numberOfNumbers = 8;
        int entry;
        int debug = 1;
        int min = 1;
        int max = 45;
        int random;

        //Loop through lastWeek array and fill with user entered numbers.        
        
        for (int i = 0; i < numberOfNumbers; i++) {
            //Check number is valid powerball number
            while (true) {
                System.out.println("Enter lastweeks Number " + (i+1) + ":");
                entry = Integer.valueOf(scanner.nextLine());
                if (entry <= 45 && entry > 0){
                    lastWeek[i] = entry;
                    break;
                } else {
                    System.out.println("Number must be between 1 and 45.");
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

        //Loop through picks array, generate random number, test if chosen already or used last week, and enter into array

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
                } else {
                    picks[j] = random;
                    break;
                }
            }
        }
        
        if (debug == 0) {
            System.out.println("New picks array filled");
            for (int b = 0; b < numberOfNumbers; b++) {
                System.out.print(picks[b] + ", ");
            }
            System.out.println();
        }

        //output picks
        System.out.println("Your randomized, less common numbers to pick are: ");
        for (int o = 0; o < numberOfNumbers; o++){
            System.out.print(picks[o] + ", ");

        }
        System.out.println();
        System.out.println("GOOD LUCK!");

        scanner.close();
    }

    // Method for checking if value is in an array
    public static boolean contains(final int[] arr, final int key){
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
}
