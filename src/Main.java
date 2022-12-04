import model.commands.*;
import model.expressions.RelationalExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.IntType;
import model.types.ReferenceType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.ProgramRepository;
import view.TextMenu;
import controller.Controller;
import repository.Repository;

public class Main {
    public static IStatement bigBoy, printTest, ifTest, rFileTest, relTest, heapTest, heapTest0;
    public static ProgramRepository programRepo;
    public static void main(String[] args) {
        // initialize Program Repo and create programs
        programRepo = new ProgramRepository();
        createPrograms();

        // initialize structures for each program and add them to the Program Repo
        Repository repo1 = new Repository(bigBoy, "log1.txt");
        Controller cont1 = new Controller(repo1);
        programRepo.addProgram(cont1);

        Repository repo2 = new Repository(printTest, "log2.txt");
        Controller cont2 = new Controller(repo2);
        programRepo.addProgram(cont2);

        Repository repo3 = new Repository(ifTest, "log3.txt");
        Controller cont3 = new Controller(repo3);
        programRepo.addProgram(cont3);

        Repository repo4 = new Repository(rFileTest, "log4.txt");
        Controller cont4 = new Controller(repo4);
        programRepo.addProgram(cont4);

        Repository repo5 = new Repository(relTest, "log5.txt");
        Controller cont5 = new Controller(repo5);
        programRepo.addProgram(cont5);

        Repository repo6 = new Repository(heapTest, "log6.txt");
        Controller cont6 = new Controller(repo6);
        programRepo.addProgram(cont6);

        // create Main menu and Program menu and start
        TextMenu menu = new TextMenu("BogoScript Interpreter");
        TextMenu programMenu = new TextMenu("Select a Program:");
        menu.addCommand(new ChangeMenuCommand("1", "Change Program", programMenu));
        menu.addCommand(new ViewProgramCommand("2", "View Program", cont6));
        menu.addCommand(new RunProgramCommand("3", "Run Selected Program", cont6));
        menu.addCommand(new ExitCommand("4", "Exit."));

        programMenu.addCommand(new ChangeMenuCommand("0", "Back", menu));
        programMenu.addCommand(new ChangeProgramCommand("1", "Example Program", menu, 0, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("2", "PrintTest Program", menu, 1, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("3", "IfTest Program", menu, 2, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("4", "RFileTest Program", menu, 3, programRepo));
        programMenu.addCommand(new ChangeProgramCommand("5", "HeapTest Program", menu, 4, programRepo));

        menu.show();

    }

    public static void createPrograms(){
        bigBoy = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(
                new VariableAssignmentStatement("v",
                        new ValueExpression(
                                new IntValue(2))),
                new PrintStatement(new VariableExpression("v"))
        ));

        ifTest =
                new IfStatement(
                        new ValueExpression(new IntValue(0)),
                        new PrintStatement(new ValueExpression(new BoolValue(true))),
                        new PrintStatement(new ValueExpression(new BoolValue(false)))
                );

        printTest =
                new CompoundStatement(
                        new PrintStatement(new ValueExpression(new IntValue(1))),
                        new PrintStatement(new ValueExpression(new IntValue(2)))
                );

        rFileTest =
                new CompoundStatement(
                        new VariableDeclarationStatement("file", new StringType()),
                        new CompoundStatement(
                                new VariableAssignmentStatement("file",
                                        new ValueExpression(
                                                new StringValue("test.in"))),
                                new CompoundStatement(
                                        new OpenRFile(new VariableExpression("file")),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("var", new IntType()),
                                                new CompoundStatement(
                                                        new ReadFile(new VariableExpression("file"), new StringValue("var")),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("var")),
                                                                new CompoundStatement(
                                                                        new ReadFile(new VariableExpression("file"), new StringValue("var")),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("var")),
                                                                                new CloseRFile(new VariableExpression("file"))
                                                                        )
                                                                )

                                                        )

                                                )

                                        )
                                )
                        )
                );

        relTest =
                new IfStatement(
                        new RelationalExpression(new ValueExpression(new IntValue(3)), ">", new ValueExpression(new IntValue(5))),
                        new PrintStatement(new ValueExpression(new BoolValue(true))),
                        new PrintStatement(new ValueExpression(new BoolValue(false)))
                );

        heapTest0 =
                new CompoundStatement(
                        new VariableDeclarationStatement("p", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AllocationStatement("p", new ValueExpression(new IntValue(20))),
                                new PrintStatement(new VariableExpression("p"))
                        )
                );

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

    }

}