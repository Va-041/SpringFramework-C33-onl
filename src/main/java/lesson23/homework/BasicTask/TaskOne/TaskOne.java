package lesson23.homework.BasicTask.TaskOne;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskOne {

    private static final Scanner scanner = new Scanner(System.in);


    public static void startTaskOne() {

        System.out.println("\n========== Task ONE ==========\n");

        makeFiles();
        getRepeatMessage();
    }

    public static void makeFiles() {

        try {
            String directoryPath = "src/main/resources/";
            String fileName = "input.xml";

            File inputFile = new File(directoryPath + fileName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            //нормализация документа
            document.getDocumentElement().normalize();

            //парсинг данных
            Element author = (Element) document.getElementsByTagName("author").item(0);
            String firstName = author.getElementsByTagName("firstName").item(0).getTextContent();
            String lastName = author.getElementsByTagName("lastName").item(0).getTextContent();
            String title = document.getElementsByTagName("title").item(0).getTextContent();

            //формируем имя выходного файла
            String outputFileName = firstName + "_" + lastName + "_" + title + "_BASIC" + ".txt";

            //полный путь к выходному файлу
            String outputFilePath = directoryPath + outputFileName;

            //сбор всех строк в StringBuilder
            NodeList lines = document.getElementsByTagName("line");
            StringBuilder contentBuilder = new StringBuilder();

            for (int i = 0; i < lines.getLength(); i++) {
                Node node = lines.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String lineContent = node.getTextContent();
                    contentBuilder.append(lineContent).append(System.lineSeparator());
                }
            }

            //запись всего контента в единый файл
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(contentBuilder.toString());
                System.out.println("\n\n\tFile " + outputFileName + " was created successfully!");
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
                startTaskOne();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from Task ONE...");
                break;
            } else {
                System.out.println("\nIncorrect input. Please enter Y or N.");
            }
        }
    }
}


