package View;
import Controller.Controller;
import Model.Exceptions.CollectionException;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;

import java.util.Scanner;

public class View {
    Scanner keyboard = new Scanner(System.in);
    int cmd;
    String cmdstring;
    Controller controller;

    public View(Controller c){
        controller = c;

    }

    public void start(){
        // System.out.println("hello world");

        while(true) {
            printMenu();
            cmdstring = keyboard.nextLine();

            try{
                cmd = Integer.parseInt(cmdstring);

            }

            catch (NumberFormatException nfe){
                cmd = 5;

            }

            switch(cmd){
                case 1:
                    System.out.println("Current Program:");
                    break;

                case 2:
                    System.out.println("Running Program...");

                    try {
                        controller.runAllSteps();

                    }

                    catch(StatementExecutionException SEE) {
                        System.out.println("Caught StatementExecutionException:");
                        System.out.println(SEE.getMessage());

                    }

                    catch(ExpressionEvaluationException EEE) {
                        System.out.println("Caught ExpressionEvaluationException:");
                        System.out.println(EEE.getMessage());

                    }

                    catch(CollectionException CE) {
                        System.out.println("Caught CollectionException");
                        System.out.println(CE.getMessage());

                    }

                    break;

                case 3:
                    System.out.println("Running Steps...");

                    try {
                        controller.runOneStep(controller.getCurrentProgramState());

                    }

                    catch(StatementExecutionException SEE) {
                        System.out.println("Caught StatementExecutionException:");
                        System.out.println(SEE.getMessage());

                    }

                    catch(ExpressionEvaluationException EEE) {
                        System.out.println("Caught ExpressionEvaluationException:");
                        System.out.println(EEE.getMessage());

                    }

                    catch(CollectionException CE) {
                        System.out.println("Caught CollectionException");
                        System.out.println(CE.getMessage());

                    }

                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Command");
                    break;
            }


        }

    }

    void printMenu(){
        System.out.println("BogoScript Language Interpreter");
        System.out.println("1. View Current Program");
        System.out.println("2. Run Program");
        System.out.println("3. Run One Step at a time");
        System.out.println("4. Exit");

    }


}
