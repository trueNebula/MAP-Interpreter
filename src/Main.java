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
    public static IStatement heapTest, garbTest, threadTest;
    public static ProgramRepository programRepo;
    public static void main(String[] args) {
        // initialize Program Repo and create programs
        programRepo = new ProgramRepository();
        createPrograms();

        // initialize structures for each program and add them to the Program Repo
        Repository repo1 = new Repository(garbTest, "log1.txt");
        Controller cont1 = new Controller(repo1);
        programRepo.addProgram(cont1);

        Repository repo2 = new Repository(threadTest, "log2.txt");
        Controller cont2 = new Controller(repo2);
        programRepo.addProgram(cont2);



        // create Main menu and Program menu and start
        TextMenu menu = new TextMenu("BogoScript Interpreter");
        TextMenu programMenu = new TextMenu("Select a Program:");
        menu.addCommand(new ChangeMenuCommand("1", "Change Program", programMenu));
        menu.addCommand(new ViewProgramCommand("2", "View Program", cont2));
        menu.addCommand(new RunProgramCommand("3", "Run Selected Program", cont2));
        menu.addCommand(new ExitCommand("4", "Exit."));

        programMenu.addCommand(new ChangeMenuCommand("0", "Back", menu));
        programMenu.addCommand(new ChangeProgramCommand("1", "GarbTest Program", menu, 0, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("2", "ThreadTest Program", menu, 1, programRepo));

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

        threadTest =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                                new CompoundStatement(
                                        new VariableAssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new AllocationStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new HeapWriteStatement("a", new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new VariableAssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("v")),
                                                                                        new PrintStatement(new HeapReadExpression(new VariableExpression("a")))

                                                                                )

                                                                        )

                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReadExpression(new VariableExpression("a")))

                                                        )
                                                )

                                        )

                                )

                        )
                );

    }

}