package application;


import model.statements.*;
import model.types.IntType;
import model.types.ReferenceType;
import model.values.IntValue;
import repository.ProgramRepository;
import controller.Controller;
import repository.Repository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.expressions.HeapReadExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    public static ProgramRepository programRepo;
    public static IStatement heapTest, garbTest, threadTest;
    private static Controller selectedProgramController;

    @FXML
    private ListView<Controller> programList;

    public void selectProgram(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/application/Main2.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        programList.getItems().add(programRepo.getProgramAtIndex(0));
        programList.getItems().add(programRepo.getProgramAtIndex(1));
        programList.getItems().add(programRepo.getProgramAtIndex(2));

        programList.getSelectionModel().selectedItemProperty().addListener((observableValue, controller, t1) -> selectedProgramController = programList.getSelectionModel().getSelectedItem());

    }

    public GUIController(){
        // initialize Program Repo and create programs
        programRepo = new ProgramRepository();
        createPrograms();

        // initialize structures for each program and add them to the Program Repo
        Repository repo1 = new Repository(heapTest, "log2.txt");
        Controller cont1 = new Controller(repo1);
        programRepo.addProgram(cont1);

        Repository repo2 = new Repository(garbTest, "log2.txt");
        Controller cont2 = new Controller(repo2);
        programRepo.addProgram(cont2);

        Repository repo3 = new Repository(threadTest, "log3.txt");
        Controller cont3 = new Controller(repo3);
        programRepo.addProgram(cont3);

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

    public static Controller getSelectedProgram(){
        return selectedProgramController;

    }

}
