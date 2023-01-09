package model.commands;

import controller.Controller;
import model.collections.dictionary.GenericDictionary;
import model.collections.dictionary.IDictionary;
import model.exceptions.*;
import model.types.IType;

public class RunProgramCommand extends Command{
    @SuppressWarnings("FieldMayBeFinal")
    private Controller controller;

    public RunProgramCommand(String k, String desc, Controller c){
        super(k, desc);
        controller = c;

    }

    @Override
    public void execute() {
        IDictionary<String, IType> typeEnv = new GenericDictionary<>();

        try {
            controller.getRepository().originalProgram.typeCheck(typeEnv);

        } catch (TypeCheckException TYE){
            System.out.println("Caught TypeCheckException");
            System.out.println(TYE.getMessage() + "\n");
            return;

        }

        try{
            controller.runAllSteps();

        } catch (StatementExecutionException SEE) {
            System.out.println("Caught StatementExecutionException:");
            System.out.println(SEE.getMessage() + "\n");

        } catch (ExpressionEvaluationException EEE) {
            System.out.println("Caught ExpressionEvaluationException:");
            System.out.println(EEE.getMessage() + "\n");

        }  catch (CollectionException CE) {
            System.out.println("Caught CollectionException");
            System.out.println(CE.getMessage() + "\n");

        } catch (LoggingException LE) {
            System.out.println("Caught LoggingException");
            System.out.println(LE.getMessage() + "\n");

        } catch (InterruptedException IE){
            System.out.println("Caught InterruptedException");
            System.out.println(IE.getMessage() + "\n");

        }

    }

}
