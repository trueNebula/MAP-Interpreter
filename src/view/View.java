package view;
import controller.Controller;
import model.exceptions.CollectionException;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.LoggingException;
import model.exceptions.StatementExecutionException;

import java.util.Scanner;

@SuppressWarnings("unused")
public class View {
    Scanner keyboard = new Scanner(System.in);
    int cmd;
    String cmdstring;
    Controller controller;

    @SuppressWarnings("unused")

    public View(Controller c){
        controller = c;

    }

    @SuppressWarnings("unused")
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
                    System.out.println(controller.getCurrentProgramState().getExecutionStack().peek());

                    break;

                case 2:
                    System.out.println("Running Program...");

                    while(!controller.getCurrentProgramState().getExecutionStack().isEmpty()) {

                        try {
                            controller.runAllSteps();

                        } catch (StatementExecutionException SEE) {
                            System.out.println("Caught StatementExecutionException:");
                            System.out.println(SEE.getMessage() + "\n");
                            break;

                        } catch (ExpressionEvaluationException EEE) {
                            System.out.println("Caught ExpressionEvaluationException:");
                            System.out.println(EEE.getMessage() + "\n");
                            break;

                        } catch (CollectionException CE) {
                            System.out.println("Caught CollectionException");
                            System.out.println(CE.getMessage() + "\n");
                            break;

                        } catch (LoggingException LE) {
                            System.out.println("Caught LoggingException");
                            System.out.println(LE.getMessage() + "\n");
                            break;

                        }

                    }

                    break;

                case 3:
                    System.out.println("Running Steps...");

                    try {
                        if (!controller.getCurrentProgramState().getExecutionStack().isEmpty())
                            controller.runOneStep(controller.getCurrentProgramState(), true);

                        else
                            System.out.println("Nothing to execute!");

                    }

                    catch(StatementExecutionException SEE){
                            System.out.println("Caught StatementExecutionException:");
                            System.out.println(SEE.getMessage() + "\n");

                        }

                    catch(ExpressionEvaluationException EEE){
                            System.out.println("Caught ExpressionEvaluationException:");
                            System.out.println(EEE.getMessage() + "\n");

                        }

                    catch(CollectionException CE){
                            System.out.println("Caught CollectionException");
                            System.out.println(CE.getMessage() + "\n");

                        }

                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Command\n");
                    break;
            }


        }

    }

    void printMenu(){
        System.out.println("BogoScript Language Interpreter");
        System.out.println("0. Change Program");
        System.out.println("1. View Current Program");
        System.out.println("2. Run Program");
        System.out.println("3. Run One Step at a time");
        System.out.println("4. Exit\n");

    }


}
