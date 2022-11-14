import model.commands.ExitCommand;
import model.commands.RunProgramCommand;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import view.TextMenu;
import controller.Controller;
import repository.Repository;

public class Main {
    public static IStatement bigBoy, printTest, ifTest, rFileTest;
    public static void main(String[] args) {
        createPrograms();

        Repository repo1 = new Repository(bigBoy, "log1.txt");
        Controller cont1 = new Controller(repo1);

        Repository repo2 = new Repository(printTest, "log2.txt");
        Controller cont2 = new Controller(repo2);

        Repository repo3 = new Repository(ifTest, "log3.txt");
        Controller cont3 = new Controller(repo3);

        Repository repo4 = new Repository(rFileTest, "log4.txt");
        Controller cont4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new RunProgramCommand("1", "Run Example Program.", cont1));
        menu.addCommand(new RunProgramCommand("2", "Run Print Test Program.", cont2));
        menu.addCommand(new RunProgramCommand("3", "Run If Test Program.", cont3));
        menu.addCommand(new RunProgramCommand("4", "Run File Test Program.", cont4));
        menu.addCommand(new ExitCommand("5", "Exit."));
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

    }

}