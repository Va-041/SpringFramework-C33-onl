package lesson23.homework.BasicTask.TaskThree;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskThree {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskThree() {
        System.out.println("\n========== Task THREE ==========\n");

        parseXMLWithStAX();
        getRepeatMessage();
    }

    public static void parseXMLWithStAX() {

        try {

            String directoryPath = "src/main/resources/";
            String fileName = "input.xml";

            File inputFile = new File(directoryPath + fileName);

            // Создаем фабрику и ридер для обработки XML
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(new FileReader(inputFile));

            StringBuilder contentBuilder = new StringBuilder();

            // Для хранения метаданных
            String firstName = "";
            String lastName = "";
            String title = "";

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                // Начало элемента
                if (event.isStartElement()) {
                    String elementName = event.asStartElement().getName().getLocalPart();

                    switch (elementName) {
                        case "firstName":
                            firstName = reader.getElementText(); // Считываем содержимое тега
                            break;
                        case "lastName":
                            lastName = reader.getElementText();
                            break;
                        case "title":
                            title = reader.getElementText();
                            break;
                        case "line":
                            contentBuilder.append(reader.getElementText()).append(System.lineSeparator());
                            break;
                    }
                }
            }

            String outputFileName = firstName + "_" + lastName + "_" + title + "_StAX.txt";
            //полный путь к выходному файлу
            String outputFilePath = directoryPath + outputFileName;


            // Запись результата в файл
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(contentBuilder.toString());
                System.out.println("\nFile " + outputFileName + " was successfully created using StAX.");
            }

        } catch (Exception e) {
            System.out.println("\nException: " + e.getMessage());
        }
    }

    public static void getRepeatMessage() {
        char userInput;

        while (true) {
            System.out.print("\n\nWould you like to repeat it?   Enter Y or N:  ");
            userInput = scanner.next().charAt(0);

            if (userInput == 'Y' || userInput == 'y') {
                startTaskThree();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from Task THREE...");
                break;
            } else {
                System.out.println("\nIncorrect input. Please enter Y or N.");
            }
        }
    }
}
