package model.commands;

import view.TextMenu;

public class ChangeMenuCommand extends Command{
    @SuppressWarnings("FieldMayBeFinal")
    private TextMenu menu;

    public ChangeMenuCommand(String k, String desc, TextMenu m){
        super(k, desc);
        menu = m;

    }


    @Override
    public void execute() {
        menu.show();

    }
}
