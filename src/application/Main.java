package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
	public Main() {

	}

	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("BogoScript Interpreter");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception var4) {
			var4.printStackTrace();

		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
