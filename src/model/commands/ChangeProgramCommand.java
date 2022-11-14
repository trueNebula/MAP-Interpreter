package model.commands;

import repository.ProgramRepository;
import view.TextMenu;


public class ChangeProgramCommand extends Command{
    @SuppressWarnings("FieldMayBeFinal")
    private TextMenu menu;
    @SuppressWarnings("FieldMayBeFinal")
    private int index;
    @SuppressWarnings("FieldMayBeFinal")
    private ProgramRepository programRepository;

    public ChangeProgramCommand(String k, String desc, TextMenu m, int i, ProgramRepository pRepo){
        super(k, desc);
        menu = m;
        index = i;
        programRepository = pRepo;

    }

    @Override
    public void execute() {
        // this is so fucking janky
        menu.addCommand(new RunProgramCommand("2", "Run Selected Program.", programRepository.getProgramAtIndex(index)));

    }

}
