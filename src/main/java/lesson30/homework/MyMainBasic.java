package lesson30.homework;

import lesson31.homework.TaskOne.StartPaymentSystem;
import lesson31.homework.TaskThree.StartCoffeeHouseWork;
import lesson31.homework.TaskTwo.StartComputerFacade;
import lesson31.homework.TaskWithOneAsterisk.StartPaymentStrategy;
import lesson31.homework.TaskWithThreeAsterisk.StartNewsPublisher;
import lesson31.homework.TaskWithTwoAsterisk.StartSupportWork;

public class MyMainBasic {
    public static void main(String[] args) {

        StartPaymentSystem.startTaskOne();
        StartComputerFacade.startTaskTwo();
        StartCoffeeHouseWork.startTaskThree();
        StartPaymentStrategy.startTaskWithOneAsterisk();
        StartNewsPublisher.startTaskWithTwoAsterisk();
        StartSupportWork.startTaskWithThreeAsterisk();
    }
}