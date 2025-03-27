package lesson23.homework.BasicTask.TaskTWO;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class TaskTwo {

    private static final Scanner scanner = new Scanner(System.in);


    public static void startTaskTWO() {

        System.out.println("\n========== Task TWO ==========\n");

        makeDeserialization();
        getRepeatMessage();
    }

    public static void makeDeserialization() {

        String jsonString = "{ \"id\": 99, \"name\": \"Johnik Donnik\", \"email\": \"johnik.donnik@gmail.com\" }";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            //десериализация
            User user = objectMapper.readValue(jsonString, User.class);

            System.out.println("\nDeserialized object:");
            System.out.println(user.toString());

        } catch (Exception e) {
            System.out.println("\nDeserialization error: " + e.getMessage());
        }
    }




    public static void getRepeatMessage() {
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it?   Enter Y or N:  ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startTaskTWO();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from Task TWO...");
                break;
            } else {
                System.out.println("\nIncorrect input. Please enter Y or N.");
            }
        }
    }
}
