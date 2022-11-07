package View;
import Controller.Controller;

public class View {
    public View(Controller controller){


    }

    public void start(){
        // System.out.println("hello world");

        printMenu();


    }

    void printMenu(){
        System.out.println("BogoScript Language Interpreter");
        System.out.println("1. View Current Program");
        System.out.println("2. Run Program");
        System.out.println("3. Run One Step at a time");


    }


}
