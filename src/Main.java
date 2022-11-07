import View.View;
import Controller.Controller;
import Repo.Repository;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository();
        Controller controller = new Controller(repo);
        View view = new View(controller);

        view.start();

    }

}