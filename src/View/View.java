package View;
import Controller.Controller;
import Model.Collections.List.GenericList;
import Model.Exceptions.CollectionException;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Statements.IStatement;
import Model.Values.IValue;

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

                    while(!controller.getCurrentProgramState().getExecutionStack().isEmpty()) {

                        try {
                            controller.runAllSteps();
                            GenericList<IValue> out = (GenericList<IValue>) controller.getCurrentProgramState().getOutputStream();

                            for(IValue i : out){
                                System.out.println(i.toString());

                            }

                            out.clear();

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

                        }

                    }

                    break;

                case 3:
                    System.out.println("Running Steps...");

                    try {
                        if (!controller.getCurrentProgramState().getExecutionStack().isEmpty()) {
                            IStatement currentStatement = controller.getCurrentProgramState().getExecutionStack().peek();
                            controller.runOneStep(controller.getCurrentProgramState());
                            GenericList<IValue> out = (GenericList<IValue>) controller.getCurrentProgramState().getOutputStream();

                            System.out.println(currentStatement.toString());

                            for (IValue i : out) {
                                System.out.println(i.toString());

                            }

                            out.clear();

                        } else
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
        System.out.println("1. View Current Program");
        System.out.println("2. Run Program");
        System.out.println("3. Run One Step at a time");
        System.out.println("4. Exit\n");

    }


}
