package view;

import model.commands.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, Command> commands;
    @SuppressWarnings("FieldMayBeFinal")
    private String menuName;

    public TextMenu(String name){
        commands = new HashMap<>();
        menuName = name;

    }

    public void addCommand(Command c){
        commands.put(c.getKey(), c);

    }

    public void printMenu(){
        System.out.println(menuName);

        for(Command i: commands.values()){
            String line = String.format("%4s : %s", i.getKey(), i.getDescription());
            System.out.println(line);

        }

    }

    public void show(){
        Scanner keyboard = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while(true) {
            printMenu();

            String key = keyboard.nextLine();
            Command c = commands.get(key);

            if (c == null) {
                System.out.println("Invalid Command!");
                continue;

            }

            c.execute();

        }

    }

}
