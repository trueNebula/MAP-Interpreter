package application;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.LoggingException;
import model.statements.IStatement;
import model.structures.ProgramState;
import model.values.IValue;
import model.values.StringValue;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

class Pair<T1, T2> {
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}

public class GUIProgramController implements Initializable {
    private Controller selectedProgram;

    @FXML
    private Text programStateIDText;
    @FXML
    private ListView<Integer> programStateIDList;
    @FXML
    private ListView<String> executionStackList;
    @FXML
    private TableView<Pair<Integer, IValue>> heapTableList;
    @FXML private TableColumn<Pair<Integer, IValue>, Integer> heapTableColumn1;
    @FXML private TableColumn<Pair<Integer, IValue>, String> heapTableColumn2;
    @FXML
    private TableView<Pair<String, IValue>> symbolTableList;
    @FXML private TableColumn<Pair<String, IValue>, String> symbolTableColumn1;
    @FXML private TableColumn<Pair<String, IValue>, String> symbolTableColumn2;
    @FXML
    private ListView<String> outputSteamList;
    @FXML
    private ListView<StringValue> fileTableList;
    @FXML
    private Button runButton;

    public GUIProgramController(){
        //setController(GUIController.getSelectedProgram());

    }

    public void setController(Controller controller) {
        this.selectedProgram = controller;
        populate();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        programStateIDList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        symbolTableColumn1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first));
        symbolTableColumn2.setCellValueFactory(p -> new SimpleStringProperty((p.getValue().second.toString())));

        heapTableColumn1.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        heapTableColumn2.setCellValueFactory(p -> new SimpleStringProperty((p.getValue().second.toString())));

        setController(GUIController.getSelectedProgram());

    }

    private ProgramState getCurrentProgramState() {
        if (selectedProgram.getProgramStateList().size() == 0)
            return null;

        else {
            int currentId = programStateIDList.getSelectionModel().getSelectedIndex();

            if (currentId == -1)
                return selectedProgram.getProgramStateList().get(0);

            else
                return selectedProgram.getProgramStateList().get(currentId);

        }

    }

    private void populate() {
        populateHeapTableView();
        populateOutputListView();
        populateFileTableListView();
        populateProgramStateIdentifiersListView();
        populateSymbolTableView();
        populateExecutionStackListView();

    }

    private void populateprogramStateIDText() {
        List<ProgramState> programStates = selectedProgram.getProgramStateList();
        programStateIDText.setText("Program State ID " + programStates.size());

    }

    private void populateHeapTableView() {
        ProgramState programState = getCurrentProgramState();
        IHeap heap = Objects.requireNonNull(programState).getHeapTable();
        ArrayList<Pair<Integer, IValue>> heapEntries = new ArrayList<>();
        for(Map.Entry<Integer, IValue> entry: heap.getElems().entrySet()) {
            heapEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        heapTableList.setItems(FXCollections.observableArrayList(heapEntries));
    }

    private void populateOutputListView() {
        ProgramState programState = getCurrentProgramState();
        List<String> output = new ArrayList<>();
        List<IValue> outputList = Objects.requireNonNull(programState).getOutputStream().getElems();
        int index;

        for (index = 0; index < outputList.size(); index++){
            output.add(outputList.get(index).toString());

        }

        outputSteamList.setItems(FXCollections.observableArrayList(output));
    }

    private void populateFileTableListView() {
        ProgramState programState = getCurrentProgramState();
        List<StringValue> files = new ArrayList<>(Objects.requireNonNull(programState).getFileTable().getElems().keySet());

        fileTableList.setItems(FXCollections.observableList(files));

    }

    private void populateProgramStateIdentifiersListView() {
        List<ProgramState> programStates = selectedProgram.getProgramStateList();
        List<Integer> idList = programStates.stream().map(ProgramState::getID).collect(Collectors.toList());
        programStateIDList.setItems(FXCollections.observableList(idList));
        populateprogramStateIDText();

    }

    private void populateSymbolTableView() {
        ProgramState programState = getCurrentProgramState();
        IDictionary<String, IValue> symbolTable = Objects.requireNonNull(programState).getSymbolTable();
        ArrayList<Pair<String, IValue>> symbolTableEntries = new ArrayList<>();

        for (Map.Entry<String, IValue> entry: symbolTable.getElems().entrySet()) {
            symbolTableEntries.add(new Pair<>(entry.getKey(), entry.getValue()));

        }

        symbolTableList.setItems(FXCollections.observableArrayList(symbolTableEntries));
    }

    private void populateExecutionStackListView() {
        ProgramState programState = getCurrentProgramState();
        List<String> executionStackToString = new ArrayList<>();

        if (programState != null)
            for (IStatement statement: programState.getExecutionStack().getReversed()) {
                executionStackToString.add(statement.toString());

            }

        executionStackList.setItems(FXCollections.observableList(executionStackToString));

    }

    public void runButtonClick(){
        if (selectedProgram != null) {
            try {
                List<ProgramState> programStates = Objects.requireNonNull(selectedProgram.getProgramStateList());
                if (programStates.size() > 0) {
                    selectedProgram.runOneStep();
                    populate();
                    programStates = selectedProgram.removeCompletedPrograms(selectedProgram.getProgramStateList());
                    selectedProgram.getRepository().setProgramStateList(programStates);
                    populateProgramStateIdentifiersListView();
                } else {
                    runButton.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Done!");
                    alert.setHeaderText("Your Program has finished!");
                    alert.setContentText("There is nothing left to execute!");
                    alert.showAndWait();
                }
            } catch (LoggingException | InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Execution error!");
                alert.setHeaderText("An execution error has occured!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An error has occured!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }

    }

}
