import model.commands.*;
import model.expressions.*;
import model.statements.*;
import model.types.IntType;
import model.types.ReferenceType;
import model.values.IntValue;
import repository.ProgramRepository;
import view.TextMenu;
import controller.Controller;
import repository.Repository;

public class Main {
    public static IStatement heapTest, garbTest;
    public static ProgramRepository programRepo;
    public static void main(String[] args) {
        // initialize Program Repo and create programs
        programRepo = new ProgramRepository();
        createPrograms();

        // initialize structures for each program and add them to the Program Repo
        Repository repo1 = new Repository(garbTest, "log1.txt");
        Controller cont1 = new Controller(repo1);
        programRepo.addProgram(cont1);



        // create Main menu and Program menu and start
        TextMenu menu = new TextMenu("BogoScript Interpreter");
        TextMenu programMenu = new TextMenu("Select a Program:");
        menu.addCommand(new ChangeMenuCommand("1", "Change Program", programMenu));
        menu.addCommand(new ViewProgramCommand("2", "View Program", cont1));
        menu.addCommand(new RunProgramCommand("3", "Run Selected Program", cont1));
        menu.addCommand(new ExitCommand("4", "Exit."));

        programMenu.addCommand(new ChangeMenuCommand("0", "Back", menu));
        programMenu.addCommand(new ChangeProgramCommand("1", "Example Program", menu, 0, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("2", "PrintTest Program", menu, 1, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("3", "IfTest Program", menu, 2, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("4", "RFileTest Program", menu, 3, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("5", "RelTest Program", menu, 4, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("6", "HeapTest Program", menu, 5, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("7", "WhileTest Program", menu, 6, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("8", "GarbTest Program", menu, 6, programRepo));

        menu.show();

    }

    public static void createPrograms(){
        heapTest =
                new CompoundStatement(
                        new VariableDeclarationStatement("p", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AllocationStatement("p", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("v", new ReferenceType(new ReferenceType(new IntType()))),
                                        new CompoundStatement(
                                                new AllocationStatement("v", new VariableExpression("p")),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("p")),
                                                        new PrintStatement(new VariableExpression("v"))
                                                )

                                        )
                                )

                        )
                );

        garbTest =
                new CompoundStatement(
                        heapTest,
                        new CompoundStatement(
                                new AllocationStatement("p", new ValueExpression(new IntValue(30))),
                                new PrintStatement(new HeapReadExpression(new HeapReadExpression(new VariableExpression("v"))))
                        )
                );

    }

}