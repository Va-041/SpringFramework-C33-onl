package lesson23.homework.TaskWithAsterisk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XmlParserUPG {

    private static final String directoryPath = "src/main/resources/";

    private static final Scanner scanner = new Scanner(System.in);

    public static void startTaskWithAsterisk() {

        System.out.println("\n========== Task with ASTERISK ==========\n");

        getMenu();
        getRepeatMessage();
    }

    public static void getMenu() {
        int choice = -1; // Инициализируем выбор

        while (true) {
            System.out.print("\nEnter 1 for parsing using SAX or 2 for parsing using DOM:   ");

            //проверка на корректный ввод
            if (scanner.hasNextInt()) { //проверяем, является ли ввод числом
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2) { //принимаем 1 или 2
                    break;
                } else {
                        System.out.println("Wrong choice. Enter 1 or 2.");
                }
            } else {
                System.out.println("Incorrect input. Please enter only the number 1 or 2.");
                scanner.next(); //очистка некорректного ввода
            }
        }

        File inputFile = new File("src/main/resources/input.xml");

        if (choice == 1) {
            System.out.println("You have chosen SAX parsing.");
            parseWithSAX(inputFile);
        } else {
            System.out.println("You have chosen DOM parsing.");
            parseWithDOM(inputFile);
        }
    }

    //метод для парсинга DOM
    private static void parseWithDOM(File inputFile) {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            //нормализация документа
            document.getDocumentElement().normalize();

            Element author = (Element) document.getElementsByTagName("author").item(0);
            String firstName = author.getElementsByTagName("firstName").item(0).getTextContent();
            String lastName = author.getElementsByTagName("lastName").item(0).getTextContent();
            String title = document.getElementsByTagName("title").item(0).getTextContent();

            //уникальное имя для общего файла
            String outputFileName = firstName + "_" + lastName + "_" + title + "_DOM" +".txt";
            String outputFilePath = directoryPath + outputFileName;


            //сбор всех строк в StringBuilder
            NodeList lines = document.getElementsByTagName("line");
            StringBuilder contentBuilder = new StringBuilder();

            for (int i = 0; i < lines.getLength(); i++) {
                Node node = lines.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    contentBuilder.append(node.getTextContent()).append(System.lineSeparator());
                }
            }

            //запись всего в файл
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(contentBuilder.toString());
                System.out.println("\nFile " + outputFileName + " was successfully created using DOM.");
            }
        } catch (Exception e) {
            System.out.println("\nException: " + e.getMessage());
        }
    }

    //метод для парсинга SAX
    private static void parseWithSAX(File inputFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            //обработчик событий SAX
            DefaultHandler handler = new DefaultHandler() {
                StringBuilder contentBuilder = new StringBuilder();
                String firstName = "";
                String lastName = "";
                String title = "";
                boolean isLine = false;
                boolean isFirstName = false;
                boolean isLastName = false;
                boolean isTitle = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("line")) {
                        isLine = true;
                    } else if (qName.equalsIgnoreCase("firstName")) {
                        isFirstName = true;
                    } else if (qName.equalsIgnoreCase("lastName")) {
                        isLastName = true;
                    } else if (qName.equalsIgnoreCase("title")) {
                        isTitle = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    String text = new String(ch, start, length).trim();
                    if (isLine) {
                        contentBuilder.append(text).append(System.lineSeparator());
                    } else if (isFirstName) {
                        firstName = text;
                    } else if (isLastName) {
                        lastName = text;
                    } else if (isTitle) {
                        title = text;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (qName.equalsIgnoreCase("line")) {
                        isLine = false;
                    } else if (qName.equalsIgnoreCase("firstName")) {
                        isFirstName = false;
                    } else if (qName.equalsIgnoreCase("lastName")) {
                        isLastName = false;
                    } else if (qName.equalsIgnoreCase("title")) {
                        isTitle = false;
                    }
                }

                @Override
                public void endDocument() {
                    // Имя файла формируем на основе тегов
                    String outputFileName = firstName + "_" + lastName + "_" + title + "_SAX.txt";
                    String outputFilePath = directoryPath + outputFileName;


                    //запись в файл
                    try (FileWriter writer = new FileWriter(outputFilePath)) {
                        writer.write(contentBuilder.toString());
                        System.out.println("\nFile " + outputFileName + " was successfully created using SAX.");
                    } catch (IOException e) {
                        System.out.println("\nException: " + e.getMessage());

                    }
                }
            };

            saxParser.parse(inputFile, handler);
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
                startTaskWithAsterisk();
                return;
            } else if (userInput == 'N' || userInput == 'n') {
                System.out.println("\n\nExit from Task with ASTERISK...");
                break;
            } else {
                System.out.println("\nIncorrect input. Please enter Y or N.");
            }
        }
    }
}

