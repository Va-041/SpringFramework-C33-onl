package lesson30.homework;

import lesson30.homework.TaskFour.CreateDeveloperWithBuilder;
import lesson30.homework.TaskOne.Singleton;
import lesson30.homework.TaskThree.CreateCourseProgram;
import lesson30.homework.TaskTwo.DeveloperProgram;

public class MyMainBasic {
    public static void main(String[] args) {

        Singleton.startTaskOne();
        DeveloperProgram.startTaskTwo();
        CreateCourseProgram.startTaskThree();
        CreateDeveloperWithBuilder.startTaskFour();
    }
}