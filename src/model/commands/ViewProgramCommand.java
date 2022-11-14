package model.commands;

import controller.Controller;
import model.collections.stack.IStack;
import model.statements.CompoundStatement;
import model.statements.IStatement;

public class ViewProgramCommand extends Command{
    @SuppressWarnings("FieldMayBeFinal")
    private Controller controller;

    public ViewProgramCommand(String k, String desc, Controller c){
        super(k, desc);
        controller = c;

    }

    @Override
    public void execute() {
        IStack<IStatement> exeStack = controller.getRepository().getCurrentProgramState().getExecutionStack();

        if (!exeStack.isEmpty()) {
            printExeStackInorder(exeStack.peek());
            System.out.println();

        }

        else
            System.out.println("No program selected!");

    }

    private void printExeStackInorder(IStatement statement){
        if (statement instanceof CompoundStatement)
            printExeStackInorder(((CompoundStatement) statement).getFirst());

        if (!(statement instanceof CompoundStatement))
            System.out.println(statement);

        if (statement instanceof CompoundStatement)
            printExeStackInorder(((CompoundStatement) statement).getSecond());

    }

}
