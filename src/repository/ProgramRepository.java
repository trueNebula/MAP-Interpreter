package repository;

import controller.Controller;

import java.util.ArrayList;

public class ProgramRepository {
    ArrayList<Controller> programs;

    public ProgramRepository(){
        programs = new ArrayList<>();

    }

    public void addProgram(Controller c){
        programs.add(c);

    }

    @SuppressWarnings("unused")
    public ArrayList<Controller> getPrograms(){
        return programs;

    }

    public Controller getProgramAtIndex(int index){
        return programs.get(index);

    }

}
