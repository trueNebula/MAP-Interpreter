package model.statements;

import model.collections.dictionary.IDictionary;
import model.exceptions.StatementExecutionException;
import model.structures.ProgramState;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

@SuppressWarnings("unused")

public class VariableDeclarationStatement implements IStatement{
    String variableName;
    IType variableType;

    public VariableDeclarationStatement(String name, IType type){
        variableName = name;
        variableType = type;

    }

    public String toString(){
        return variableType.toString() + " " + variableName + "; ";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();

        if(symTable.get(variableName) != null)
            throw new StatementExecutionException("Variable already declared");

        else{
            IValue variable;
            if(variableType.toString().equals("int"))
                variable = new IntValue(0);

            else
                variable = new BoolValue(false);


            symTable.put(variableName, variable);

        }

        return state;

    }

}
